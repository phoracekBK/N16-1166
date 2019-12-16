package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import database.database_interface;
import db_server_webasto.db_server_webasto_main;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class login_window extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField user_name_input;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	private JTabbedPane tabbedPane_reference;
	private JLabel lblError;
	
	public login_window(JTabbedPane tabbedPane, database_interface db_reference) 
	{
            setAlwaysOnTop(true);
            setType(Type.POPUP);
            setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            setResizable(false);
            this.tabbedPane_reference = tabbedPane;

            setBounds(100, 100, 405, 229);
            getContentPane().setLayout(null);
            {
                    user_name_input = new JTextField();

                    user_name_input.setText(db_reference.get_record_item("settings", "network", "last_logged_user", "ID"));

                    user_name_input.addActionListener(new ActionListener() 
                    {
                            public void actionPerformed(ActionEvent e) 
                            {
                                    login(db_reference);
                            }
                    });
                    user_name_input.setBounds(114, 49, 242, 22);
                    getContentPane().add(user_name_input);
                    user_name_input.setColumns(10);
            }
            setTitle("Login");

            passwordField = new JPasswordField();
            passwordField.addActionListener(new ActionListener() 
            {
                    public void actionPerformed(ActionEvent e) 
                    {
                            login(db_reference);
                    }
            });
            passwordField.setBounds(114, 95, 242, 22);
            getContentPane().add(passwordField);

            JLabel lblUserName = new JLabel("Username:");
            lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
            lblUserName.setBounds(12, 52, 80, 16);
            getContentPane().add(lblUserName);

            JLabel lblPassword = new JLabel("Password:");
            lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
            lblPassword.setBounds(12, 98, 80, 16);
            getContentPane().add(lblPassword);

            lblError = new JLabel("error");
            lblError.setVisible(false);
            lblError.setHorizontalAlignment(SwingConstants.LEFT);
            lblError.setForeground(Color.RED);
            lblError.setBounds(114, 20, 242, 16);
            getContentPane().add(lblError);

            JButton btnLogin = new JButton("Login");
            btnLogin.setBounds(114, 137, 97, 25);
            getContentPane().add(btnLogin);

            btnLogin.addActionListener(new ActionListener() 
            {

                    public void actionPerformed(ActionEvent e) 
                    {
                            login(db_reference);
                    }
            });
	}

	private void login(database_interface db_reference)
	{
            if (!user_name_input.getText().equals("") && passwordField.getPassword().length > 0)
            {
                db_reference.set_record_item("settings", "ID", "network", "last_logged_user", user_name_input.getText());
                
                if (db_reference.get_record_item("users", user_name_input.getText(), "name", "name") != null)
                {
                    MessageDigest md;
                    try 
                    {
                        md = java.security.MessageDigest.getInstance("SHA-256");
                        if(Base64.getEncoder().withoutPadding().encodeToString(md.digest(new String(passwordField.getPassword()).getBytes())).equals(db_reference.get_record_item("users",user_name_input.getText(),"password","name")))
                        {
                                lblError.setVisible(false);
                                tabbedPane_reference.setVisible(true);
                                db_server_webasto_main.logged_in = user_name_input.getText();
                                passwordField.setText("");
                               
                                setVisible(false);
                        }
                        else
                        {
                                lblError.setText("Bad password!");
                                lblError.setVisible(true);
                        }
                    } 
                    catch (NoSuchAlgorithmException e) 
                    {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }

                }
                else
                {
                        lblError.setText("Bad user name!");
                        lblError.setVisible(true);
                }   
            }
            else
            {
                lblError.setText("No name or password!");
                lblError.setVisible(true);
            }



            /*
            lblError.setVisible(false);
            tabbedPane_reference.setVisible(true);
            //db_server_webasto_main.logged_in = user_name_input.getText();
            passwordField.setText("");
            setVisible(false);
            */
		
	}
	
	public void logout()
	{
		this.setVisible(true);
		db_server_webasto_main.logged_in = "";
		this.tabbedPane_reference.setVisible(false);
	}
}
