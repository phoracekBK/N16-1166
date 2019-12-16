package server;

public class server_thread extends Thread
{

	communication_layer server;
    private boolean server_enable;
    
    public server_thread()
    {
        this.server_enable = false;
    }
    
    public void set_server_reference(communication_layer reference)
    {
        this.server = reference;
    }
   
    public communication_layer get_server_reference()
    {
        return this.server;
    }
    
    public void server_stop()
    {
       this.server.socket_close();
       this.server_enable = false;
    }  
    
    public void server_start()
    {
        synchronized(this)
        {
            this.server_enable = true;
            this.notify();
        }
    }
	 @Override
	    public synchronized void run()
	    {
	        try 
	        {
	            while(true)
	            {
	                while(this.server_enable == false)
	                {
	                    this.wait();
	                }
	                
	                if (server != null)   
	                {
	                    server.plc_communication();
	                }
	            }
	        } 
	        catch (InterruptedException ex) 
	        {
	            
	        }
	    }
}
