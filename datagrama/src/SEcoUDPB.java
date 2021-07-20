import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
public class SEcoUDPB {
    public static void main(String[] args){
        try{
            //vargasespinocarloshassan3cm18
            String dst = "127.0.0.1";
            int pto = 2000;
            DatagramSocket s = new DatagramSocket(2000);
            System.out.println("Servidor iniciado, esperando cliente");
            for(;;){
                DatagramPacket p = new DatagramPacket(new byte[2000],2000);
                //recibiendo respuesta desde el cliente los paquetes
                s.receive(p);
                System.out.println("Datagrama recibido desde"+p.getAddress()+":"+p.getPort());
                InetAddress Cliente=p.getAddress();
                int port =p.getPort();
                int Paquetes = ByteBuffer.wrap(p.getData()).getInt();
                System.out.println("Paquetes esperados: "+Paquetes);
                //Obteniendo mensaje
                String respuesta="";
                for(int i=0;i<Paquetes;i++){
                    s.receive(p);
                    String temp = new String(p.getData(),0,p.getLength());
                    respuesta+=temp;
                    System.out.println("Recibiendo mensaje: " + respuesta);
                }
                System.out.println("Mensaje recibido: " + respuesta);
                //reenviamos al cliente
                 int from=0;
                int to=0;
                byte mensajeBytes[] = respuesta.getBytes();
                byte aux[];
                for(int i=0; i<(Paquetes-1); i++){
                    from=i*20;
                    to=from+20;
                    aux= Arrays.copyOfRange(mensajeBytes,from, to);
                    p = new DatagramPacket(aux,aux.length, Cliente, port);
                    s.send(p);
                }
                //Enviando último paquete
                aux= Arrays.copyOfRange(mensajeBytes,to, mensajeBytes.length);
                p = new DatagramPacket(aux,aux.length, Cliente, port);
                s.send(p);
                System.out.println("La respuesta del servidor se ha mandado");
            }//for
            //s.close()
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}

