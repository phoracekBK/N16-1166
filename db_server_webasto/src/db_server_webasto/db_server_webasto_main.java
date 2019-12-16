package db_server_webasto;

import database.database_interface;
import gui.graphic_interface;
import gui.hello_prompt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.communication_layer;
import server.server_thread;

public class db_server_webasto_main 
{
	public static String logged_in;

	public static void main(String[] args) 
	{	
		
            String line;
            String pidInfo ="";

            try 
            {
                Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
                BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((line = input.readLine()) != null) 
                {
                    pidInfo+=line; 
                    System.out.println(line);

                }
                input.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(db_server_webasto_main.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(pidInfo.contains("Database and traceability software"))
            {
                System.out.println("here");
            }


            /* inicializace spousteciho okna */
		
		hello_prompt hello_progress = new hello_prompt();
		hello_progress.setVisible(true);
		
		logged_in = new String();
		
		hello_progress.set_label_progress("Loading of database content:");
		hello_progress.set_prograss(10);
		
		/* inicializace databaze */

		database_interface database = new database_interface("./db/db_content.db");
	
		hello_progress.set_label_progress("Initializing network interface:");
		hello_progress.set_prograss(40);
		
		/* inicializace komunikacniho vlakna pro plc 1 */
		server_thread server_thread_plc_1 = new server_thread();
		
		/* inicializace komunikacniho vlakna pro plc 2 */
		server_thread server_thread_plc_2 = new server_thread();
		
		/* inicializace komunikace pro plc 1 */
		communication_layer communication_1 = new communication_layer();
		
		/* inicializace komunikace pro plc 2 */
		communication_layer communication_2 = new communication_layer();
		
		/* nastaveni reference na datab�zovou strukturu */
		communication_1.set_database_reference(database);
		communication_2.set_database_reference(database);
		
		/* nastaveni reference na serverovou komunikaci */
		server_thread_plc_1.set_server_reference(communication_1);
		server_thread_plc_2.set_server_reference(communication_2);
		
		/* spusteni paralelniho vlakna komunikace pro plc 1 */
		server_thread_plc_1.start();
		
		/* spusteni paralelniho vlakna komunikace pro plc 2 */
		server_thread_plc_2.start();
		
		/* inicializace grafickeho rozhrani */
		graphic_interface window = new graphic_interface(hello_progress, server_thread_plc_1, server_thread_plc_2, database);
		window.setVisible(true);
		//window.show_login_window();
		
		hello_progress.setVisible(false);
		hello_progress = null;

	}

}
