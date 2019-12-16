package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JTabbedPane;

import database.database_interface;
import db_server_webasto.db_server_webasto_main;
import server.server_thread;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;


public class graphic_interface extends JFrame 
{
	private static final long serialVersionUID = 1L;
	/**
	 * Create the frame.
	 */
	private JTabbedPane tabbedPane;
	private login_window login;
	private traceability station_monitoring;

	public graphic_interface(hello_prompt hello_progress, server_thread server_reference_plc_1, server_thread server_reference_plc_2, database_interface database) 
	{
		hello_progress.set_label_progress("Initialization of main window...");
		
		setTitle("Database and traceability software");
		ImageIcon icon = new ImageIcon("./icons/bk.png");
        this.setIconImage(icon.getImage());
        this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(800,600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
        hello_progress.set_prograss(50);
        hello_progress.set_label_progress("Loading graphic...");

		hello_progress.set_prograss(60);
		this.login = new login_window(this.tabbedPane, database);

		tabbedPane.addTab("Settings", new settings_view(server_reference_plc_1, server_reference_plc_2, database, login));
		tabbedPane.addTab("Traceability", new database_management(database));
		hello_progress.set_prograss(60);
		
		station_monitoring = new traceability();
		
		tabbedPane.addTab("Station monitor", station_monitoring);
		
		/* nastaveni odkazu na objekt zobrazovani stavu stanic do objektu komunikacniho rozhrani */
		server_reference_plc_1.get_server_reference().set_traceability_reference(station_monitoring.get_traceability_referece());
		server_reference_plc_2.get_server_reference().set_traceability_reference(station_monitoring.get_traceability_referece());
		
		hello_progress.set_prograss(70);
		tabbedPane.addTab("Environment monitor", new graph_view(database));
		this.tabbedPane.setVisible(false);
		
		hello_progress.set_label_progress("Loading loging window...");
		this.pack();	
	
		this.login.setVisible(true);
		hello_progress.set_prograss(90);
		events();
	}
  
	private void events()
        {
		addWindowStateListener(new WindowStateListener() 
		{
			@Override
			public void windowStateChanged(WindowEvent arg0) 
			{
				if(db_server_webasto_main.logged_in.equals(""))
				{
					if ((arg0.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED)
					{
						login.setVisible(false);
						System.out.println("minimize");
					}
					   // maximized
					else if ((arg0.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH)
					{
					    login.setVisible(true);
					    System.out.println("maximize");
					}
				}
			}
		});

		addComponentListener(new ComponentListener() 
		{
		    public void componentResized(ComponentEvent e) 
		    {
			      if(tabbedPane != null)
			      {
			    	  tabbedPane.setBounds(10, 10, getWidth()-40, getHeight()-70);
			    	  station_monitoring.setSize(getWidth()-40, getHeight()-70);
			      }
		     }

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
	
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
