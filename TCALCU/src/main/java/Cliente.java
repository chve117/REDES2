import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    private Cliente() {}
    public static void main(String[] args) {
        Scanner entrada= new Scanner(System.in);
	String host = (args.length < 1) ? null : args[0];
	try {
	    Registry registry = LocateRegistry.getRegistry(host);	
            //también puedes usar getRegistry(String host, int port)
            Suma stub = (Suma) registry.lookup("Suma");
            Suma Resta = (Suma) registry.lookup("Resta");
            Suma Multi = (Suma) registry.lookup("Multi");
            Suma Divi = (Suma) registry.lookup("Divi");
	    int x=0,y=0;
            System.out.println("Escriba x:");
            x= entrada.nextInt();
            System.out.println("Escriba y:");
            y= entrada.nextInt();
	    int response = stub.suma(x,y);
            int respuesta = stub.resta(x,y);
            int respuestam = stub.multi(x,y);
            float respuestad = stub.divi(x,y);
	    System.out.println("respuesta sumar "+x+" y "+y+" : " + response);
	    System.out.println("respuesta resta "+x+" y "+y+" : " + respuesta);
            System.out.println("respuesta multiplicacion "+x+" y "+y+" : " + respuestam);
            System.out.println("respuesta division "+x+" y "+y+" : " + respuestad);
	} catch (Exception e) {
	    System.err.println("Excepción del cliente: " +e.toString());
	    e.printStackTrace();
	}
      }
}

