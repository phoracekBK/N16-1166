package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class communication_layer extends communication_interface
{
	private ServerSocket server_socket;
    private Socket connection_handler;
    
    public communication_layer()
    {
    	super();
    }
    
    public void socket_close()
    {
        try 
        {
            if(this.connection_handler != null)
            {
                this.connection_handler.close();
                this.connection_handler = null;
            }
            
            if (this.server_socket != null)
            {
                this.server_socket.close();
                this.server_socket = null;
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(server_thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void server_init(int server_tcp_port)
    {
        try 
        { 
            if(this.server_socket != null)
            {
                this.server_socket.close();
            }
            
            this.server_socket = new ServerSocket(server_tcp_port);
           
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(server_thread.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Server already run, application close!");
            System.exit(20);
        }
    }
    
    public void plc_communication()
    { 
            System.out.println("server enabled");
            try 
            {
                this.connection_handler = this.server_socket.accept();
                this.connection_handler.setSoTimeout(3000);

                System.out.println("connection to: "+connection_handler.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(connection_handler.getInputStream()));
                OutputStreamWriter writer = new OutputStreamWriter(connection_handler.getOutputStream());
               
                String receaved_data;
                while( true)
                {
                   receaved_data = in.readLine();
                    System.out.println(receaved_data); 
                    if(!receaved_data.equals(""))
                    {
                    	String str = parse_command(receaved_data);
                        writer.write(copy_and_fill_to_start(str, 1024, (char) 128, (char)128));
                        writer.flush();
                        System.out.println("message send - "+ str); 
                    }
                }
            }
            catch(Exception ex)
            {
                System.out.println("Server reset");
                try 
                {
                    if( this.connection_handler != null)
                    {
                        this.connection_handler.close();
                    } 
                } 
                catch (IOException ex1) 
                {
                    Logger.getLogger(server_thread.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
}
