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

		//insert code//
    }
}



