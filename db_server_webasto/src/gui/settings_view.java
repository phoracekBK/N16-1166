package gui;

import database.csv_backup_synchronizer;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import database.database_interface;
import db_server_webasto.db_server_webasto_main;
import server.server_thread;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;

public class settings_view extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private final JTextField portNumberInputPLC1;
	private final JLabel lblErrorMessage;
	private final JLabel lblServersetting;
	private final JLabel lblUsermanagement;
	private final JLabel lblExportdatabse;
	private final JLabel lblImportdatabase;
	private final JPanel user_management_panel;
	private final JPanel back_up_panel;
	private final JPanel restore_panel;
	private final JPanel control_panel;
	private final JPanel server_setting_panel;
	private final JList<String> user_list;
	private final JTextField user_name_input;
	private final JPasswordField passwordField;
	private final JButton btnAdd;
	private final JButton btnCreate;
	private final JLabel lblPassword;
	private final JLabel lblUserName;	
	private final JLabel lblTcpPort;
	private final JButton btnDeleteUser;	
	private final JButton btnCancel;
	private final JLabel lblCreateNewAccount;
	private final JLabel lblSwitch;
        private final JLabel lblDbFileBackupAddress;
        private final JTextField textDbFileBackupAddress;
        private final JButton btnBrowseDbBackupAddress;
	private boolean server_state;
	private final JTextField text_output_file;
	private final JButton browse_output_address;
	private final JButton btnBackupDatabase;
	private final JButton browse_input_address;
	private final JTextField text_input_file;
	private final JButton restore;
	private final JLabel lblNewLabel;
	private final JLabel lblUserManagement;
	private final JLabel lblDataAndSettings;
	private final JLabel lblOutputFileAddress;
	private final JLabel lblInputFileAddress;
	private final JLabel lblDataAndSettings_1;
	private final JPanel user_add_card;
	private final JPanel panel;
	private final JTextField portNumberInputPLC2;
	private final JLabel lblPlcTcpPort;
	private final JLabel lblBadTcpPort;
	private final server_thread plc_1;
	private final server_thread plc_2;
	private final database_interface db_reference;
	private final JPanel blank;
	private final JPanel manage;
	private final JLabel lbl_user_name;
	private final JButton btnNewButton;
	private final JPanel change_password;
	private final JPasswordField actual_password_input;
	private final JPasswordField new_password_input;
	private final JPasswordField new_password_confirm_input;
	private final JButton passwd_cancel;
	private final JButton btn_change_password_ok;
	private final JLabel lblLogout;
	private final login_window login;
        private final JTextField output_csv_address;
        private final JButton csv_address_browse_btn;
        private final csv_backup_synchronizer csv_synchronizer;
        
	
	public settings_view(server_thread plc_1, server_thread plc_2, database_interface db_reference,login_window login) 
	{
		this.db_reference = db_reference;
		this.login = login;

		setLayout(null);
		
		control_panel = new JPanel();
		control_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		control_panel.setBounds(30, 140, 660, 600);
		add(control_panel);
		control_panel.setLayout(new CardLayout(0, 0));
		
		server_setting_panel = new JPanel();
		server_setting_panel.setVisible(false);
		control_panel.add(server_setting_panel, "server_setting_card");
		server_setting_panel.setLayout(null);
		
		portNumberInputPLC1 = new JTextField();
		portNumberInputPLC1.setBounds(175, 80, 228, 22);
		server_setting_panel.add(portNumberInputPLC1);
		portNumberInputPLC1.setColumns(10);
		
		lblTcpPort = new JLabel("TCP port PLC_1:");
                lblTcpPort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTcpPort.setBounds(40, 83, 120, 16);
		server_setting_panel.add(lblTcpPort);
		
		lblErrorMessage = new JLabel("Bad TCP port number!");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(500, 83, 163, 16);
		server_setting_panel.add(lblErrorMessage);
		
                output_csv_address = new JTextField("");
                output_csv_address.setBounds(175, 175, 228, 22);
		server_setting_panel.add(output_csv_address);
 
                lblDbFileBackupAddress = new JLabel("Database backup address:");
                lblDbFileBackupAddress.setBounds(12, 220, 228, 22);
		server_setting_panel.add(lblDbFileBackupAddress);
                
                textDbFileBackupAddress = new JTextField("");
                textDbFileBackupAddress.setBounds(175, 220, 228, 22);
		server_setting_panel.add(textDbFileBackupAddress);
                
                btnBrowseDbBackupAddress = new JButton("Browse");
                btnBrowseDbBackupAddress.setBounds(450, 220, 90, 22);
		server_setting_panel.add(btnBrowseDbBackupAddress);
                
                csv_address_browse_btn = new JButton("Browse");
                csv_address_browse_btn.setBounds(450, 175, 90, 22);
                server_setting_panel.add(csv_address_browse_btn);
                
                JLabel lbl_csv_address = new JLabel("CSV output address:");
                lbl_csv_address.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_csv_address.setBounds(40, 175, 120, 16);
		server_setting_panel.add(lbl_csv_address);
                
		lblSwitch = new JLabel("switch");
		lblSwitch.setBounds(175, 270, 80, 35);
		server_setting_panel.add(lblSwitch);
		
		lblNewLabel = new JLabel("Server setting");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(175, 26, 233, 27);
		server_setting_panel.add(lblNewLabel);
		
		portNumberInputPLC2 = new JTextField();
		portNumberInputPLC2.setColumns(10);
		portNumberInputPLC2.setBounds(175, 128, 228, 22);
		server_setting_panel.add(portNumberInputPLC2);
		
		lblPlcTcpPort = new JLabel("TCP port PLC_2:");
		lblPlcTcpPort.setBounds(40, 134, 120, 16);
                lblPlcTcpPort.setHorizontalAlignment(SwingConstants.RIGHT);
		server_setting_panel.add(lblPlcTcpPort);
		
		lblBadTcpPort = new JLabel("Bad TCP port number!");
		lblBadTcpPort.setVisible(false);
		lblBadTcpPort.setForeground(Color.RED);
		lblBadTcpPort.setBounds(393, 131, 136, 16);
		server_setting_panel.add(lblBadTcpPort);
		
		user_management_panel = new JPanel();
		user_management_panel.setVisible(false);
		control_panel.add(user_management_panel, "user_management_panel");
		user_management_panel.setLayout(null);
		
		user_list = new JList<String>();
		
		user_list.setBounds(25, 59, 200, 466);
		user_management_panel.add(user_list);
		
		btnAdd = new JButton("Add");
		
		btnAdd.setBounds(25, 540, 97, 25);
		user_management_panel.add(btnAdd);
		
		lblUserManagement = new JLabel("User management");
		lblUserManagement.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUserManagement.setBounds(30, 20, 200, 33);
		user_management_panel.add(lblUserManagement);
		
		panel = new JPanel();
		panel.setBounds(261, 59, 356, 466);
		user_management_panel.add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		user_add_card = new JPanel();
		user_add_card.setVisible(false);
		panel.add(user_add_card, "user_add_card");
		user_add_card.setLayout(null);
		
		change_password = new JPanel();
		change_password.setVisible(false);
		panel.add(change_password, "passwd_card");
		change_password.setLayout(null);
		
		actual_password_input = new JPasswordField();
		actual_password_input.setBounds(25, 50, 225, 25);
		change_password.add(actual_password_input);
		
		new_password_input = new JPasswordField();
		new_password_input.setBounds(25, 110, 225, 25);
		change_password.add(new_password_input);
		
		new_password_confirm_input = new JPasswordField();
		new_password_confirm_input.setBounds(25, 170, 225, 25);
		change_password.add(new_password_confirm_input);
		
		JLabel lblActualPassword = new JLabel("Current password:");
		lblActualPassword.setBounds(25, 25, 225, 20);
		change_password.add(lblActualPassword);
		
		JLabel lblNewPassword = new JLabel("New password:");
		lblNewPassword.setBounds(25, 85, 225, 20);
		change_password.add(lblNewPassword);
		
		JLabel lblNewLabel_1 = new JLabel("Confirmation:");
		lblNewLabel_1.setBounds(25, 145, 225, 20);
		change_password.add(lblNewLabel_1);
		
		btn_change_password_ok = new JButton("OK");
		
		btn_change_password_ok.setBounds(25, 215, 97, 25);
		change_password.add(btn_change_password_ok);
		
		passwd_cancel = new JButton("Cancel");
		
		passwd_cancel.setBounds(153, 215, 97, 25);
		change_password.add(passwd_cancel);
		
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(211, 138, 97, 25);
		user_add_card.add(btnCancel);
		
		btnCreate = new JButton("Create");
		btnCreate.setBounds(98, 138, 97, 25);
		user_add_card.add(btnCreate);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 98, 210, 22);
		user_add_card.add(passwordField);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(-12, 98, 95, 16);
		user_add_card.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		
		lblUserName = new JLabel("User name:");
		lblUserName.setBounds(-22, 58, 105, 16);
		user_add_card.add(lblUserName);
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		user_name_input = new JTextField();
		user_name_input.setBounds(97, 58, 211, 22);
		user_add_card.add(user_name_input);
		user_name_input.setColumns(10);
		
		lblCreateNewAccount = new JLabel("Create new account");
		lblCreateNewAccount.setBounds(98, 13, 194, 26);
		user_add_card.add(lblCreateNewAccount);
		lblCreateNewAccount.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		blank = new JPanel();
		blank.setVisible(false);
		panel.add(blank, "blank_card");
		
		back_up_panel = new JPanel();
		back_up_panel.setVisible(false);
		control_panel.add(back_up_panel, "backup_panel");
		back_up_panel.setLayout(null);
		
		text_output_file = new JTextField();
		text_output_file.setBounds(150, 110, 300, 25);
		back_up_panel.add(text_output_file);
		text_output_file.setColumns(50);
		
		browse_output_address = new JButton("Browse");
		
		browse_output_address.setBounds(40, 110, 100, 25);
		back_up_panel.add(browse_output_address);
		
		btnBackupDatabase = new JButton("Back up");
		
		btnBackupDatabase.setBounds(150, 150, 100, 25);
		back_up_panel.add(btnBackupDatabase);
		
		lblDataAndSettings = new JLabel("Data and settings backup");
		lblDataAndSettings.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDataAndSettings.setBounds(150, 25, 300, 35);
		back_up_panel.add(lblDataAndSettings);
		
		lblOutputFileAddress = new JLabel("Output file address:");
		lblOutputFileAddress.setBounds(150, 85, 135, 16);
		back_up_panel.add(lblOutputFileAddress);
		
		restore_panel = new JPanel();
		restore_panel.setVisible(false);
		control_panel.add(restore_panel, "restor_panel");
		restore_panel.setLayout(null);
		
		browse_input_address = new JButton("Browse");
		
		browse_input_address.setBounds(40, 110, 100, 25);
		restore_panel.add(browse_input_address);
		
		text_input_file = new JTextField();
		text_input_file.setColumns(50);
		text_input_file.setBounds(150, 110, 300, 25);
		restore_panel.add(text_input_file);
		
		restore = new JButton("Restore");
		
		restore.setBounds(150, 150, 100, 25);
		restore_panel.add(restore);
		
		lblInputFileAddress = new JLabel("Input file address:");
		lblInputFileAddress.setBounds(150, 85, 300, 16);
		restore_panel.add(lblInputFileAddress);
		
		lblDataAndSettings_1 = new JLabel("Data and settings restore");
		lblDataAndSettings_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDataAndSettings_1.setBounds(150, 25, 300, 35);
		restore_panel.add(lblDataAndSettings_1);
		
		lblServersetting = new JLabel("server_setting");
		lblServersetting.setBounds(40, 43, 87, 65);
		lblServersetting.setIcon(new ImageIcon(new ImageIcon("./icons/server_setting_icon.png").getImage().getScaledInstance(87, 65, Image.SCALE_SMOOTH)));
		add(lblServersetting);
		
		lblUsermanagement = new JLabel("user_management");
		lblUsermanagement.setBounds(175, 42, 108, 68);
		lblUsermanagement.setIcon(new ImageIcon(new ImageIcon("./icons/user_management_icon.png").getImage().getScaledInstance(108,68, Image.SCALE_SMOOTH)));
		add(lblUsermanagement);
		
		lblExportdatabse = new JLabel("export_databse");
		lblExportdatabse.setBounds(330, 40, 100, 70);
		lblExportdatabse.setIcon(new ImageIcon(new ImageIcon("./icons/export_database_icon.png").getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH)));
		add(lblExportdatabse);
		
		lblImportdatabase = new JLabel("import_database");
		lblImportdatabase.setBounds(475, 40, 105, 70);
		lblImportdatabase.setIcon(new ImageIcon(new ImageIcon("./icons/import_database_icon.png").getImage().getScaledInstance(105, 70, Image.SCALE_SMOOTH)));
		add(lblImportdatabase);
		lblErrorMessage.setVisible(false);

		manage = new JPanel();
		panel.add(manage, "manage_card");
		manage.setLayout(null);
		
		btnDeleteUser = new JButton("Delete");
		btnDeleteUser.setBounds(183, 77, 84, 25);
		manage.add(btnDeleteUser);
		
		lbl_user_name = new JLabel("User account: ");
		lbl_user_name.setFont(new Font("Tahoma", Font.BOLD, 17));
		lbl_user_name.setBounds(12, 13, 290, 39);
		manage.add(lbl_user_name);
		
		btnNewButton = new JButton("Change password");
		
		btnNewButton.setBounds(12, 77, 150, 25);
		manage.add(btnNewButton);
		
		lblLogout = new JLabel("logout");
		
		lblLogout.setBounds(630, 40, 51, 70);
		lblLogout.setIcon(new ImageIcon(new ImageIcon("./icons/logout_icon.png").getImage().getScaledInstance(51, 70, Image.SCALE_SMOOTH)));
		add(lblLogout);
		
		((CardLayout)control_panel.getLayout()).show(control_panel, "server_setting_card");
		events();
		((CardLayout)panel.getLayout()).show(panel, "blank_card");

                /* load configuration */
                portNumberInputPLC1.setText(db_reference.get_record_item("settings", "network", "port_plc_1", "ID"));
                portNumberInputPLC2.setText(db_reference.get_record_item("settings", "network", "port_plc_2", "ID"));
                output_csv_address.setText(db_reference.get_record_item("settings", "network", "csv_output_address", "ID"));
                textDbFileBackupAddress.setText(db_reference.get_record_item("settings", "network", "db_file_backup_address", "ID"));
                
                db_reference.set_csv_address(output_csv_address.getText());
                csv_synchronizer = new csv_backup_synchronizer();
                csv_synchronizer.set_output_address(output_csv_address.getText());
                csv_synchronizer.start();
                
                /* init network interface */
		this.plc_1 = plc_1;
		this.plc_2 = plc_2;
		
		plc_1.get_server_reference().server_init(Integer.parseInt(portNumberInputPLC1.getText()));
		plc_1.server_start();
		 
		plc_2.get_server_reference().server_init(Integer.parseInt(portNumberInputPLC2.getText()));
		plc_2.server_start();
                
                /* run network */
		server_state = true;
		portNumberInputPLC1.setEnabled(false);
		portNumberInputPLC2.setEnabled(false);
                csv_address_browse_btn.setEnabled(false);
                output_csv_address.setEnabled(false);
                textDbFileBackupAddress.setEnabled(false);
                btnBrowseDbBackupAddress.setEnabled(false);

		lblSwitch.setIcon(new ImageIcon(new ImageIcon("./icons/switch_button_on_icon.png").getImage().getScaledInstance(80, 34, Image.SCALE_SMOOTH)));
                
	}
	
    private String set_address(String button_text, String default_file_name, String output_file_format)
    {
        JFileChooser chooser = new JFileChooser();
        
        if(output_file_format != null)
        {
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("*."+output_file_format, output_file_format));
        }
        else
        {
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }
        
        chooser.setApproveButtonText(button_text);
        
        if(default_file_name != null)
        {
            chooser.setSelectedFile(new File(default_file_name));  
        }
        
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Browse the folder to process.");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
        {
            return chooser.getSelectedFile().toString();
        }
        else
        {
            return "";
        }
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				control_panel.setBounds(30, 140, 660, getHeight()-150);
				user_list.setBounds(25, 70, 200, getHeight()-300);
				btnAdd.setBounds(25, getHeight()-210, 90, 25);
				//btnDeleteUser.setBounds(25, getHeight()-210, 95, 25);
				revalidate();
				repaint();
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		lblSwitch.addMouseListener(new MouseAdapter()
                {
                     @Override
	    	 public void mouseEntered(MouseEvent evt)
	         {
	    		 if(server_state == true)
	        	 {
	        		 lblSwitch.setIcon(new ImageIcon(new ImageIcon("./icons/switch_button_on_focus_icon.png").getImage().getScaledInstance(80, 34, Image.SCALE_SMOOTH)));
	        	 }
	        	 else
	        	 {
	        		 lblSwitch.setIcon(new ImageIcon(new ImageIcon("./icons/switch_button_off_focus_icon.png").getImage().getScaledInstance(80, 34, Image.SCALE_SMOOTH)));
	        	 }
	         }
                     @Override
	         public void mouseExited(MouseEvent evt)
	         {
	        	 if(server_state == true)
	        	 {
	        		 lblSwitch.setIcon(new ImageIcon(new ImageIcon("./icons/switch_button_on_icon.png").getImage().getScaledInstance(80, 34, Image.SCALE_SMOOTH)));
	        	 }
	        	 else
	        	 {
	        		 lblSwitch.setIcon(new ImageIcon(new ImageIcon("./icons/switch_button_off_icon.png").getImage().getScaledInstance(80, 34, Image.SCALE_SMOOTH)));
	        	 }
	         }
                     @Override
	         public void mousePressed(MouseEvent evt)
	         {
	        	 if(server_state == true)
	        	 {
                             lblSwitch.setIcon(new ImageIcon(new ImageIcon("./icons/switch_button_off_icon.png").getImage().getScaledInstance(80, 34, Image.SCALE_SMOOTH)));
                             server_state = false;
                             portNumberInputPLC2.setEnabled(true);
                             portNumberInputPLC1.setEnabled(true);
                             csv_address_browse_btn.setEnabled(true);
                             output_csv_address.setEnabled(true);
                             textDbFileBackupAddress.setEnabled(true);
                             btnBrowseDbBackupAddress.setEnabled(true);
                             plc_1.server_stop();
                             plc_2.server_stop();
	        	 }
	        	 else
	        	 {
                            if(!portNumberInputPLC1.getText().equals(portNumberInputPLC2.getText()))
                            {
                               server_state = true;
                               portNumberInputPLC1.setEnabled(false);
                               portNumberInputPLC2.setEnabled(false);
                               csv_address_browse_btn.setEnabled(false);
                               output_csv_address.setEnabled(false);
                               textDbFileBackupAddress.setEnabled(false);
                               btnBrowseDbBackupAddress.setEnabled(false);
                               
                               db_reference.set_record_item("settings", "ID", "network", "port_plc_1", portNumberInputPLC1.getText());
                               db_reference.set_record_item("settings", "ID", "network", "port_plc_2", portNumberInputPLC2.getText());
                               db_reference.set_record_item("settings", "ID", "network", "csv_output_address", output_csv_address.getText());
                               db_reference.set_record_item("settings", "ID", "network", "db_file_backup_address", textDbFileBackupAddress.getText());
                        
                               db_reference.set_csv_address(output_csv_address.getText());
                               csv_synchronizer.set_output_address(output_csv_address.getText());
                               
                               plc_1.get_server_reference().server_init(Integer.parseInt(portNumberInputPLC1.getText()));
                               plc_1.server_start();
                               
                               plc_2.get_server_reference().server_init(Integer.parseInt(portNumberInputPLC2.getText()));
                               plc_2.server_start();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(
                                                           null, 
                                                           "TCP ports of PLC_1 and PLC_2 are the same!", 
                                                           "Error",
                                                           JOptionPane.ERROR_MESSAGE, 
                                                           null); 
                            }
                            
                            lblSwitch.setIcon(new ImageIcon(new ImageIcon("./icons/switch_button_on_icon.png").getImage().getScaledInstance(80, 34, Image.SCALE_SMOOTH)));	 
	        	 }
	         }
                     @Override
	         public void mouseReleased(MouseEvent evt)
	         {
	        	 if(server_state == true)
	        	 {
	        		 lblSwitch.setIcon(new ImageIcon(new ImageIcon("./icons/switch_button_on_focus_icon.png").getImage().getScaledInstance(80, 34, Image.SCALE_SMOOTH)));
	        	 }
	        	 else
	        	 {
	        		 lblSwitch.setIcon(new ImageIcon(new ImageIcon("./icons/switch_button_off_focus_icon.png").getImage().getScaledInstance(80, 34, Image.SCALE_SMOOTH)));
	        	 }
	         }
        });
		
		portNumberInputPLC1.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				int i = 0;
				String tempString = "";
				while(i<portNumberInputPLC1.getText().length() && i <5)
				{
					if(portNumberInputPLC1.getText().charAt(i) >47 && portNumberInputPLC1.getText().charAt(i) <58)
					tempString += portNumberInputPLC1.getText().charAt(i);
					i++;
				}
				portNumberInputPLC1.setText(tempString);
			}
		});
		
		portNumberInputPLC2.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				int i = 0;
				String tempString = "";
				while(i<portNumberInputPLC1.getText().length() && i <5)
				{
					if(portNumberInputPLC1.getText().charAt(i) >47 && portNumberInputPLC1.getText().charAt(i) <58)
					tempString += portNumberInputPLC1.getText().charAt(i);
					i++;
				}
				portNumberInputPLC1.setText(tempString);
			}
		});
		
		lblServersetting.addMouseListener(new MouseAdapter()
        {
                     @Override
	    	 public void mouseEntered(MouseEvent evt)
	         {
	    		 lblServersetting.setIcon(new ImageIcon(new ImageIcon("./icons/server_setting_focus_icon.png").getImage().getScaledInstance(87, 65, Image.SCALE_SMOOTH)));
	         }
                     @Override
	         public void mouseExited(MouseEvent evt)
	         {
	        	 lblServersetting.setIcon(new ImageIcon(new ImageIcon("./icons/server_setting_icon.png").getImage().getScaledInstance(87, 65, Image.SCALE_SMOOTH)));
	         }
                     @Override
	         public void mousePressed(MouseEvent evt)
	         {
	        	 lblServersetting.setIcon(new ImageIcon(new ImageIcon("./icons/server_setting_icon.png").getImage().getScaledInstance(87, 65, Image.SCALE_SMOOTH)));
	        	 ((CardLayout)control_panel.getLayout()).show(control_panel, "server_setting_card");
	        	 server_setting_panel.setVisible(true);
	        	 user_management_panel.setVisible(false);
	        	 back_up_panel.setVisible(false);
	        	 restore_panel.setVisible(false);
	         }
                     @Override
	         public void mouseReleased(MouseEvent evt)
	         {
	        	 lblServersetting.setIcon(new ImageIcon(new ImageIcon("./icons/server_setting_focus_icon.png").getImage().getScaledInstance(87, 65, Image.SCALE_SMOOTH)));
	         }
        });
		
		
		lblLogout.addMouseListener(new MouseAdapter()
        {
                     @Override
	    	 public void mouseEntered(MouseEvent evt)
	         {
	    		 lblLogout.setIcon(new ImageIcon(new ImageIcon("./icons/logout_focus_icon.png").getImage().getScaledInstance(51, 70, Image.SCALE_SMOOTH)));
	         }
                     @Override
	         public void mouseExited(MouseEvent evt)
	         {
	        	 lblLogout.setIcon(new ImageIcon(new ImageIcon("./icons/logout_icon.png").getImage().getScaledInstance(51, 70, Image.SCALE_SMOOTH)));
	         }
                     @Override
	         public void mousePressed(MouseEvent evt)
	         {
	        	 lblLogout.setIcon(new ImageIcon(new ImageIcon("./icons/logout_icon.png").getImage().getScaledInstance(51, 70, Image.SCALE_SMOOTH)));
	        	 login.logout();
	        	 /* set server setting tab (default state) */
	        	 ((CardLayout)control_panel.getLayout()).show(control_panel, "server_setting_card");
	        	 server_setting_panel.setVisible(true);
	        	 user_management_panel.setVisible(false);
	        	 back_up_panel.setVisible(false);
	        	 restore_panel.setVisible(false);
	         }
                     @Override
	         public void mouseReleased(MouseEvent evt)
	         {
	        	 lblLogout.setIcon(new ImageIcon(new ImageIcon("./icons/logout_focus_icon.png").getImage().getScaledInstance(51, 70, Image.SCALE_SMOOTH)));
	         }
        });
		
		
		lblUsermanagement.addMouseListener(new MouseAdapter()
        {
                     @Override
	    	 public void mouseEntered(MouseEvent evt)
	         {
	    		 lblUsermanagement.setIcon(new ImageIcon(new ImageIcon("./icons/user_management_focus_icon.png").getImage().getScaledInstance(108,68, Image.SCALE_SMOOTH)));
	         }
                     @Override
	         public void mouseExited(MouseEvent evt)
	         {
	        	 lblUsermanagement.setIcon(new ImageIcon(new ImageIcon("./icons/user_management_icon.png").getImage().getScaledInstance(108,68, Image.SCALE_SMOOTH)));
	         }
                     @Override
	         public void mousePressed(MouseEvent evt)
	         {
	        	 lblUsermanagement.setIcon(new ImageIcon(new ImageIcon("./icons/user_management_icon.png").getImage().getScaledInstance(108,68, Image.SCALE_SMOOTH)));
	        	 ((CardLayout)control_panel.getLayout()).show(control_panel, "user_management_card");
	        	 server_setting_panel.setVisible(false);
	        	 user_management_panel.setVisible(true);
	        	 back_up_panel.setVisible(false);
	        	 restore_panel.setVisible(false);
	        	 
	        	 ((CardLayout)panel.getLayout()).show(panel, "blank_card");
					manage.setVisible(false);
					change_password.setVisible(false);
	        	 
	        	 load_user_list();
	         }
                     @Override
	         public void mouseReleased(MouseEvent evt)
	         {
	        	 lblUsermanagement.setIcon(new ImageIcon(new ImageIcon("./icons/user_management_focus_icon.png").getImage().getScaledInstance(108,68, Image.SCALE_SMOOTH)));
	         }
        });
                
	csv_address_browse_btn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent evt)
            {

            }

            @Override
            public void mouseExited(MouseEvent evt)
            {

            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                output_csv_address.setText(set_address("Save", null, null));
                //db_reference.generate_output_csv("17200000119", "glued_frame_581", "0", "0");        
            }

            @Override
            public void mouseReleased(MouseEvent evt)
            {

            }
             
        });
        
        btnBrowseDbBackupAddress.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent evt)
            {

            }

            @Override
            public void mouseExited(MouseEvent evt)
            {

            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                textDbFileBackupAddress.setText(set_address("Save", null, null));
                //db_reference.generate_output_csv("17200000119", "glued_frame_581", "0", "0");        
            }

            @Override
            public void mouseReleased(MouseEvent evt)
            {

            }
             
        });
                
		lblExportdatabse.addMouseListener(new MouseAdapter()
                {
                     @Override
	    	 public void mouseEntered(MouseEvent evt)
	         {
	    		 lblExportdatabse.setIcon(new ImageIcon(new ImageIcon("./icons/export_database_focus_icon.png").getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH)));
	         }
                     @Override
	         public void mouseExited(MouseEvent evt)
	         {
	        	 lblExportdatabse.setIcon(new ImageIcon(new ImageIcon("./icons/export_database_icon.png").getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH)));
	         }
                     @Override
	         public void mousePressed(MouseEvent evt)
	         {
	        	 lblExportdatabse.setIcon(new ImageIcon(new ImageIcon("./icons/export_database_icon.png").getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH)));
	        	 ((CardLayout)control_panel.getLayout()).show(control_panel, "backup_card");
	        	 server_setting_panel.setVisible(false);
	        	 user_management_panel.setVisible(false);
	        	 back_up_panel.setVisible(true);
	        	 restore_panel.setVisible(false);
	         }
                     @Override
	         public void mouseReleased(MouseEvent evt)
	         {
	        	 lblExportdatabse.setIcon(new ImageIcon(new ImageIcon("./icons/export_database_focus_icon.png").getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH)));
	         }
        });
		
		
		lblImportdatabase.addMouseListener(new MouseAdapter()
        {
                     @Override
	    	 public void mouseEntered(MouseEvent evt)
	         {
	    		 lblImportdatabase.setIcon(new ImageIcon(new ImageIcon("./icons/import_database_focus_icon.png").getImage().getScaledInstance(105, 70, Image.SCALE_SMOOTH)));
	         }
                     @Override
	         public void mouseExited(MouseEvent evt)
	         {
	        	 lblImportdatabase.setIcon(new ImageIcon(new ImageIcon("./icons/import_database_icon.png").getImage().getScaledInstance(105, 70, Image.SCALE_SMOOTH)));
	         }
	         public void mousePressed(MouseEvent evt)
	         {
	        	 lblImportdatabase.setIcon(new ImageIcon(new ImageIcon("./icons/import_database_icon.png").getImage().getScaledInstance(105, 70, Image.SCALE_SMOOTH)));
	        	 ((CardLayout)control_panel.getLayout()).show(control_panel, "restore_card");
	        	 server_setting_panel.setVisible(false);
	        	 user_management_panel.setVisible(false);
	        	 back_up_panel.setVisible(false);
	        	 restore_panel.setVisible(true);
	         }
	         public void mouseReleased(MouseEvent evt)
	         {
	        	 lblImportdatabase.setIcon(new ImageIcon(new ImageIcon("./icons/import_database_focus_icon.png").getImage().getScaledInstance(105, 70, Image.SCALE_SMOOTH)));
	         }
        });
		
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				((CardLayout)panel.getLayout()).show(panel, "user_add_card");
				blank.setVisible(false);
				change_password.setVisible(false);
				user_name_input.setText("");
				passwordField.setText("");
			}
		});
		
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				((CardLayout)panel.getLayout()).show(panel, "blank_card");
				user_add_card.setVisible(false);
				change_password.setVisible(false);
			}
		});
		
		btnDeleteUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

				/* osetrit proti smazani posledniho uzivatele (u aktualne prihlaseneho uctu neni zobrazoveno tlacitko na vymazani uctu)*/
				
				if (JOptionPane.showConfirmDialog(  
		                null,
		                "Really remove the user?" ,
		                "Delete user",
		                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{
					if(db_reference.remove_record("users", "name", user_list.getSelectedValue()) == true )
					{
						JOptionPane.showMessageDialog(
							    null, 
							    "User was successfully removed.", 
							    "Success",
							    JOptionPane.INFORMATION_MESSAGE, 
							    null); 
	
						load_user_list();
						((CardLayout)panel.getLayout()).show(panel, "blank_card");
					}
					else
					{
						JOptionPane.showMessageDialog(
							    null, 
							    "User was not removed.", 
							    "Success",
							    JOptionPane.ERROR_MESSAGE, 
							    null); 
					}
				}
				
			}
		});
		
		
		btnCreate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				/*
				 * 
				 * CREATE TABLE 'users' (
  'name' varchar(255),
  'password' varchar(255),
  PRIMARY KEY ('name')
);
				 * 
				 */

				try 
				{
					if(!user_name_input.getText().equals("") && passwordField.getPassword().length > 3)
					{
						MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
						if(db_reference.add_record("users","'name', 'password' " ,"'"+user_name_input.getText()+"', '"+Base64.getEncoder().withoutPadding().encodeToString(md.digest(new String(passwordField.getPassword()).getBytes()))+"'") == true)
						{
							JOptionPane.showMessageDialog(
								    null, 
								    "User was successfully created.", 
								    "Success",
								    JOptionPane.INFORMATION_MESSAGE, 
								    null); 
							load_user_list();
							user_name_input.setText("");
							passwordField.setText("");
						}
						else
						{
							JOptionPane.showMessageDialog(
								    null, 
								    "User was not created!", 
								    "Error",
								    JOptionPane.ERROR_MESSAGE, 
								    null); 
						}
					}
					else
					{
						JOptionPane.showMessageDialog(
							    null, 
							    "Password must be longer than 3 characters!", 
							    "Error",
							    JOptionPane.ERROR_MESSAGE, 
							    null); 
					}
				} 
				catch (NoSuchAlgorithmException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		browse_output_address.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String temp = set_address("Save", "db_backup", "db");
				if(!temp.equals(""))
				{
					text_output_file.setText(temp+".db");
				}
			}
		});
		
		browse_input_address.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				text_input_file.setText(set_address("Open", "", "db"));
			}
		});
		
		btnBackupDatabase.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
                            if(!text_output_file.getText().equals(""))
                            {
                                if (db_reference.datebase_backup(text_output_file.getText()))
                                {          
                                    JOptionPane.showMessageDialog(
                                        		    null, 
							    "Backup success.", 
							    "Backup",
							    JOptionPane.INFORMATION_MESSAGE, 
							    null); 
                                    
				    text_output_file.setText("");
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(
                                        		    null, 
							    "Backup copy error!", 
							    "Error",
							    JOptionPane.ERROR_MESSAGE, 
							    null); 
                                }
                                   
                            }
			}
		});
		
		restore.addActionListener(new ActionListener() 
		{
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        if(!text_input_file.getText().equals(""))
                        {    
                            try 
                            {
                                db_reference.get_database_structure().close();
                            } 
                            catch (SQLException ex) 
                            {
                                Logger.getLogger(settings_view.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            if(db_reference.datebase_restore(text_input_file.getText()))
                            {
                                try 
                                {
                                    
                                    db_reference.set_database_structure(DriverManager.getConnection("jdbc:sqlite:./db/db_content.db"));
                                    JOptionPane.showMessageDialog(
                                                null,
                                                "Restor success.",
                                                "Backup",
                                                JOptionPane.INFORMATION_MESSAGE,
                                                null);
                                    text_input_file.setText("");
                                } 
                                catch (SQLException ex) 
                                {
                                    Logger.getLogger(settings_view.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(
                                                        null, 
                                                        "Restor copy error!", 
                                                        "Error",
                                                        JOptionPane.ERROR_MESSAGE, 
                                                        null); 
                            }
                        }
                    }
		});
		
		user_list.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(user_list.getSelectedValue()!=null)
				{
					lbl_user_name.setText("User account: "+user_list.getSelectedValue());
					btnDeleteUser.setVisible(!user_list.getSelectedValue().equals(db_server_webasto_main.logged_in+" (me)"));
					((CardLayout)panel.getLayout()).show(panel, "manage_card");
					blank.setVisible(false);
					change_password.setVisible(false);
				}
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				change_password.setVisible(true);
				blank.setVisible(false);
				manage.setVisible(false);
				((CardLayout)panel.getLayout()).show(panel, "passwd_card");
				actual_password_input.setText("");
				new_password_confirm_input.setText("");
				new_password_input.setText("");
			}
		});
		
		passwd_cancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(user_list.getSelectedValue()!=null)
				{
					lbl_user_name.setText("User account: "+user_list.getSelectedValue());
					btnDeleteUser.setVisible(!user_list.getSelectedValue().equals(db_server_webasto_main.logged_in+" (me)"));
					((CardLayout)panel.getLayout()).show(panel, "manage_card");
					blank.setVisible(false);
					change_password.setVisible(false);
				}
			}
		});
		
		btn_change_password_ok.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				/* overit zda se aktualni heslo shoduje s heslem daneho uzivatele */
				try 
				{
					MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");

					String password = Base64.getEncoder().withoutPadding().encodeToString(md.digest(new String(actual_password_input.getPassword()).getBytes()));
					
					if(db_reference.get_record_item("users", "name","password",
							user_list.getSelectedValue().equals(db_server_webasto_main.logged_in+" (me)")? db_server_webasto_main.logged_in : user_list.getSelectedValue() 
							).equals(password))
					{

						/* overit zda neni vstup noveho hesla prazdny */
						if(new_password_input.getPassword().length > 2)
						{
							/* overit zda se obe hesla shoduje */
							if(new String(new_password_confirm_input.getPassword()).equals(new String(new_password_input.getPassword())))
							{
								/* ulozit nove heslo do databaze */
								if(db_reference.set_record_item("users", "name",user_list.getSelectedValue().equals(db_server_webasto_main.logged_in+" (me)")? db_server_webasto_main.logged_in : user_list.getSelectedValue(), "password", Base64.getEncoder().withoutPadding().encodeToString(md.digest(new String(new_password_input.getPassword()).getBytes()))) == true)
								{
									JOptionPane.showMessageDialog(
										    null, 
										    "Password was succesfully changed!", 
										    "Passwd",
										    JOptionPane.INFORMATION_MESSAGE, 
										    null); 
									
									new_password_confirm_input.setText("");
									new_password_input.setText("");
									actual_password_input.setText("");
								}
								else
								{
									JOptionPane.showMessageDialog(
										    null, 
										    "Password was not changed!", 
										    "Unknown error",
										    JOptionPane.ERROR_MESSAGE, 
										    null); 
								}
								
							}
							else
							{
								JOptionPane.showMessageDialog(
									    null, 
									    "Password do not mutch!", 
									    "Error",
									    JOptionPane.ERROR_MESSAGE, 
									    null); 
							}
							
							
							
						}
						else
						{
							JOptionPane.showMessageDialog(
								    null, 
								    "Password must be longer than 3 characters!", 
								    "Error",
								    JOptionPane.ERROR_MESSAGE, 
								    null); 
						}
					}
					else
					{
						JOptionPane.showMessageDialog(
							    null, 
							    "Bad actuale password!", 
							    "Error",
							    JOptionPane.ERROR_MESSAGE, 
							    null); 
					}
				} 
				catch (NoSuchAlgorithmException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void load_user_list()
	{
		try
		{
			DefaultListModel<String> dlm = new DefaultListModel<String>();
			
			Connection user_table = db_reference.get_database_structure();
			String sql = "SELECT name FROM 'users';";
	
			Statement stmt = user_table.createStatement();
			stmt.execute(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				String temp_key = rs.getString("name");
				 if(temp_key.equals(db_server_webasto_main.logged_in))
				 {
					 dlm.addElement(temp_key+" (me)");
				 }
				 else
				 {
					 dlm.addElement(temp_key);
				 }
			}
			
			this.user_list.setModel(dlm);
		}
		catch (SQLException esd) 
   	 	{
   		// TODO Auto-generated catch block		
   		esd.printStackTrace();
   	 	}
	}
}
