import java.net.*;
import java.io.*;
public class SEcoTCPB {
    public static void main(String[] args){
        try{
            // Se crea el socket
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Esperando cliente ...");
            // Iniciamos el ciclo infinito 
            for(;;){
                //recibimos
                
                // Tenemos un bloqueo, en el momento que llegue una conexión continua el programa
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde "+  cl.getInetAddress()+":" + cl.getPort());
                String mensaje ="Hola mundo";
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                // Se envía el mensaje
                pw.println(mensaje);
                // Se limpia le flujo
                pw.flush();
                BufferedReader br3 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
                String mensajito = br3.readLine();
                System.out.println("Recibimos un mensaje desde el cliente");
                System.out.println("Mensaje:"+mensajito);
                 pw.println(mensajito);
                 pw.flush();
	  pw.close();
                cl.close();
            }//for
}catch(Exception e){ // Manejo de excepciones
            e.printStackTrace();
        }//catch
    }//main
}
