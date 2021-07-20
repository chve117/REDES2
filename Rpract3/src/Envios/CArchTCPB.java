/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Envios;
import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;

public class CArchTCPB {

    public static void Enviainfo(DataOutputStream Envi, int filesN, int buffSize) {
        try {
            Envi.writeInt(buffSize);
            Envi.flush();
            Envi.writeInt(filesN);
            Envi.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
// función usada para el envio del tamaño de buffer y tambien el archivo seleccionado
    }

    public static void sendFile(DataOutputStream dos, String Path, String Name, long Size, int buffSize) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(Path));
            dos.writeUTF(Name); 
            dos.flush();
            dos.writeLong(Size);
            dos.flush();
            byte[] b = new byte[buffSize];
            long envio = 0;
            int porcentaje, n;
            while (envio < Size) {//mandamos condicion mientras que envio sea menor al tamaño del archivo
                n = dis.read(b);
                System.out.println("Hola bb");
                dos.write(b, 0, n);
                dos.flush();
                envio = envio + n;
                porcentaje = (int) (envio * 100 / Size);// describimos el porcentaje del envio
                System.out.print("Enviado: " + porcentaje + "%\r");// imprimimos el porcentaje de cada archivo enviado
            } // While
            System.out.println("Enviando");
            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escriba la direccion del servidor: "); //se ingresa la direccion del servidor
            String host = br.readLine();
            // Este es el puerto que se ponga en el servidor, en este caso 7000 
            System.out.println("Escriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());

            System.out.println("Buffer size: (Max: 65545 ) ");//hacemos saber el tamaño maximo del buffer
            int aux = Integer.parseInt(br.readLine());
            int buffSize = aux < 65000 ? aux : 65000;//comparamos si el valor ingresado es menor al tamaño maximo del buffer
            
            System.out.println("Desea aplicar nagle 1. Si 2. No ");// preguntamos si se desea aplicar el algoritmo de nagle
            boolean nagle = Integer.parseInt(br.readLine()) == 1 ? true : false;// parseo para la descicion del uso del algoritmo

            Socket Client;
            JFileChooser jf = new JFileChooser(".");/* El punto significa que lo va a abrir en la */
            /* carpeta donde esta corriendo el archivo java */
            jf.setMultiSelectionEnabled(true);//se hace uso de la multiseleccion de atchivos
            int r = jf.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                System.out.println("Selected Files: ");
                Client = new Socket(host, pto);
                DataOutputStream dos = new DataOutputStream(Client.getOutputStream());
                if (!nagle)
                    Client.getTcpNoDelay(); /* Apaga Nagle */
                Enviainfo(dos, jf.getSelectedFiles().length, buffSize);//Llamamos a la funcion para enviar info a servidor

                for (File F : jf.getSelectedFiles()) {
                    System.out.println("File: " + F.getName() + " Size: " + F.length());
                    sendFile(dos, F.getAbsolutePath(), F.getName(), F.length(), buffSize);
                }
                dos.close();

                Client.close();

            } // if
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}