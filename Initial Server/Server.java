import hangman.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*; 
import org.omg.PortableServer.POA;
import java.util.*; 
import java.io.*;

public class Server {
    public static void main(String args[]) {
        String name_service="hangman";

        try{
            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            HangmanImpl hangman_service = new HangmanImpl();

          	org.omg.CORBA.Object service_ref =  rootpoa.servant_to_reference(hangman_service);
            Hangman hangman_ref = HangmanHelper.narrow(service_ref);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent nc = new NameComponent(name_service, "");
            NameComponent path[] = {nc};
            ncRef.rebind(path, hangman_ref);

            System.out.println("Server running...");

			BufferedReader data_reader = new BufferedReader(new InputStreamReader(System.in));
            String msg_kbd= " ";
            while (!msg_kbd.equals("quit") && !msg_kbd.equals("QUIT")) {
                System.out.println("Enter \"quit\" to shutdown the server...");
                msg_kbd = data_reader.readLine();
                if(msg_kbd.equals("quit") || msg_kbd.equals("QUIT")) {
                    System.out.println("Shutdown of server is in progress...");
                    ncRef.unbind(path);
                    orb.shutdown(false);
                    System.out.println("Shutdown was successful...");
                }
            }

        } catch (Exception e) {
            System.out.println("Exception in server...\nException:\n" + e);
        }
    }
}



