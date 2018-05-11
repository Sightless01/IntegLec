import HangmanGame.*;           // The package containing stubs
import org.omg.CosNaming.*;  // Client_hello will use the naming service
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;      // All CORBA applications need these classes
import java.util.*;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import java.awt.*;
import java.applet.Applet;

//
// Usage: java -cp [path] Client_hello -ORBInitialPort [Port]
// [path] is the path that contains the "HelloWorld" directory 
//      Ex:  [path]\HelloWord
// [Port] is the same port number of "orbd -ORBInitialPort [Port]"
//
public class Client
{
	
  public static void main(String args[])
  {
    String name = "";
    String word = "";
	String name_service="hangman";
//
//      create the properties:org.omg.CORBA.ORBInitialHost and org.omg.CORBA.ORBInitialPort
//	Properties properties = System.getProperties();
//      properties.put("org.omg.CORBA.ORBInitialHost",args[0]);
//      properties.put( "org.omg.CORBA.ORBInitialPort",args[1]);
//
    try{
      
      System.out.println("Client>Creating and initializing the ORB");
      ORB orb = ORB.init(args, null); //or ORB orb = ORB.init(args, properties);  to start the orb with the properties
//      
      System.out.println("Client>getting the root naming context");
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
//
//    it uses NamingConetExt Interoperable Naming Service
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef); 
//      
      System.out.println("Client>Resolving the object reference in naming ["+name_service+"]");
      NameComponent nc = new NameComponent(name_service, "");
      NameComponent path[] = {nc};
      Hangman hangman = HangmanHelper.narrow(ncRef.resolve(path));
//      
      System.out.println("Client>calling the Hello service...");
	  do {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel jUserName = new JLabel("User Name");
        JTextField username = new JTextField();
		panel.add(jUserName, BorderLayout.NORTH);
		panel.add(username, BorderLayout.SOUTH);
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
        int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION);
 
        if (result == JOptionPane.OK_OPTION) {
            name = username.getText();
			word = hangman.startGame(name);
			if(word.equals("Player Already exists.")){
				String message = "Ooops!Player Already exists.";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error",
                JOptionPane.ERROR_MESSAGE);
			}
			System.out.println(word);
		} else if (result == JOptionPane.CANCEL_OPTION){
			System.exit(0);
		}
      } while(word.equals("Player Already exists."));
      HangmanUI hangmanUI = new HangmanUI(hangman, name, word);
      hangmanUI.setResizable(false);
      hangmanUI.setLocationRelativeTo(null);
      hangmanUI.setVisible(true);     
    }catch(Exception e)
    {
	System.out.println("Exception:\n"+e);
    }
//
  }
}
