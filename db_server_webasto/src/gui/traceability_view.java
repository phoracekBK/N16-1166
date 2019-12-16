package gui;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.SwingConstants;

public class traceability_view extends JPanel 
{
	private JLabel lblFs;
	private JLabel lblPa30r;
	private JLabel lblPa20b;
	private JLabel lblPa60r;
	private JLabel lblPa10b;
        private JLabel lblPa30b;
        
        
	private static final long serialVersionUID = 1L;
	private JLabel lblFS_name;
	private JLabel lblPA30R_name;
	private JLabel lblPA60R_name;
	private JLabel lblPA20B_name;
	private JLabel lblPA10B_name;
        private JLabel lblPA30B_name;
	private int fs_hight;
	private int pa30r_hight;
	private int pa60r_hight;
	private int pa20b_hight;
	private int pa10b_hight;
        private int pa30b_hight;
		
	private JLabel pa30r_station_state;
	private JLabel pa60r_station_state;
	private JLabel pa20b_station_state;
	private JLabel pa20b_blend_expiration;
	private JLabel pa10b_station_state;
        private JLabel pa30b_station_state;
	private JLabel fs_station_state;	
	
	private JLabel pa30r_cyclus_state;
	private JLabel pa60r_cyclus_state;
	private JLabel pa10b_cyclus_state;
        private JLabel pa30b_cyclus_state;
	private JLabel fs_cyclus_state;	
		
	private JLabel pa30r_home_position;
	private JLabel pa60r_home_position;
	private JLabel pa10b_home_position;
        private JLabel pa30b_home_position;
	
	
        private JLabel pa30b_actual_frame_code;	
	private JLabel pa30r_actual_frame_code;
	private JLabel pa60r_actual_frame_code;
	private JLabel pa10b_actual_left_cover_code;
	private JLabel pa10b_actual_right_cover_code;
        private JLabel pa30b_actual_left_cover_code;
	private JLabel pa30b_actual_right_cover_code;
	
	
	private JLabel shaker_1_expiration;
	private JLabel shaker_2_expiration;
	private JLabel shaker_3_expiration;
	private JLabel shaker_4_expiration;
	
	private JLabel pa30r_station_configuration;
	private JLabel pa60r_station_configuration;
	
	private int higth = 0;

	private int count_resolution(int image_width, int image_hight, int new_width)
	{
		return (int) (((double)image_hight)/(((double)image_width)/((double)new_width)));
	}
	
	public traceability_view() 
	{
	
		//this.setBackground(Color.WHITE);
		
	    setLayout(null);
		
	    ImageIcon icon = null;
	    
	    lblFs = new JLabel("fs");
	    icon = new ImageIcon("./icons/FS_icon.PNG");
	    fs_hight = count_resolution(icon.getIconWidth(), icon.getIconHeight(),400);
	   
		lblFs.setIcon(new ImageIcon(icon.getImage().getScaledInstance(400,fs_hight, Image.SCALE_SMOOTH)));
		add(lblFs);
		
		lblPa30r = new JLabel("pa30r");
		icon = new ImageIcon("./icons/PA30R_icon.PNG");
		pa30r_hight = count_resolution(icon.getIconWidth(), icon.getIconHeight(), 400);
		
		lblPa30r.setIcon(new ImageIcon(icon.getImage().getScaledInstance(400, pa30r_hight, Image.SCALE_SMOOTH)));
		add(lblPa30r);
		
		lblPa60r = new JLabel("pa60r");
		icon = new ImageIcon("./icons/PA60R_icon.PNG");
		pa60r_hight = count_resolution(icon.getIconWidth(), icon.getIconHeight(), 400);
		
		lblPa60r.setIcon(new ImageIcon(icon.getImage().getScaledInstance(400, pa60r_hight, Image.SCALE_SMOOTH)));
		add(lblPa60r);
		
		lblPa20b = new JLabel("pa20b");
		icon = new ImageIcon("./icons/PA20B_icon.PNG");
		pa20b_hight = count_resolution(icon.getIconWidth(), icon.getIconHeight(), 400);
		
		lblPa20b.setIcon(new ImageIcon(icon.getImage().getScaledInstance(400, pa20b_hight, Image.SCALE_SMOOTH)));
		add(lblPa20b);
		
		lblPa10b = new JLabel("pa10b");
		icon = new ImageIcon("./icons/PA10B_icon.PNG");
		pa10b_hight = count_resolution(icon.getIconWidth(), icon.getIconHeight(), 400);
		
		lblPa10b.setIcon(new ImageIcon(icon.getImage().getScaledInstance(400,pa10b_hight, Image.SCALE_SMOOTH)));
		add(lblPa10b);
                
                
                lblPa30b = new JLabel("pa30b");
		icon = new ImageIcon("./icons/PA30B_icon.PNG");
		pa30b_hight = count_resolution(icon.getIconWidth(), icon.getIconHeight(), 400);
		
		lblPa30b.setIcon(new ImageIcon(icon.getImage().getScaledInstance(400,pa30b_hight, Image.SCALE_SMOOTH)));
		add(lblPa30b);
                
		
		higth = 1800+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight+pa30b_hight;
		
		this.setBounds(0, 0, 1000, higth);

		lblFS_name = new JLabel("Filling station");
		lblFS_name.setHorizontalAlignment(SwingConstants.CENTER);
		lblFS_name.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		add(lblFS_name);

		
		lblPA30R_name = new JLabel("PA30R station");
		lblPA30R_name.setHorizontalAlignment(SwingConstants.CENTER);
		lblPA30R_name.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		add(lblPA30R_name);

		
		lblPA60R_name = new JLabel("PA60R station");
		lblPA60R_name.setHorizontalAlignment(SwingConstants.CENTER);
		lblPA60R_name.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		add(lblPA60R_name);
		
		lblPA20B_name = new JLabel("PA20B station");
		lblPA20B_name .setHorizontalAlignment(SwingConstants.CENTER);
		lblPA20B_name .setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		add(lblPA20B_name );
		
		lblPA10B_name = new JLabel("PA10B station");
		lblPA10B_name .setHorizontalAlignment(SwingConstants.CENTER);
		lblPA10B_name .setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		add(lblPA10B_name);
                
                lblPA30B_name = new JLabel("PA30B station");
		lblPA30B_name .setHorizontalAlignment(SwingConstants.CENTER);
		lblPA30B_name .setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		add(lblPA30B_name);
		
		
		
		fs_station_state = new JLabel("Station state: Unknown");
		fs_station_state.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fs_station_state.setVerticalAlignment(SwingConstants.TOP);
		add(fs_station_state);
		
		pa30r_station_state = new JLabel("Station state: Unknown");
		pa30r_station_state.setVerticalAlignment(SwingConstants.TOP);
		pa30r_station_state.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa30r_station_state);

		
		pa60r_station_state = new JLabel("Station state: Unknown");
		pa60r_station_state.setVerticalAlignment(SwingConstants.TOP);
		pa60r_station_state.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa60r_station_state);
		
		pa10b_station_state = new JLabel("Station state: Unknown");
		pa10b_station_state.setVerticalAlignment(SwingConstants.TOP);
		pa10b_station_state.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa10b_station_state);
		
                pa30b_station_state = new JLabel("Station state: Unknown");
		pa30b_station_state.setVerticalAlignment(SwingConstants.TOP);
		pa30b_station_state.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa30b_station_state);
                
		pa20b_station_state = new JLabel("Station state: Unknown");
		pa20b_station_state.setVerticalAlignment(SwingConstants.TOP);
		pa20b_station_state.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa20b_station_state);
		
		pa20b_blend_expiration = new JLabel("Next covers expiration: Unknown"); // Ready to remove
		pa20b_blend_expiration.setVerticalAlignment(SwingConstants.TOP);
		pa20b_blend_expiration.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pa20b_blend_expiration.setHorizontalAlignment(SwingConstants.RIGHT);
		add(pa20b_blend_expiration);
		
		
		pa10b_actual_left_cover_code = new JLabel("Current left cover code: Unknown");
		pa10b_actual_left_cover_code.setVerticalAlignment(SwingConstants.TOP);
		pa10b_actual_left_cover_code.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pa10b_actual_left_cover_code.setHorizontalAlignment(SwingConstants.RIGHT);
		add(pa10b_actual_left_cover_code);
		
                pa30b_actual_left_cover_code = new JLabel("Current left cover code: Unknown");
		pa30b_actual_left_cover_code.setVerticalAlignment(SwingConstants.TOP);
		pa30b_actual_left_cover_code.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pa30b_actual_left_cover_code.setHorizontalAlignment(SwingConstants.RIGHT);
		add(pa30b_actual_left_cover_code);
                
		pa30b_actual_right_cover_code = new JLabel("Current right cover code: Unknown");
		pa30b_actual_right_cover_code.setVerticalAlignment(SwingConstants.TOP);
		pa30b_actual_right_cover_code.setFont(new Font("Tahoma", Font.PLAIN, 20));
                pa30b_actual_right_cover_code.setHorizontalAlignment(SwingConstants.RIGHT);
		add(pa30b_actual_right_cover_code);
                
                pa10b_actual_right_cover_code = new JLabel("Current right cover code: Unknown");
		pa10b_actual_right_cover_code.setVerticalAlignment(SwingConstants.TOP);
		pa10b_actual_right_cover_code.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa10b_actual_right_cover_code);
		
		fs_cyclus_state = new JLabel("Work cyclus state: Unknown");
		fs_cyclus_state.setVerticalAlignment(SwingConstants.TOP);
		fs_cyclus_state.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fs_cyclus_state.setHorizontalAlignment(SwingConstants.RIGHT);
		add(fs_cyclus_state);
		
		pa30r_cyclus_state = new JLabel("Work cyclus state: Unknown");
		pa30r_cyclus_state.setVerticalAlignment(SwingConstants.TOP);
		pa30r_cyclus_state.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pa30r_cyclus_state.setHorizontalAlignment(SwingConstants.RIGHT);
		add(pa30r_cyclus_state);

		
		pa60r_cyclus_state = new JLabel("Work cyclus state: Unknown");
		pa60r_cyclus_state.setVerticalAlignment(SwingConstants.TOP);
		pa60r_cyclus_state.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pa60r_cyclus_state.setHorizontalAlignment(SwingConstants.RIGHT);
		add(pa60r_cyclus_state);
		
		pa10b_cyclus_state = new JLabel("Work cyclus state: Unknown");
		pa10b_cyclus_state.setVerticalAlignment(SwingConstants.TOP);
		pa10b_cyclus_state.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pa10b_cyclus_state.setHorizontalAlignment(SwingConstants.RIGHT);
		add(pa10b_cyclus_state);
                
                pa30b_cyclus_state = new JLabel("Work cyclus state: Unknown");
		pa30b_cyclus_state.setVerticalAlignment(SwingConstants.TOP);
		pa30b_cyclus_state.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pa30b_cyclus_state.setHorizontalAlignment(SwingConstants.RIGHT);
		add(pa30b_cyclus_state);
		
		pa30r_home_position = new JLabel("Home position: Unknown");
		pa30r_home_position.setVerticalAlignment(SwingConstants.TOP);
		pa30r_home_position.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa30r_home_position);

		
		pa60r_home_position = new JLabel("Home position: Unknown");
		pa60r_home_position.setVerticalAlignment(SwingConstants.TOP);
		pa60r_home_position.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa60r_home_position);
		
		pa10b_home_position = new JLabel("Home position: Unknown");
		pa10b_home_position.setVerticalAlignment(SwingConstants.TOP);
		pa10b_home_position.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa10b_home_position);

                pa30b_home_position = new JLabel("Robot home position: Unknown");
		pa30b_home_position.setVerticalAlignment(SwingConstants.TOP);
		pa30b_home_position.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa30b_home_position);
                

		
		pa30r_actual_frame_code = new JLabel("Current frame code: Unknown");
		pa30r_actual_frame_code.setVerticalAlignment(SwingConstants.TOP);
		pa30r_actual_frame_code.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pa30r_actual_frame_code.setHorizontalAlignment(SwingConstants.RIGHT);
		add(pa30r_actual_frame_code);
                
                pa30b_actual_frame_code = new JLabel("Current frame code: Unknown");
		pa30b_actual_frame_code.setVerticalAlignment(SwingConstants.TOP);
		pa30b_actual_frame_code.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pa30b_actual_frame_code.setHorizontalAlignment(SwingConstants.LEFT);
		add(pa30b_actual_frame_code);
		

		
		pa60r_actual_frame_code = new JLabel("Current frame code: Unknown");
		pa60r_actual_frame_code.setVerticalAlignment(SwingConstants.TOP);
		pa60r_actual_frame_code.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pa60r_actual_frame_code.setHorizontalAlignment(SwingConstants.RIGHT);
		add(pa60r_actual_frame_code);
		
		pa30r_station_configuration = new JLabel("Current station configuration: Unknown");
		pa30r_station_configuration.setVerticalAlignment(SwingConstants.TOP);
		pa30r_station_configuration.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa30r_station_configuration);
		
		
		pa60r_station_configuration = new JLabel("Current station configuration: Unknown");
		pa60r_station_configuration.setVerticalAlignment(SwingConstants.TOP);
		pa60r_station_configuration.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(pa60r_station_configuration);
		
		shaker_1_expiration = new JLabel("Bottle in shaker 1: Unknown");
		shaker_1_expiration.setVerticalAlignment(SwingConstants.TOP);
		shaker_1_expiration.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(shaker_1_expiration);
		
		shaker_2_expiration = new JLabel("Bottle in shaker 2: Unknown");
		shaker_2_expiration.setVerticalAlignment(SwingConstants.TOP);
		shaker_2_expiration.setHorizontalAlignment(SwingConstants.RIGHT);
		shaker_2_expiration.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(shaker_2_expiration);
		
		shaker_3_expiration = new JLabel("Bottle in shaker 3: Unknown");
		shaker_3_expiration.setVerticalAlignment(SwingConstants.TOP);
		shaker_3_expiration.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(shaker_3_expiration);
		
		shaker_4_expiration = new JLabel("Bottle in shaker 4: Unknown");
		shaker_4_expiration.setVerticalAlignment(SwingConstants.TOP);
		shaker_4_expiration.setFont(new Font("Tahoma", Font.PLAIN, 20));
		shaker_4_expiration.setHorizontalAlignment(SwingConstants.RIGHT);
		add(shaker_4_expiration);
		
		
		events();
	}

	
	public void setWidth(int width)
	{
		this.setPreferredSize(new Dimension(width, higth));
	}
	
	private void events()
	{
		addComponentListener(new ComponentListener() 
		{

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
                                lblPa30b.setBounds(getWidth()/2-200, 1600+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight, 400,pa30b_hight);
				lblPa10b.setBounds(getWidth()/2-200, 1400+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight, 400,pa10b_hight);
				lblPa20b.setBounds(getWidth()/2-200, 1200+fs_hight+pa30r_hight+pa60r_hight,400,pa20b_hight);
				lblPa60r.setBounds(getWidth()/2-200, 1000+fs_hight+pa30r_hight, 400, pa60r_hight);
				lblPa30r.setBounds(getWidth()/2-200, 600+fs_hight, 400, pa30r_hight);
				lblFs.setBounds(getWidth()/2-200, 200,400,fs_hight);	
				
				
				lblFS_name.setBounds(getWidth()/2-200, 130, 400, 50);
                                lblPA30B_name.setBounds(getWidth()/2-200, 1530+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight, 400, 50);
				lblPA10B_name.setBounds(getWidth()/2-200, 1330+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight, 400, 50);
				lblPA20B_name.setBounds(getWidth()/2-200, 1130+fs_hight+pa30r_hight+pa60r_hight, 400, 50);
				lblPA60R_name.setBounds(getWidth()/2-200, 930+fs_hight+pa30r_hight, 400, 50);
				lblPA30R_name.setBounds(getWidth()/2-200, 530+fs_hight, 400, 50);
				
				
				fs_station_state.setBounds(getWidth()/2+200, 250, 400, 70);
				pa30r_station_state.setBounds(getWidth()/2+200, 650+fs_hight, 400, 70);
				pa60r_station_state.setBounds(getWidth()/2+200, 1050+fs_hight+pa30r_hight, 400, 70);
				pa20b_station_state.setBounds(getWidth()/2+200, 1250+fs_hight+pa30r_hight+pa60r_hight, 400, 70);
				pa10b_station_state.setBounds(getWidth()/2+200, 1450+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight, 400, 70);
				pa30b_station_state.setBounds(getWidth()/2+200, 1650+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight, 400, 70);
                                
                                
				fs_cyclus_state.setBounds(getWidth()/2-200-400, 250+fs_hight/6, 400, 70);
				pa30r_cyclus_state.setBounds(getWidth()/2-200-400, 650+fs_hight+pa30r_hight/5, 400, 70);
				pa60r_cyclus_state.setBounds(getWidth()/2-200-400, 1050+fs_hight+pa30r_hight+pa60r_hight/5, 400, 70);
				pa20b_blend_expiration.setBounds(getWidth()/2-200-400, 1250+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight/2, 400, 70);
				pa10b_cyclus_state.setBounds(getWidth()/2-200-400, 1450+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight/5, 400, 70);
                                pa30b_cyclus_state.setBounds(getWidth()/2-200-400, 1650+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight+pa30b_hight/5, 400, 70);
				
				shaker_1_expiration.setBounds(getWidth()/2+200, 250+fs_hight/6*2, 400, 70);
				pa30r_home_position.setBounds(getWidth()/2+200,650+fs_hight+pa30r_hight/5*2, 400, 70);
				pa60r_home_position.setBounds(getWidth()/2+200,1050+fs_hight+pa30r_hight+pa60r_hight/5*2, 400, 70);
				pa10b_home_position.setBounds(getWidth()/2+200, 1450+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight/5*2, 400, 70);
				pa30b_home_position.setBounds(getWidth()/2+200, 1650+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight+pa30b_hight/5*2, 400, 70);
                                
                                
				shaker_2_expiration.setBounds(getWidth()/2-200-400, 250+fs_hight/6*3, 400, 70);
				pa30r_actual_frame_code.setBounds(getWidth()/2-200-400, 650+fs_hight+pa30r_hight/5*3, 400, 70);
				pa60r_actual_frame_code.setBounds(getWidth()/2-200-400, 1050+fs_hight+pa30r_hight+pa60r_hight/5*3, 400, 70);
				pa10b_actual_left_cover_code.setBounds(getWidth()/2-200-400, 1450+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight/5*3, 400, 70);
				pa30b_actual_left_cover_code.setBounds(getWidth()/2-200-400, 1650+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight+pa30b_hight/5*3, 400, 70);
				
                                
                                
                                pa30b_actual_frame_code.setBounds(getWidth()/2+200, 1650+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight+pa30b_hight/5*4, 400, 70);
                                
				pa10b_actual_right_cover_code.setBounds(getWidth()/2+200, 1450+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight/5*4, 400, 70);
				
                                pa30b_actual_right_cover_code.setBounds(getWidth()/2-200-400, 1650+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight+pa30b_hight/5*3+85, 400, 70);
                                
				shaker_3_expiration.setBounds(getWidth()/2+200, 250+fs_hight/6*4, 400, 70);
				
				pa30r_station_configuration.setBounds(getWidth()/2+200,650+fs_hight+pa30r_hight/5*4, 400, 70);
				pa60r_station_configuration.setBounds(getWidth()/2+200,1050+fs_hight+pa30r_hight+pa60r_hight/5*4, 400, 70);
				
				shaker_4_expiration.setBounds(getWidth()/2-200-400, 250+fs_hight/6*5, 400, 70);
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	
	}
	
	 @Override
	    public void paintComponent(Graphics g) 
	    {
	        super.paintComponent(g);
	       
	       if (fs_station_state.getText().equals("Station state: OK"))
	       {
	    	   g.setColor(Color.GREEN);
	       }
	       else
	       {
	    	   g.setColor(Color.RED);
	       }
	       
	       g.fillRect(getWidth()/2-200, 190, 400, 10);
	       
	       
	       if (pa30r_station_state.getText().equals("Station state: OK"))
	       {
	    	   g.setColor(Color.GREEN);
	       }
	       else
	       {
	    	   g.setColor(Color.RED);
	       }
	       g.fillRect(getWidth()/2-200, 590+fs_hight, 400, 10);

	       if (pa60r_station_state.getText().equals("Station state: OK"))
	       {
	    	   g.setColor(Color.GREEN);
	       }
	       else
	       {
	    	   g.setColor(Color.RED);
	       }
	       g.fillRect(getWidth()/2-200, 990+fs_hight+pa30r_hight, 400, 10);
	       
	       if (pa20b_station_state.getText().equals("Station state: Active"))
	       {
	    	   g.setColor(Color.GREEN);
	       }
	       else
	       {
	    	   g.setColor(Color.RED);
	       }
	       g.fillRect(getWidth()/2-200, 1190+fs_hight+pa30r_hight+pa60r_hight, 400, 10);
	       
	       if (pa10b_station_state.getText().equals("Station state: OK"))
	       {
	    	   g.setColor(Color.GREEN);
	       }
	       else
	       {
	    	   g.setColor(Color.RED);
	       }
	       g.fillRect(getWidth()/2-200, 1390+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight, 400, 10);
               
               if (pa30b_station_state.getText().equals("Station state: OK"))
	       {
	    	   g.setColor(Color.GREEN);
	       }
	       else
	       {
	    	   g.setColor(Color.RED);
	       }
	       g.fillRect(getWidth()/2-200, 1590+fs_hight+pa30r_hight+pa60r_hight+pa20b_hight+pa10b_hight, 400, 10);
               
	        
	    }
	
	 public void fs_set_station_labels(String station_state, String work_cyclus_state, String shaker_1_time_expiration, String shaker_2_time_expiration,String shaker_3_time_expiration, String shaker_4_time_expiration)
	 {
		 if (!work_cyclus_state.equals("") && work_cyclus_state != null)
		 {
			 this.fs_cyclus_state.setText(work_cyclus_state);
		 }
		 
		 if(!station_state.equals("") && station_state != null)
		 {
			 this.fs_station_state.setText(station_state);
		 }
		 
		 if(!shaker_1_time_expiration.equals("") && shaker_1_time_expiration != null)
		 {
			 this.shaker_1_expiration.setText(shaker_1_time_expiration);
		 }
		 
		 if(!shaker_2_time_expiration.equals("") && shaker_2_time_expiration != null)
		 {
			 this.shaker_2_expiration.setText(shaker_2_time_expiration);
		 }
		 
		 if(!shaker_3_time_expiration.equals("") && shaker_3_time_expiration != null)
		 {
			 this.shaker_3_expiration.setText(shaker_3_time_expiration);
		 }
		 
		 if(!shaker_4_time_expiration.equals("") && shaker_4_time_expiration != null)
		 {
			 this.shaker_4_expiration.setText(shaker_4_time_expiration);
		 }
		 
		 revalidate();
		 repaint();
	 }
	 
	 
	 public void pa30r_set_station_labels(String station_state, String work_cyclus_state, String home_position, String current_frame_code, String station_configuration)
	 {
		 if(!station_state.equals("") && station_state != null)
		 {
			 this.pa30r_station_state.setText(station_state);
		 }
		 
		 if(!work_cyclus_state.equals("") && work_cyclus_state != null)
		 {
			 this.pa30r_cyclus_state.setText(work_cyclus_state);
		 }
		 
		 if(!home_position.equals("") && home_position != null)
		 {
			 this.pa30r_home_position.setText(home_position);
		 }
		 
		 if(!current_frame_code.equals("") && current_frame_code != null)
		 {
			 this.pa30r_actual_frame_code.setText(current_frame_code);
		 }
		 
		 if(!station_configuration.equals("") && station_configuration != null)
		 {
			 this.pa30r_station_configuration.setText(station_configuration);
		 }
		 
		 revalidate();
		 repaint();
	 }
	 
	 public void pa60r_set_station_labels(String station_state, String work_cyclus_state, String home_position, String current_frame_code, String station_configuration)
	 {
		 if(!station_state.equals("") && station_state != null)
		 {
			 pa60r_station_state.setText(station_state);
		 }
		 
		 if(!work_cyclus_state.equals("") && work_cyclus_state != null)
		 {
			 this.pa60r_cyclus_state.setText(work_cyclus_state);
		 }
		 
		 if(!home_position.equals("") && home_position != null)
		 {
			 this.pa60r_home_position.setText(home_position);
		 }
		 
		 if(!current_frame_code.equals("") && current_frame_code != null)
		 {
			 this.pa60r_actual_frame_code.setText(current_frame_code);
		 }
		 
		 if(!station_configuration.equals("") && station_configuration != null)
		 {
			 this.pa60r_station_configuration.setText(station_configuration);
		 }
		 
		 revalidate();
		 repaint();
	 }
	 
	 public void pa20b_set_station_labels(String station_state, String next_cover_expiration)
	 {
		 if(!station_state.equals("") && station_state != null)
		 {
			 this.pa20b_station_state.setText(station_state);
		 }
		 
		 if(!next_cover_expiration.equals("") && next_cover_expiration != null)
		 {
			 this.pa20b_blend_expiration.setText(next_cover_expiration);
		 }
		 
		 revalidate();
		 repaint();
	 }
	 
	 public void pa10b_set_station_labels(String station_state, String work_cyclus_state, String home_position, String left_cover_code, String right_cover_code)
	 {
		 if(!station_state.equals("") && station_state != null)
		 {
			 this.pa10b_station_state.setText(station_state);
		 }
		 
		 if(!work_cyclus_state.equals("") && work_cyclus_state != null)
		 {
			 this.pa10b_cyclus_state.setText(work_cyclus_state);
		 }
		 
		 if(!home_position.equals("") && home_position != null)
		 {
			 this.pa10b_home_position.setText(home_position);
		 }
		 
		 if(!left_cover_code.equals("") && left_cover_code != null)
		 {
			 this.pa10b_actual_left_cover_code.setText(left_cover_code);
		 }
		 
		 if(!right_cover_code.equals("") && right_cover_code != null)
		 {
			 this.pa10b_actual_right_cover_code.setText(right_cover_code);
		 }
		 
		 revalidate();
		 repaint();
	 }
         
         
         
         public void pa30b_set_station_labels(String station_state, String work_cyclus_state, String home_position, String left_cover_code, String right_cover_code, String current_frame_code)
	 {
		 if(!station_state.equals("") && station_state != null)
		 {
			 this.pa30b_station_state.setText(station_state);
		 }
		 
		 if(!work_cyclus_state.equals("") && work_cyclus_state != null)
		 {
			 this.pa30b_cyclus_state.setText(work_cyclus_state);
		 }
		 
		 if(!home_position.equals("") && home_position != null)
		 {
			 this.pa30b_home_position.setText(home_position);
		 }
		 
		 if(!left_cover_code.equals("") && left_cover_code != null)
		 {
			 this.pa30b_actual_left_cover_code.setText(left_cover_code);
		 }
		 
		 if(!right_cover_code.equals("") && right_cover_code != null)
		 {
			 this.pa30b_actual_right_cover_code.setText(right_cover_code);
		 }
                 
                 if(!current_frame_code.equals("") && current_frame_code != null)
		 {
			 this.pa30b_actual_frame_code.setText(current_frame_code);
		 }
		 
		 revalidate();
		 repaint();
	 }
         
}
