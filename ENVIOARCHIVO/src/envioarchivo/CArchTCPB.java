/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENVIOARCHIVO;
import javax.swing.JFileChooser;
import java.net.*;
import java.io.*;

public class CArchTCPB {

    public static void sendInfo(DataOutputStream dos, int filesN, int buffSize) {
        try {
            dos.writeInt(buffSize);/* Tamaño del buffer */
            dos.flush();
            dos.writeInt(filesN);/* Numero de archivos */
            dos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendFile(DataOutputStream dos, String Path, String Name, long Size, int buffSize) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(Path));
            dos.writeUTF(Name); /* Envia el nombre del archivo */
            dos.flush();
            dos.writeLong(Size);/* Envia el tamaño del archivo */
            dos.flush();
            byte[] b = new byte[buffSize];/* Ya tiene el tamaño de buffer que ingresamos */
            long sent = 0;
            int porcentaje, n;
            while (sent < Size) {
                n = dis.read(b);
                dos.write(b, 0, n);
                dos.flush();
                sent = sent + n;
                porcentaje = (int) (sent * 100 / Size);
                System.out.print("Enviado: " + porcentaje + "%\r");
            } // While
            System.out.println("| - - - Sent - - - |");
            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Server address: "); /* Aqui va localhost */
            String host = br.readLine();
            /* Este es el puerto que se ponga en el servidor, en este caso 7000 */
            System.out.println("On Port: ");
            int pto = Integer.parseInt(br.readLine());

            System.out.println("Nagle (?): 1. Yes 2. No ");
            boolean nagle = Integer.parseInt(br.readLine()) == 1 ? true : false;
            /* Esta parte sirve con el metodo de Socket llamado .getTCPNoDelay() */
            /* Por lo que con solo un if queda removido el algoritmo */
            /* Si no se usa es aplicado */

            System.out.println("Buffer size: (Max: 65545 ) ");
            int aux = Integer.parseInt(br.readLine());
            int buffSize = aux < 65000 ? aux : 65000;

            Socket Client;
            JFileChooser jf = new JFileChooser(".");/* El punto significa que lo va a abrir en la */
            /* carpeta donde esta corriendo el archivo java */
            jf.setMultiSelectionEnabled(true);/* Esto agrega la multiseleccion */
            int r = jf.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                System.out.println("Selected Files: ");
                Client = new Socket(host, pto);
                DataOutputStream dos = new DataOutputStream(Client.getOutputStream());
                if (!nagle)
                    Client.getTcpNoDelay(); /* Apaga Nagle */
                sendInfo(dos, jf.getSelectedFiles().length, buffSize);/* Manda la info */

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
