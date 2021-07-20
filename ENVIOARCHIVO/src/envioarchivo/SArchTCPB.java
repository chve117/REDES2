/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENVIOARCHIVO;

import java.net.*;
import java.io.*;

public class SArchTCPB {
    public static void main(String[] args) {

        int i = 0;
        try {
            ServerSocket s = new ServerSocket(7000);
            System.out.println("Servidor preparadado.");
            for (;;) {
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde" + cl.getInetAddress() + ":" + cl.getPort());
                DataInputStream dis = new DataInputStream(cl.getInputStream());

                int buffSize = dis.readInt();
                System.out.println("Tamaño del buffer: " + buffSize);

                int nfiles = dis.readInt();
                System.out.println("Numero de archivos: " + nfiles);

            
                for (i = 0; i < nfiles; i++) {
                    
                    byte[] b = new byte[buffSize];

                    String nombre = dis.readUTF();
                    System.out.println("Recibimos el archivo: " + nombre);

                    long tam = dis.readLong();
                    System.out.println("Tamanio: " + tam);

                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre));
                    long recibidos = 0;
                    int n, porcentaje;
                    while (recibidos < tam) {
                        n = dis.read(b);
                        dos.write(b, 0, n);
                        dos.flush();
                        recibidos = recibidos + n;
                        porcentaje = (int) (recibidos * 100 / tam);
                        System.out.print("Recibido: " + porcentaje + "%\r");
                    } // While
                    System.out.println("Archivo recibido.");
                    dos.close();
                }
                dis.close();
                cl.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } // catch
    }
}
