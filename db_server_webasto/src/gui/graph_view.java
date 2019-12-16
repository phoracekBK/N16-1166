package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import database.database_interface;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

public class graph_view extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	private int height;
	private int width;
	private int treshold_temperature;
	private int treshold_humidity;
	private JSlider slider;
	private JLabel humidity_label_max;
	private JLabel humidity_label_min;
	private JLabel temperature_label_min;
	private JLabel temperature_label_max;
	private JButton btnBack;
	private JButton btnNext;
	private JLabel lblDateMin;
	private JLabel lblDateMax;
	private JTextField humidity_treshold;
	private JTextField temperature_treshold;
	private JButton btnSet;
	private JLabel lblHumiditytreshold;
	private JLabel lblTemperatureTreshold;
	private database_interface database_reference;
	private int mouse_position_x;
	private int mouse_position_y;
	private JLabel lblTemperatureselection;
	private JLabel lblHumidityselection;
	private JLabel lblSelectionDate;
	private boolean repaint_enable;
	private String[] temperature_list;
	private String[] humidity_list;
	private String[] date_list;
	private int list_size;
	
	public graph_view(database_interface database) 
	{
		this.database_reference = database;
		this.repaint_enable = false;
		this.treshold_humidity = 50;
		this.treshold_temperature = 20;
		this.mouse_position_x = 0;
		this.mouse_position_y = 0;
		
		setLayout(null);
		this.setMaximumSize(new Dimension(0,0));	
		temperature_label_max = new JLabel("60\u00B0C");
		add(temperature_label_max);		
		temperature_label_min = new JLabel("0\u00B0C");
		add(temperature_label_min);		
	    humidity_label_min = new JLabel("0%");
		add(humidity_label_min);		
		humidity_label_max = new JLabel("100%");
		add(humidity_label_max);		
	    slider = new JSlider();    
	    
	    slider.setMinimum(1);
	  
	    slider.setBounds(443, 120, 56, 16);
	    slider.setValue(0);
		add(slider);	
		btnBack = new JButton("Back");		
		btnBack.setBounds(75, 350, 97, 25);
		add(btnBack);		
		btnNext = new JButton("Next");	
		btnNext.setBounds(692, 362, 97, 25);
		add(btnNext);	
		lblDateMin = new JLabel("date min");
		lblDateMin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateMin.setBounds(232, 58, 56, 16);
		add(lblDateMin);		
		lblDateMax = new JLabel("date max");
		lblDateMax.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblDateMax);
		
		humidity_treshold = new JTextField();
		humidity_treshold.setText("50");
		humidity_treshold.setBounds(443, 610, 116, 22);
		add(humidity_treshold);
		humidity_treshold.setColumns(10);
		
		temperature_treshold = new JTextField();
		temperature_treshold.setText("20");
		
		
		temperature_treshold.setBounds(595, 610, 116, 22);
		add(temperature_treshold);
		temperature_treshold.setColumns(10);
		
		btnSet = new JButton("Set");
		
		btnSet.setBounds(751, 609, 97, 25);
		add(btnSet);
		
		lblHumiditytreshold = new JLabel("humidity treshold:");
		lblHumiditytreshold.setBounds(443, 581, 116, 16);
		add(lblHumiditytreshold);
		
		lblTemperatureTreshold = new JLabel("temperature treshold:");
		lblTemperatureTreshold.setBounds(595, 581, 140, 16);
		add(lblTemperatureTreshold);
		
		lblSelectionDate = new JLabel("date");
		lblSelectionDate.setVisible(false);
		
		add(lblSelectionDate);
		
		lblHumidityselection = new JLabel("humiditySelection");
		lblHumidityselection.setVisible(false);;
		lblHumidityselection.setBounds(179, 719, 56, 16);
		add(lblHumidityselection);
		
		lblTemperatureselection = new JLabel("temperatureSelection");
		lblTemperatureselection.setVisible(false);
		lblTemperatureselection.setBounds(179, 748, 56, 16);
		add(lblTemperatureselection);
		
		Timer timer = new Timer();
			
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  refresh();
			  }
			}, 0, 60*1000);
		
		
		events();
	}
	
	private void events()
	{
			
		addComponentListener(new ComponentListener() 
		{
		    public void componentResized(ComponentEvent e) 
		    {
		    	slider.setBounds(170,80, getWidth()-340, 20);
		    	btnNext.setBounds(getWidth()-70-80 ,72 , 80, 30);
		    	btnBack.setBounds(70 , 72, 80, 30);
		        temperature_label_max.setBounds(35,110,65, 20);
		        temperature_label_min.setBounds(40, (int)((getHeight()/100.0)*25.0)+105,65, 20);
		        humidity_label_min.setBounds(45, 120+35+(int)((getHeight()/100.0)*50.0),65, 20);
		        humidity_label_max.setBounds(35, 120+45+(int)((getHeight()/100.0)*25.0),65, 20);
				lblDateMin.setBounds((int)(getWidth()/2.0)-250, 30, 200,20);
				lblDateMax.setBounds((int)(getWidth()/2.0)+50, 30, 200, 20);
				temperature_treshold.setBounds((getWidth()-70-80-200-30),(int) (getHeight()/100.0*50.0)+170+20+20, 200, 30);
				btnSet.setBounds((getWidth()-70-80), (int) (getHeight()/100.0*50.0)+170+20+20, 80, 30);
				humidity_treshold.setBounds((getWidth()-70-80-200-30-20-200),(int) (getHeight()/100.0*50.0)+170+20+20, 200, 30);
				lblHumiditytreshold.setBounds((getWidth()-70-80-200-30-20-200),(int) (getHeight()/100.0*50.0)+170+15, 200, 15);
				lblTemperatureTreshold.setBounds((getWidth()-70-80-200-30),(int) (getHeight()/100.0*50.0)+170+15, 200, 15);
				
				mouse_position_x = 0;
				mouse_position_y = 0;
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
		
		
		addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				System.out.println("mouse");
				mouse_position_x = arg0.getX();
				mouse_position_y = arg0.getY();
				repaint_enable = true;
				revalidate();
				repaint();
				System.out.println("repaint - mouse");
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				// TODO Auto-generated method stub	
			}

			@Override
			public void mouseExited(MouseEvent arg0) 
			{
				// TODO Auto-generated method stub
						
			}

			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				// TODO Auto-generated method stub
						
			}

			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				// TODO Auto-generated method stub		
			}
			
		});
		
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				slider.setValue(slider.getValue()-1);
				mouse_position_x = 0;
	    		mouse_position_y = 0;
	    		revalidate();
	    		repaint();
			}
		});
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				slider.setValue(slider.getValue()+1);
				mouse_position_x = 0;
	    		mouse_position_y = 0;
	    		revalidate();
	    		repaint();
			}
		});
		
		slider.addChangeListener(new ChangeListener() 
		{
	    	public void stateChanged(ChangeEvent arg0) 
	    	{
	    		System.out.println(slider.getValue());
	    		mouse_position_x = 0;
	    		mouse_position_y = 0;
	    		revalidate();
	    		repaint();
	    		System.out.println("repaint - slider");
	    	}
	    });
		
		temperature_treshold.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				int i = 0;
				String tempString = "";
				while(i<temperature_treshold.getText().length() && i <2)
				{
					if(temperature_treshold.getText().charAt(i) >47 && temperature_treshold.getText().charAt(i) <58)
					tempString += temperature_treshold.getText().charAt(i);
					i++;
				}
				temperature_treshold.setText(tempString);
			}
		});
		
		humidity_treshold.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				int i = 0;
				String tempString = "";
				while(i<humidity_treshold.getText().length() && i <2)
				{
					if(humidity_treshold.getText().charAt(i) >47 && humidity_treshold.getText().charAt(i) <58)
					tempString += humidity_treshold.getText().charAt(i);
					i++;
				}
				humidity_treshold.setText(tempString);
			}
		});
		
		btnSet.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!humidity_treshold.getText().equals(""))
				{
						treshold_humidity = Integer.parseInt(humidity_treshold.getText());	
				}
				
				if(!temperature_treshold.getText().equals(""))
				{
					if(Integer.parseInt(temperature_treshold.getText()) <80)
					{
						treshold_temperature = Integer.parseInt(temperature_treshold.getText());
					}
					else
					{
						temperature_treshold.setText(Integer.toString(treshold_temperature));
					}
				}
				revalidate();
				repaint();
				System.out.println("repaint - set");
			}
		});
	}
	
	 @Override
	    public void paintComponent(Graphics g) 
	    {
	        super.paintComponent(g);
	         
	        System.out.println("repaint");
	        
	        /* draw graph frame */
	        this.height =  this.getSize().height;
	        this.width = this.getSize().width;
	        
	        
	        /* graph field 1 */
	        g.drawRect(70, 120, (width-140), (int)((height/100.0)*25.0));
	        g.setColor (Color.WHITE );
	       
	        g.fillRect(71, 121,width-141, (int)((height/100.0)*25.0)-1);
	        
	        /* graph field 2 - humidity */
	        g.setColor ( Color.BLACK );
	        g.drawRect(70, 170+(int)((height/100.0)*25.0), (width-140), (int)((height/100.0)*25.0));
	        g.setColor ( Color.WHITE );
	        g.fillRect(71, 171+(int)((height/100.0)*25.0), (width-141), (int)((height/100.0)*25.0)-1);
	        

	        g.setColor (Color.BLACK );

	        
	        //g.drawLine(((width/100)*50), ((height/100)*50),((width/100)*50), ((height/100)*75));
	        
	        /* temperature treshold line */
	        double temperature_position = ((double) (((height/100.0)*25.0)) /60.0 * treshold_temperature);
	        g.setColor(Color.RED);
	        g.drawLine(70, 120+(int)((height/100.0)*25.0) -((int) temperature_position),width-70,120+(int)((height/100.0)*25.0) -((int) temperature_position));
	        
	        
	        g.setColor(Color.BLUE);
	        double humidity_position = ((double)((( height/100.0)*70.0) - (((height/100.0)*45.0)))/100.0 * treshold_humidity);
	        g.drawLine(70, 120+50+(int)((height/100.0)*25.0)+(int)((height/100.0)*25.0 -humidity_position), width-70 ,120+50+(int)((height/100.0)*25.0)+(int)((height/100.0)*25.0) -((int) humidity_position));

	        draw_graph(g);   
	    }
	 
	 private void refresh()
	 {
		 try 
		 {
			 /* spocitat pocet zaznamu v tabulce */
			 Connection enviroment_table = database_reference.get_database_structure();
			 Statement stmt = enviroment_table.createStatement();
			 ResultSet rs = stmt.executeQuery("select count(*) from enviroment");
		 
			 this.list_size = rs.getInt(1);
			 this.temperature_list = new String[this.list_size];
			 this.humidity_list = new String[this.list_size];
			 this.date_list = new String[this.list_size];
			 
			 this.slider.setMaximum(this.list_size);
			 
			 String sql = "SELECT * FROM 'enviroment';";
			 
	 		 rs  = stmt.executeQuery(sql);

	 		 int index = 0;
	 		 while (rs.next())
	 		 {
	 			 this.date_list[index] = rs.getString(1);
	 			 this.temperature_list[index] = rs.getString(2);
	 			 this.humidity_list[index] = rs.getString(3);
	 			 index ++;
	 		 }
			 
		 }
		 catch(SQLException esd)
		 {
			 esd.printStackTrace();
		 }
	 }

	 private void draw_graph(Graphics g)
	 {
		 
		 /*
		  * CREATE TABLE 'covers' (
  'cover_code' varchar(255),
  'orientation' varchar(255),
  'PA10B_date_time' varchar(255),
  'PA10B_activator_left' varchar(255),
  'PA10B_activator_right' varchar(255),
  PRIMARY KEY ('cover_code'));
		  * 
		  * 
		  * CREATE TABLE 'frame_in_process_581' (
  'frame_code' varchar(255),
  'order' varchar(255),
  'configuration' varchar(255),
  'PA10R_date_time' varchar(255),
  'PA30R_date_time' varchar(255),
  'PA50R_date_time' varchar(255),
  'PA60R_date_time' varchar(255),
  'PA10R_Activator_DW646' varchar(255),
  'PA10R_Remover_208_SIKA' varchar(255),
  'PA10R_Primer_207_SIKA' varchar(255),
  'PA10R_Primer_DV954V1' varchar(255),
  'PA30R_Primer_207_SIKA' varchar(255),
  'PA30R_Remover_208_SIKA' varchar(255),
  PRIMARY KEY ('frame_code'));
		  * 
		  * 
		  * 
		  * CREATE TABLE 'frame_in_process_582' (
  'frame_code' varchar(255),
  'order' varchar(255),
  'configuration' varchar(255),
  'PA10R_date_time' varchar(255),
  'PA30R_date_time' varchar(255),
  'PA50R_date_time' varchar(255),
  'PA60R_date_time' varchar(255),
  'PA10R_Activator_DW646' varchar(255),
  'PA10R_Remover_208_SIKA' varchar(255),
  'PA10R_Primer_207_SIKA' varchar(255),
  'PA10R_Primer_DV954V1' varchar(255),
  'PA30R_Primer_207_SIKA' varchar(255),
  'PA30R_Remover_208_SIKA' varchar(255),
  PRIMARY KEY ('frame_code'));
		  * 
		  * 
		  * CREATE TABLE 'glued_frame_581' (
  'ID' varchar(255),
  'frame_code' varchar(255),
  'order' varchar(255),
  'configuration' varchar(255),
  'PA10R_date_time' varchar(255),
  'PA30R_date_time' varchar(255),
  'PA50R_date_time' varchar(255),
  'PA60R_date_time' varchar(255),
  'PA10R_Activator_DW646' varchar(255),
  'PA10R_Remover_208_SIKA' varchar(255),
  'PA10R_Primer_207_SIKA' varchar(255),
  'PA10R_Primer_DV954V1' varchar(255),
  'PA30R_Primer_207_SIKA' varchar(255),
  'PA30R_Remover_208_SIKA' varchar(255),
  PRIMARY KEY ('ID'));
  
  CREATE TABLE 'glued_frame_582' (
  'ID' varchar(255),
  'frame_code' varchar(255),
  'order' varchar(255),
  'configuration' varchar(255),
  'PA10R_date_time' varchar(255),
  'PA30R_date_time' varchar(255),
  'PA50R_date_time' varchar(255),
  'PA60R_date_time' varchar(255),
  'PA30B_date_time' varchar(255),
  'PA10R_Activator_DW646' varchar(255),
  'PA10R_Remover_208_SIKA' varchar(255),
  'PA10R_Primer_207_SIKA' varchar(255),
  'PA10R_Primer_DV954V1' varchar(255),
  'PA30R_Primer_207_SIKA' varchar(255),
  'PA30R_Remover_208_SIKA' varchar(255),
  PRIMARY KEY ('ID'));
		  * 
		  * 
		  * 
		  * CREATE TABLE 'enviroment' (
  'date_time' varchar(255),
  'temperature' varchar(255),
  'humidity' varchar(255),
  PRIMARY KEY ('date_time'));
		  */
		 	 if (this.list_size > 0)
		 	 {
		 		 int index = slider.getValue() > 1440 ? (slider.getValue()-1440) : 0; // 1440 = 24*60
		 		 int temperature_pre = (int) (120+((height/100.0)*25.0));
				 int humidity_pre = (int) ((120+50+(int)((height/100.0)*25.0)+(int)((height/100.0)*25.0)));
				 int position_pre = 70;
		 		 
				 g.setColor(Color.green);
				 
				 while(index < this.list_size && index < slider.getValue())
				 {
					 System.out.println(index);
					 if (index+1440 >= slider.getValue())
					 {
						 int temperature = (int) (120+((height/100.0)*25.0) - ((double) (((height/100.0)*25.0)) /60.0 * Double.parseDouble(this.temperature_list[index])));
						 int humidity = (int) (120+50+(int)((height/100.0)*25.0)+(int)((height/100.0)*25.0 - ((double)((( height/100.0)*70.0) - (((height/100.0)*45.0)))/100.0 *Double.parseDouble(this.humidity_list[index]))));
						 int position;
						 
						 if(slider.getValue() < 1440)
						 {
							 if (index == 0)
							 {
								 lblDateMin.setText(this.date_list[index]);
								 temperature_pre = temperature;
								 humidity_pre = humidity;
							 }
						 }
						 else
						 {
							 if(1440-(slider.getValue()-index) == 0)
							 {
								 lblDateMin.setText(this.date_list[index]);
								 temperature_pre = temperature;
								 humidity_pre = humidity;
							 }
						 }
						 
						 if(index == slider.getValue()-1)
						 {
							 lblDateMax.setText(this.date_list[index]); 
						 }
						 
						 if (slider.getValue() <= 1440)
						 {
							 position = (int)(70.0+((getWidth() - 140.0)/1440.0)* (double)(index));
						 }
						 else
						 {
							 position = (int)(70.0+((getWidth() - 140.0)/1440.0)* (double)(1440-(slider.getValue()-index)));	
						 } 
		
						 g.setColor(Color.green);
						 g.drawLine(position_pre, temperature_pre, position, temperature);
						 g.drawLine(position_pre, humidity_pre, position, humidity);
						 
						
						 
						 if(mouse_position_x > 70 && mouse_position_x < getWidth()-70 && (mouse_position_y > 120 && mouse_position_y < (120+((height/100.0)*25.0))) || mouse_position_y >170+(int)((height/100.0)*25.0) && mouse_position_y < 170+((height/100.0)*50.0))
						 {
							 if((mouse_position_x-((int)(getWidth() - 140.0)/1440.0)) <= position && position <= mouse_position_x +((int)(getWidth() - 140.0)/1440.0))
							 {
								 g.setColor(Color.black);
								 g.drawLine(mouse_position_x, 120, mouse_position_x,(int)(120+((height/100.0)*25.0)));
								 g.drawLine(mouse_position_x, 170+(int)((height/100.0)*25.0), mouse_position_x,170+(int)((height/100.0)*50.0));

								
								 if(this.repaint_enable == true)
								 {
									 this.repaint_enable = false;
									 lblSelectionDate.setText(this.date_list[index]);
									 lblTemperatureselection.setText("T: "+Double.parseDouble(this.temperature_list[index])+"°C");
									 lblHumidityselection.setText("H: "+Double.parseDouble(this.humidity_list[index])+"%");
									 
									 lblSelectionDate.setVisible(true);
									 lblTemperatureselection.setVisible(true);
									 lblHumidityselection.setVisible(true);
		
									 if(position >= (getWidth()-200))
									 {
										 lblSelectionDate.setBounds(position-200, 123+(int)((height/100.0)*25.0), 200, 15); 
										 lblSelectionDate.setHorizontalAlignment(SwingConstants.RIGHT);
										 
										 lblTemperatureselection.setBounds(position-200, 138+(int)((height/100.0)*25.0), 200, 15);
										 lblTemperatureselection.setHorizontalAlignment(SwingConstants.RIGHT);
										 
										 lblHumidityselection.setBounds(position-200, 153+(int)((height/100.0)*25.0), 200, 15);
										 lblHumidityselection.setHorizontalAlignment(SwingConstants.RIGHT);
									 }
									 else
									 {
										 lblSelectionDate.setBounds(position, 123+(int)((height/100.0)*25.0), 200, 15);
										 lblSelectionDate.setHorizontalAlignment(SwingConstants.LEFT);
										 
										 lblTemperatureselection.setBounds(position, 138+(int)((height/100.0)*25.0), 200, 15);
										 lblTemperatureselection.setHorizontalAlignment(SwingConstants.LEFT);
										 
										 lblHumidityselection.setBounds(position, 153+(int)((height/100.0)*25.0), 200, 15);
										 lblHumidityselection.setHorizontalAlignment(SwingConstants.LEFT);
									 }
								 }
								
							 }
						 }
						 else
						 {
							 lblSelectionDate.setVisible(false);
							 lblTemperatureselection.setVisible(false);
							 lblHumidityselection.setVisible(false);
						 }
						 
						 temperature_pre = temperature;
						 position_pre = position;
						 humidity_pre = humidity;
					 }
					 
					 index ++;
				 }
		 	 }
	 }
}
