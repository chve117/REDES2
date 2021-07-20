import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import static javafx.beans.binding.Bindings.length;
public class CEcoUDPB {
    public static void main(String[] args){
        try{
            //vargasespinocarloshassan 3cm18
            DatagramPacket p;
            DatagramSocket cl = new DatagramSocket();
            System.out.print("Cliente iniciado, escriba un mensaje de saludo:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String mensaje = br.readLine();
            byte[] b = mensaje.getBytes();
            //Diviendo los paquetes
            int Paquetes = (int)Math.ceil((double)b.length/20);//se usa ceil para las fracciones 2.4 llegue a 3 por ejemplo
            byte[] PaquetesBytes = ByteBuffer.allocate(4).putInt(Paquetes).array();
            System.out.println("Numero de paquetes necesarios: " +Paquetes);
            System.out.println("El mensaje que mando es: "+ mensaje);
            //mandando la cantidad de paquetes para generar los packetsocket
            int longitud=b.length;
            System.out.println("longitud entera: "+longitud);
            String dst = "127.0.0.1";
            int pto = 2000;
            p = new DatagramPacket(PaquetesBytes,PaquetesBytes.length,InetAddress.getByName(dst),pto);
            cl.send(p);
            //mandamos los paquetes
            int piso=0,techo=0;
            byte aux[];
            for(int i=0;i<(Paquetes-1);i++){
                piso=i*20;
                techo=piso+20;
                aux= Arrays.copyOfRange(b,piso,techo);
                p = new DatagramPacket(aux,aux.length,InetAddress.getByName(dst),pto);
                cl.send(p);
            }
            aux= Arrays.copyOfRange(b,techo,b.length);
            p= new DatagramPacket(aux,aux.length,InetAddress.getByName(dst),pto);
            cl.send(p);
            //recibo respuesta del servidor
           p = new DatagramPacket(new byte[20],20);
            System.out.println("Recibiendo Eco del Servidor...\n");
            String respserv="";
            for(int i=0;i<Paquetes;i++){
                cl.receive(p);
                String temp = new String(p.getData(),0,p.getLength());
                respserv+=temp;
                System.out.println("Respuesta de servidor"+respserv);
            }
            System.out.println("Respuesta de servidor"+respserv);

            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}