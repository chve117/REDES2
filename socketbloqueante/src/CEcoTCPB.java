import java.net.*;
import java.io.*;
import java.util.Scanner;
public class CEcoTCPB {
    public static void main(String[] args){
      try{
            Scanner sc=new Scanner(System.in);
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            System.out.printf("Escriba la dirección del servidor: ");
           String host = br1.readLine();
           System.out.printf("\n\nEscriba el puerto:");
           int pto = Integer.parseInt(br1.readLine());
           // Creamos el socket y nos conectamos
          Socket cl = new Socket(host,pto);
          BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
          //pedimos el mensaje
          System.out.println("Ingrese el mensaje:");
          String mensajito=sc.nextLine();
          System.out.println(mensajito);
          // Nos conectamos
          System.out.println("Conexión establecida desde "+  cl.getInetAddress()+":" + cl.getPort());
          //mandamos mensajito
          String mensaje = br2.readLine();
          System.out.println("Recibimos un mensaje desde el servidor");
          System.out.println("Mensaje:"+mensaje);
          //mandamos el mensajito
          PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                // Se envía el mensaje
                pw.println(mensajito);
                // Se limpia le flujo
                pw.flush();
                BufferedReader br4 = new BufferedReader(new InputStreamReader(cl.getInputStream()));
                System.out.println("Eco bbcita:"+br4.readLine());
          // Cerramos flujos y socket
            pw.close();
            br1.close();
            br2.close();
            cl.close();
        }catch(Exception e){ //Manejo de excepciones
            e.printStackTrace();
        }
    }
}
