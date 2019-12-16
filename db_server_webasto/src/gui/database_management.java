package gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import database.database_interface;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.net.Socket;
import java.sql.ResultSetMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;

public class database_management extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private final JTable table;
	private final JLabel lblCreate;
	private final JLabel lblRemove;
	private final JLabel lblSearch;
	private final JScrollPane scrollPane;
	private final JPanel controlPanel;
	private final JComboBox<String> comboBox;
        private final JComboBox<String> record_choose_id;
	private final JLabel lblSelectTable;
	private final JLabel lblExport;
	private final JPanel create_table_panel;
	private final JTextField new_table_input;
	private final JButton btnCreateTable;
	private final JLabel lblTableName;
	private final JPanel remove_table_panel;
	private final JPanel search_panel;
	private final JPanel export_panel;
	private final JButton btnRemoveTable;
	private final JTextField record_search_input;
	private final JLabel lblRecordName;
	private final JButton btnSearch;
        private final JButton btnPrint;
	private final JTextField textField;
	private final JButton btnBrowse;
	private final JButton btnExportTable;
	private final CardLayout cards;
	private final database_interface db_reference;
	private final JButton btnBack;
        private final JButton btnRemoveRecord;
	private final JLabel lblRefresh;
        private String last_search;
	
	public database_management(database_interface db_reference) 
	{
		this.db_reference = db_reference;
		setLayout(null);
                last_search = new String();
		cards = new CardLayout(0, 0);
		
		controlPanel = new JPanel(cards);
		controlPanel.setBounds(601, 182, 276, 557);
		add(controlPanel);
		
		create_table_panel = new JPanel();
		controlPanel.add(create_table_panel, "1");
		create_table_panel.setVisible(false);
		create_table_panel.setLayout(null);
		
		new_table_input = new JTextField();
		new_table_input.setBounds(23, 35, 116, 22);
		create_table_panel.add(new_table_input);
		new_table_input.setColumns(10);
		
		btnCreateTable = new JButton("Create table");
		
		btnCreateTable.setBounds(5, 65, 115, 25);
		create_table_panel.add(btnCreateTable);
		
		lblTableName = new JLabel("SQL query:");
		lblTableName.setBounds(5, 5, 125, 16);
		create_table_panel.add(lblTableName);

		remove_table_panel = new JPanel();
		remove_table_panel.setVisible(false);
		controlPanel.add(remove_table_panel, "2");
		remove_table_panel.setLayout(null);
		
		btnRemoveTable = new JButton("Remove table");
		btnRemoveTable.setBounds(5, 10, 111, 25);
		remove_table_panel.add(btnRemoveTable);
		
		search_panel = new JPanel();
		search_panel.setVisible(false);
		controlPanel.add(search_panel, "3");
		search_panel.setLayout(null);
		
                
                JLabel info_label  = new JLabel("Select record search key:");
		info_label.setBounds(5, 5, 146, 16);
		search_panel.add(info_label);
                
                record_choose_id = new JComboBox<String>();
                record_choose_id.setBounds(5, 25, 115, 22);
                search_panel.add(record_choose_id);
                
		record_search_input = new JTextField();
		record_search_input.setBounds(5, 80, 115, 22);
                record_search_input.setColumns(50);
		search_panel.add(record_search_input);
		
		
		lblRecordName = new JLabel("Identifier:");
		lblRecordName.setBounds(5, 60, 146, 16);
		search_panel.add(lblRecordName);

		btnSearch = new JButton("Search");
		
		btnSearch.setBounds(5, 115, 150, 25);
		search_panel.add(btnSearch);
		
		btnBack = new JButton("Back");
		btnBack.setVisible(false);
		btnBack.setBounds(165, 115, 150, 25);
		search_panel.add(btnBack);
                
                btnRemoveRecord = new JButton("Remove record");
		btnRemoveRecord.setVisible(false);
		btnRemoveRecord.setBounds(5, 150, 150, 25);
		search_panel.add(btnRemoveRecord);
                
                
                btnPrint = new JButton("Print birth label");
                btnPrint.setVisible(false);
                btnPrint.setBounds(165, 150, 150, 25);
                search_panel.add(btnPrint);
		
		export_panel = new JPanel();
		export_panel.setVisible(false);
		controlPanel.add(export_panel, "4");
		export_panel.setLayout(null);
		
		textField = new JTextField();
		export_panel.add(textField);
		textField.setColumns(255);
		
		btnBrowse = new JButton("Browse");
		
		btnBrowse.setBounds(5, 25, 97, 25);
		export_panel.add(btnBrowse);
		
		btnExportTable = new JButton("Export");
		
		
		export_panel.add(btnExportTable);
		
		JLabel lblOutputFileAddress = new JLabel("Output file address:");
		lblOutputFileAddress.setBounds(120, 5, 150, 16);
		export_panel.add(lblOutputFileAddress);
		
		this.lblCreate = new JLabel("create");
		lblCreate.setBounds(40, 40, 74, 60);
		this.lblCreate.setIcon(new ImageIcon(new ImageIcon("./icons/create_table_icon.png").getImage().getScaledInstance(74, 60, Image.SCALE_SMOOTH)));
		add(lblCreate);
		
		this.lblRemove = new JLabel("remove");
		lblRemove.setBounds(160, 40, 78, 60);
		this.lblRemove.setIcon(new ImageIcon(new ImageIcon("./icons/remove_table_icon.png").getImage().getScaledInstance(78, 60, Image.SCALE_SMOOTH)));
		add(lblRemove);
		
		this.lblSearch = new JLabel("search");
		lblSearch.setBounds(300, 40, 58, 60);
		this.lblSearch.setIcon(new ImageIcon(new ImageIcon("./icons/search_icon.png").getImage().getScaledInstance(58, 60, Image.SCALE_SMOOTH)));
		add(lblSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 150, 452, 589);
		add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(false);
                table.setRowSelectionAllowed(true);
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox<String>();
		
		comboBox.setBounds(601, 147, 276, 22);
		comboBox.addItem("Select table...");
		comboBox.setVisible(false);
		add(comboBox);
		
		lblSelectTable = new JLabel("Select table:");
		lblSelectTable.setBounds(601, 147, 276, 16);
		lblSelectTable.setVisible(false);
		add(lblSelectTable);
		
		lblExport = new JLabel("export");
		lblExport.setBounds(400, 40, 75, 60);
		this.lblExport.setIcon(new ImageIcon(new ImageIcon("./icons/export_table_icon.png").getImage().getScaledInstance(75, 60, Image.SCALE_SMOOTH)));
		add(lblExport);
		cards.show(controlPanel, "1");
		
		lblRefresh = new JLabel("refresh");
		lblRefresh.setBounds(530, 33, 50, 67);
		lblRefresh.setIcon(new ImageIcon(new ImageIcon("./icons/refresh_icon.png").getImage().getScaledInstance(50, 67, Image.SCALE_SMOOTH)));
		add(lblRefresh);
		
		load_database_tables();
		
		events();
	}
	
	private String set_address(String actual_directory, String button_text, String default_file_name, String output_file_format)
    {
        JFileChooser chooser = new JFileChooser();
        
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("*."+output_file_format, output_file_format));
        chooser.setApproveButtonText(button_text);
        chooser.setSelectedFile(new File(default_file_name));  
        chooser.setCurrentDirectory(new java.io.File(actual_directory));
        chooser.setDialogTitle("Browse the directory to process.");
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
				
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				scrollPane.setBounds(30, 130, (int)(getWidth()/100.0*70.0), getHeight()-150);
				controlPanel.setBounds((int)(getWidth()/100.0*75.0), 180, (int)(getWidth()/100.0*25.0), getHeight()-150);
				lblSelectTable.setBounds((int)(getWidth()/100.0*75.0)+1, 125, (int)(getWidth()/100.0*25.0), 16);
				comboBox.setBounds((int)(getWidth()/100.0*75.0)+5, 150, (int)(getWidth()/100.0*25.0-25), 22);
				new_table_input.setBounds(5, 25, (int)(getWidth()/100.0*25.0-20), 22);
				record_search_input.setBounds(5, 80, (int)(getWidth()/100.0*25.0-20), 22);
				textField.setBounds(120, 25,  (int)(getWidth()/100.0*25.0-20-130), 25);
				btnExportTable.setBounds(getWidth() < 900 ? 5 :120, 65, 80, 25);
                                record_choose_id.setBounds(5, 25, (int)(getWidth()/100.0*25.0-25), 25);
				revalidate();
				repaint();
			}

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		lblRefresh.addMouseListener(new MouseAdapter()
        {
	    	 public void mouseEntered(MouseEvent evt)
	         {
	    		 lblRefresh.setIcon(new ImageIcon(new ImageIcon("./icons/refresh_focus_icon.png").getImage().getScaledInstance(50, 67, Image.SCALE_SMOOTH)));
	         }
	         public void mouseExited(MouseEvent evt)
	         {
	        	 lblRefresh.setIcon(new ImageIcon(new ImageIcon("./icons/refresh_icon.png").getImage().getScaledInstance(50, 67, Image.SCALE_SMOOTH)));
	         }
	         public void mousePressed(MouseEvent evt)
	         {
	        	lblRefresh.setIcon(new ImageIcon(new ImageIcon("./icons/refresh_icon.png").getImage().getScaledInstance(50, 67, Image.SCALE_SMOOTH)));
	        	Object temp_selected_item = comboBox.getSelectedItem();
	        	load_database_tables();
	     		comboBox.setSelectedItem(temp_selected_item);
	     		load_table_content();
	         }
	         public void mouseReleased(MouseEvent evt)
	         {
	        	 lblRefresh.setIcon(new ImageIcon(new ImageIcon("./icons/refresh_focus_icon.png").getImage().getScaledInstance(50, 67, Image.SCALE_SMOOTH)));
	         }
        });
		
		
		
		    lblExport.addMouseListener(new MouseAdapter()
	        {
		    	 public void mouseEntered(MouseEvent evt)
		         {
		    		 lblExport.setIcon(new ImageIcon(new ImageIcon("./icons/export_table_focus_icon.png").getImage().getScaledInstance(75, 60, Image.SCALE_SMOOTH)));
		         }
		         public void mouseExited(MouseEvent evt)
		         {
		        	 lblExport.setIcon(new ImageIcon(new ImageIcon("./icons/export_table_icon.png").getImage().getScaledInstance(75, 60, Image.SCALE_SMOOTH)));
		         }
		         public void mousePressed(MouseEvent evt)
		         {
		        	 cards.last(controlPanel);
		        	 export_panel.setVisible(true);
		        	 remove_table_panel.setVisible(false);
		        	 search_panel.setVisible(false);
		        	 lblExport.setIcon(new ImageIcon(new ImageIcon("./icons/export_table_icon.png").getImage().getScaledInstance(75, 60, Image.SCALE_SMOOTH)));
		        	 comboBox.setVisible(true);
		        	 lblSelectTable.setVisible(true);
		        	 
		         }
		         public void mouseReleased(MouseEvent evt)
		         {
		        	 lblExport.setIcon(new ImageIcon(new ImageIcon("./icons/export_table_focus_icon.png").getImage().getScaledInstance(75, 60, Image.SCALE_SMOOTH)));
		         }
	        });
		    
		lblSearch.addMouseListener(new MouseAdapter()
	        {
		    	 public void mouseEntered(MouseEvent evt)
		         {
		    		 lblSearch.setIcon(new ImageIcon(new ImageIcon("./icons/search_focus_icon.png").getImage().getScaledInstance(58, 60, Image.SCALE_SMOOTH)));
		         }
		         public void mouseExited(MouseEvent evt)
		         {
		        	 lblSearch.setIcon(new ImageIcon(new ImageIcon("./icons/search_icon.png").getImage().getScaledInstance(58, 60, Image.SCALE_SMOOTH)));
		         }
		         public void mousePressed(MouseEvent evt)
		         {
		        	 cards.show(controlPanel, "3");
		        	 search_panel.setVisible(true);
		        	 export_panel.setVisible(false);
		        	 remove_table_panel.setVisible(false);
		        	 lblSearch.setIcon(new ImageIcon(new ImageIcon("./icons/search_icon.png").getImage().getScaledInstance(58, 60, Image.SCALE_SMOOTH)));
		        	 comboBox.setVisible(true);
		        	 lblSelectTable.setVisible(true);
		        
		         }
		         public void mouseReleased(MouseEvent evt)
		         {
		        	 lblSearch.setIcon(new ImageIcon(new ImageIcon("./icons/search_focus_icon.png").getImage().getScaledInstance(58, 60,Image.SCALE_SMOOTH)));
		         }
	        });
		    
		    lblCreate.addMouseListener(new MouseAdapter()
	        {
		    	 public void mouseEntered(MouseEvent evt)
		         {
		    		 lblCreate.setIcon(new ImageIcon(new ImageIcon("./icons/create_table_focus_icon.png").getImage().getScaledInstance(74, 60, Image.SCALE_SMOOTH)));
		         }
		         public void mouseExited(MouseEvent evt)
		         {
		        	 lblCreate.setIcon(new ImageIcon(new ImageIcon("./icons/create_table_icon.png").getImage().getScaledInstance(74, 60, Image.SCALE_SMOOTH)));
		         }
		         public void mousePressed(MouseEvent evt)
		         {
		        	 cards.show(controlPanel, "1");
		        	 search_panel.setVisible(false);
		        	 export_panel.setVisible(false);
		        	 remove_table_panel.setVisible(false);
		        	 lblCreate.setIcon(new ImageIcon(new ImageIcon("./icons/create_table_icon.png").getImage().getScaledInstance(74, 60, Image.SCALE_SMOOTH)));
		        	 comboBox.setVisible(false);
		        	 lblSelectTable.setVisible(false);
		        	
		         }
		         public void mouseReleased(MouseEvent evt)
		         {
		        	 lblCreate.setIcon(new ImageIcon(new ImageIcon("./icons/create_table_focus_icon.png").getImage().getScaledInstance(74, 60, Image.SCALE_SMOOTH)));
		         }
	        });
		    
		    lblRemove.addMouseListener(new MouseAdapter()
	        {
		    	 public void mouseEntered(MouseEvent evt)
		         {
		    		 lblRemove.setIcon(new ImageIcon(new ImageIcon("./icons/remove_table_focus_icon.png").getImage().getScaledInstance(78, 60, Image.SCALE_SMOOTH)));
		         }
		         public void mouseExited(MouseEvent evt)
		         {
		        	 lblRemove.setIcon(new ImageIcon(new ImageIcon("./icons/remove_table_icon.png").getImage().getScaledInstance(78, 60, Image.SCALE_SMOOTH)));
		         }
		         public void mousePressed(MouseEvent evt)
		         {
		        	 cards.show(controlPanel, "2");
		        	 search_panel.setVisible(false);
		        	 export_panel.setVisible(false);
		        	 remove_table_panel.setVisible(true);
		        	 lblRemove.setIcon(new ImageIcon(new ImageIcon("./icons/remove_table_icon.png").getImage().getScaledInstance(78, 60, Image.SCALE_SMOOTH)));
		        	 comboBox.setVisible(true);
		        	 lblSelectTable.setVisible(true);

		         }
		         public void mouseReleased(MouseEvent evt)
		         {
		        	 
		        	 lblRemove.setIcon(new ImageIcon(new ImageIcon("./icons/remove_table_focus_icon.png").getImage().getScaledInstance(78, 60, Image.SCALE_SMOOTH)));
		         }
	        });
		    
		    btnCreateTable.addActionListener(new ActionListener() 
		    {
				public void actionPerformed(ActionEvent e) 
				{
					if(!new_table_input.getText().equals(""))
					{
						if(db_reference.db_query(new_table_input.getText()) == true)
						{
						load_database_tables();
						new_table_input.setText("");
						JOptionPane.showMessageDialog(
							    null, 
							    "Database table successfully created.", 
							    "Info",
							    JOptionPane.INFORMATION_MESSAGE, 
							    null); 
						}
						else
						{
							JOptionPane.showMessageDialog(
								    null, 
								    "Database table was not created!", 
								    "Error",
								    JOptionPane.ERROR_MESSAGE, 
								    null); 
						}
					}
					else
					{
						JOptionPane.showMessageDialog(
							    null, 
							    "Bad table name!", 
							    "Error",
							    JOptionPane.ERROR_MESSAGE, 
							    null); 
					}
				}
			});
		    
		    btnSearch.addActionListener(new ActionListener() 
		    {
                        public void actionPerformed(ActionEvent arg0) 
                        {
                            if(!record_search_input.getText().equals(""))
                            {
                                last_search = record_search_input.getText();
                                search_record();
                                record_search_input.setText("");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(
                                    null, 
                                    "Bad record identifier!", 
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE, 
                                    null);
                            } 
                        }
                    });
		    
		    btnBrowse.addActionListener(new ActionListener() 
		    {
				public void actionPerformed(ActionEvent e) 
				{
					textField.setText(set_address("./", "Save", "db_table", "csv")+".csv");
				}
			});
                    
                    btnRemoveRecord.addActionListener(new ActionListener() 
		    {
				public void actionPerformed(ActionEvent e) 
				{
                                    if (JOptionPane.showConfirmDialog(  
					                null,
					                "Really remove the record?" ,
					                "Delete record",
					                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)	
				    {
					if(db_reference.remove_record(comboBox.getSelectedItem().toString(), table.getColumnName(0), table.getValueAt(table.getSelectedRow(), 0).toString()) == true)
                                        {
                                            JOptionPane.showMessageDialog(
                                                                null, 
                                                                "Record was successfully deleted.", 
                                                                "Delete",
                                                                JOptionPane.INFORMATION_MESSAGE, 
                                                                null);

                                            if(table.getRowCount() > 1 && btnBack.isVisible() == true)
                                            {
                                                search_record();
                                            }
                                            else
                                            {
                                                load_table_content();
                                            }
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(
							    null, 
							    "Record not deleted!", 
							    "Error",
							    JOptionPane.ERROR_MESSAGE, 
							    null);
                                        }
                                    }
				}
			});
		    
		    btnExportTable.addActionListener(new ActionListener() 
		    {
				public void actionPerformed(ActionEvent e) 
				{
					if(!textField.getText().equals("") && comboBox.getSelectedIndex() != 0)
					{
                                             try 
                                             {
                                                    OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(textField.getText()), "UTF-8");
                                                    //comboBox.getSelectedItem().toString()
                                                    Connection temp_db_table = db_reference.get_database_structure();
                                                    String sql = "SELECT * FROM '"+comboBox.getSelectedItem().toString()+"';";

                                            Statement stmt = temp_db_table.createStatement();
                                            stmt.execute(sql);
                                            ResultSet rs = stmt.executeQuery(sql);
                                            ResultSetMetaData rsmd = rs.getMetaData();
                                            int i = 1;

                                            /* nactene nazvu polozek databazoveho zaznamu */
                                            while(i <= rsmd.getColumnCount())
                                            {
                                                    writer.write(rsmd.getColumnName(i));
                                                    if(i < rsmd.getColumnCount())
                                                    {
                                                            writer.write(';');
                                                    }
                                                    i++;
                                            }


                                            while(rs.next())
                                            {
                                                    i = 1;
                                                    writer.write("\n");
                                                    while(i <= rsmd.getColumnCount())
                                                    {

                                                                    String temp = rs.getString(rsmd.getColumnLabel(i));
                                                                    if (temp != null)
                                                                    {
                                                                            writer.write(temp);
                                                                    }
                                                                    
                                                                    if (i < rsmd.getColumnCount())
                                                                    {
                                                                            writer.write(';');
                                                                    }
                                                                     i++;
                                                            }

                                            } 

                                                    writer.flush();
                                                    writer.close();
                                                    textField.setText("");
                                                    JOptionPane.showMessageDialog(
                                                                null, 
                                                                "Table successfully exported to csv file.", 
                                                                "Export",
                                                                JOptionPane.INFORMATION_MESSAGE, 
                                                                null);

                                                    //lblLoading.setVisible(false);
                                             } 
                                             catch (UnsupportedEncodingException | FileNotFoundException e1) 
                                             {
                                                    // TODO Auto-generated catch block
                                                    e1.printStackTrace();
                                             } 
                                             catch (IOException e1) 
                                             {
                                                    // TODO Auto-generated catch block
                                                    e1.printStackTrace();
                                             }
                                            catch (SQLException esd) 
                                            {
                                                // TODO Auto-generated catch block		
                                                esd.printStackTrace();
                                            }
					}
					else
					{
						JOptionPane.showMessageDialog(
							    null, 
							    "No output file address!", 
							    "Error",
							    JOptionPane.ERROR_MESSAGE, 
							    null);
					}
				}
			});
			
		    btnRemoveTable.addActionListener(new ActionListener() 
		    {
				public void actionPerformed(ActionEvent arg0) 
				{
					if(comboBox.getSelectedIndex() !=0)
					{
						if(comboBox.getSelectedItem().equals("users") == false && comboBox.getSelectedItem().equals("settings") == false)
						{
							if (JOptionPane.showConfirmDialog(  
					                null,
					                "Really remove the table?" ,
					                "Delete table",
					                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)	
							{
								db_reference.remove_db_table(comboBox.getSelectedItem().toString());
								load_database_tables();
								//lblLoading.setVisible(false);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(
								    null, 
								    "Protected table!", 
								    "Error",
								    JOptionPane.ERROR_MESSAGE, 
								    null); 
						}
					}
				}
			});
		    
		    comboBox.addActionListener(new ActionListener() 
		    {
				public void actionPerformed(ActionEvent e) 
				{
					if(comboBox!=null || comboBox.getSelectedIndex() != 0)
					{
                                            load_table_content();
					}
				}
			});
		    
                    btnPrint.addActionListener(new ActionListener() 
		    {
                        @Override
                        public void actionPerformed(ActionEvent e) 
                        {
                            try
                            {
                                int row_index;
                                if (table.getRowCount()>1)
                                {
                                    row_index = table.getSelectedRow();
                                }
                                else
                                {
                                    row_index = 0;
                                }
                                
                                System.out.println("row index = "+row_index+" row count = "+table.getRowCount());
                                
                                if(row_index >= 0)
                                {
                                    short frame_kind;
                                    if(comboBox.getSelectedItem().toString().equals("glued_frame_581") == true)
                                    {
                                        frame_kind = 1;
                                    }
                                    else if (comboBox.getSelectedItem().toString().equals("glued_frame_582") == true)
                                    {
                                         frame_kind = 2;
                                    }
                                    else if (comboBox.getSelectedItem().toString().equals("glued_frame_AU326_1") == true)
                                    {
                                        frame_kind = 3;
                                    } 
                                    else
                                    {
                                        return;
                                    }

                                
                                String commandOut = "^XA^MMT^PW480^LL0200^LS0^FO0,0^GFA,03072,03072,00032,:Z64:eJztk7GO4jAQhifJIZ2gIYWbrbZchTdI5Ui3PUjx+1jQ8RRZaCK7uTI3vNz9M4YEpIu47nQSI+wYO1/GM/MP0cte9k/NbXW2c+ehm+Y/8nb8StaOu5m7raKSG5mLMJ3Hv+WT56rHlE88jcvEm1mevcz1IP554nl88QkflF/O+X/GZ1H5VU8V4s/TZh7G+K8vmi0+lLXGUoYvGSfb7sF/HvALPS1D8CEc5V9/zy9cmznXknPGOvfp2kWqbIr/bXhjzi/MVDN75iMPWFz5THjj4NQ58K5RHhvJPzzCfxXCPqhvecI/Nrzy39S/s8Kl0RJ4pzzix4vdqo9+c4jneIgxHgIV8R0L5W1ZlmvwZBp4b3CLzxbbGZaSWvUalj3ucApU7dNA/oOvurv8C9+SAYvLK09GjvgofP2Lmc+IXsaBO7pAATV4eIT/0uxw3V3icQ+kEsVUPpyEr3pMJ2Qfo/KSBohhqfwPSYB75CWMKx+V33wh3HNPGxk+"
                                        + "xq+IZlgpvyspW4NX/ShvzR0vNac+dEH1V0kOfC5/sVA+9W8z8cjIHV8PeOuDOxQ7R8gnDE/1T77Fb0S0Jao35d8S0oH8aWqvneeR7n24agCeVQj+xi/Ap/qLhhqQt/rTSjqlQPWj1t5HfXax32j9adEmFT7qz476085LKoT+EbTewav+5GSR4qeb/o2T/JPoX9sxH7QLEPxQMA9UX1B/aYOC9SRTXhQnjWfxPfTPVsJKysolSYRLVLjtEkt5YHT0PTWwTfysKf8xf678ev5c7f3J+cte9rL/zn4DEoMnnA==:C58E"
                                        + "^FT32,85^A0N,17,16^FH\\^FD";
                                
                                String MCode = "";

                                    /* construct of mcode data */
                                    switch (frame_kind) {
                                        case 1:
                                            MCode += "AU581*1762975*"+table.getValueAt(row_index, 5);
                                            commandOut += "AU581^FS";
                                            break;
                                        case 2:
                                            MCode += "AU582*1762976*"+table.getValueAt(row_index, 12);
                                            commandOut += "AU582^FS";
                                            break;
                                        case 3:
                                            MCode += "AU326-1*1768362*"+table.getValueAt(row_index, 5);
                                            commandOut += "AU326-1^FS";
                                            break;
                                        default:
                                            break;
                                    }   
                                    

                                MCode += "*"+table.getValueAt(row_index, 0);
                                commandOut += "^FT32,104^A0N,17,16^FH\\^FDID-No.";

                                    switch (frame_kind) {
                                        case 1:
                                            commandOut += "1762975^FS";
                                            break;
                                        case 2:
                                            commandOut += "1762976^FS";
                                            break;
                                        case 3:
                                            commandOut += "1768362^FS";
                                            break;
                                        default:
                                            break;
                                    }
                                
                                commandOut += "^FT180,122^A0N,17,16^FH\\^FD";
                                
                                if(frame_kind == 1 ||frame_kind == 3)
                                {
                                    commandOut += table.getValueAt(row_index, 5).toString().substring(0, 10)+"^FS";
                                }
                                else if(frame_kind == 2)
                                {
                                     commandOut += table.getValueAt(row_index, 12).toString().substring(0, 10)+"^FS";
                                }

                                commandOut += "^FT31,142^A0N,17,16^FH\\^FDSerial-No:^FS^BY96,96^FT332,141^BXN,4,200,0,0,1,~^FH\\^FD"+MCode+"^FS"
                                        + "^FT32,161^A0N,17,16^FH\\^FDWBR Made in Romania (WRO)^FS"
                                        + "^FT31,123^A0N,17,16^FH\\^FDDate:^FS"
                                        + "^FT31,181^A0N,17,16^FH\\^FD"+MCode+"^FS"
                                        + "^FT70,122^A0N,17,16^FH\\^FD";

                                if (frame_kind == 1)
                                {
                                    commandOut += table.getValueAt(row_index, 5).toString().substring(11)+"^FS";

                                }
                                else if(frame_kind == 2)
                                {
                                    commandOut += table.getValueAt(row_index, 12).toString().substring(11)+"^FS";
                                }

                                commandOut += "^FT179,142^A0N,17,16^FH\\^FD"+table.getValueAt(row_index, 0).toString().substring(3)+"^FS"
                                        + "^FT114,142^A0N,17,16^FH\\^FD172^FS^PQ1,0,1,Y^XZ";

                                 Socket clientSocket = new Socket("192.168.0.30", 9100);
                                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())); 
                                 out.write(commandOut); // zapsani zpravy do BufferedWriteru
                                 out.flush(); // odeslani zpravy
                                 clientSocket.close();
                                 /* print label */
                                JOptionPane.showMessageDialog(
                                                            null, 
                                                            "Birth label is printed on Filling station printer.", 
                                                            "Printing",
                                                            JOptionPane.INFORMATION_MESSAGE, 
                                                            null); 
                                }
                                else
                                {
                                JOptionPane.showMessageDialog(
                                                            null, 
                                                            "No record selected!", 
                                                            "Error",
                                                            JOptionPane.ERROR_MESSAGE, 
                                                            null); 
                                }
                            }
                            catch (Exception ex) 
                            {
                                Logger.getLogger(database_management.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(
                                                            null, 
                                                            "Label was not successfuly printed!", 
                                                            "Error",
                                                            JOptionPane.ERROR_MESSAGE, 
                                                            null); 
                            }


                        }
                    });
                    
		    btnBack.addActionListener(new ActionListener() 
		    {
				public void actionPerformed(ActionEvent e) 
				{
                                    load_table_content();
				}
			});
		    
		    table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) 
				{
					//table.getColumnName(table.getSelectedColumn())
					if(table.getSelectedRow() >= 0 && comboBox.getSelectedItem().equals("users") == false && comboBox.getSelectedItem().equals("settings") == false)
					{
                                            btnRemoveRecord.setVisible(true);
                                            /*
						for(int i=0; i< table.getColumnCount(); i++)
						{
							if(table.getColumnName(i).equals("ID"))
							{
								System.out.println(table.isCellEditable(table.getSelectedRow(), i)+" row: "+Integer.toString(table.getSelectedRow())+" - "+table.getValueAt(table.getSelectedRow(), i));
							}
						}
                                            */
					}
					else
                                        {
                                            btnRemoveRecord.setVisible(false);
                                        }
				}
			});
	}
        
        private void search_record()
        {
            if(comboBox.getSelectedIndex() != 0)
            {
            Connection record =  db_reference.get_database_structure();

            if(record != null)
            { 	
                String sql = "SELECT * FROM '"+comboBox.getSelectedItem().toString()+"' WHERE "+record_choose_id.getSelectedItem().toString()+" = '"+last_search+"';";

                        Statement stmt;
                        try 
                        {
                                stmt = record.createStatement();
                                stmt.execute(sql);
                                ResultSet rs    = stmt.executeQuery(sql);
                                int i = 1;

                                ResultSetMetaData rsmd = rs.getMetaData();

                                ArrayList<String> array_keys = new ArrayList<String>();
                                ArrayList<String> array = new ArrayList<String>();

                                DefaultTableModel dtm = new DefaultTableModel(0,0)
                                {
                                    private static final long serialVersionUID = 1L;

                                    @Override
                                    public boolean isCellEditable(int row, int column) 
                                    {
                                        return false;
                                    }
                                };


                                if (rs.next())
                                {
                                    while(i<=rsmd.getColumnCount())
                                    {
                                        array_keys.add(rsmd.getColumnLabel(i));
                                        i++;
                                    }

                                    dtm.setColumnIdentifiers(array_keys.toArray());


                                    do
                                    {
                                        array.clear();
                                        i = 1;
                                        while(i<=rsmd.getColumnCount())
                                        {
                                            array.add(rs.getString(i));
                                            i++;
                                        }    
                                        dtm.addRow(array.toArray());
                                    }
                                    while(rs.next());
                                }
                                else
                                {
                                        JOptionPane.showMessageDialog(
                                                            null, 
                                                            "Record not found!", 
                                                            "Error",
                                                            JOptionPane.ERROR_MESSAGE, 
                                                            null);
                                        return;
                                }

                    table.setModel(dtm);

                    /* automatic resizing of columns */
                        if(dtm.getColumnCount() >= 5)
                        {
                                for(int index = 0; index < dtm.getColumnCount(); index++)
                                {

                                        table.getColumnModel().getColumn(index).setPreferredWidth((int)((getWidth()/100.0*70.0)/5.0));
                                }
                        }
                        else
                        {
                                for(int index = 0; index < dtm.getColumnCount(); index++)
                                    {
                                         table.getColumnModel().getColumn(index).setPreferredWidth((int)((getWidth()/100.0*70.0)/((double)dtm.getColumnCount())));
                                    }
                        }

                            btnBack.setVisible(true);
                            btnRemoveRecord.setVisible(false);

                            if (comboBox.getSelectedItem().toString().equals("glued_frame_581") || comboBox.getSelectedItem().toString().equals("glued_frame_582") ||comboBox.getSelectedItem().toString().equals("glued_frame_AU326_1"))
                            {
                                btnPrint.setVisible(true);
                            }
                        } 
                        catch (SQLException e) 
                        {
                                // TODO Auto-generated catch block		
                                e.printStackTrace();
                        }

                }
                else
                {
                    // this.table_view.setModel(new DefaultTableModel(0, 0));
                        JOptionPane.showMessageDialog(
                                                    null, 
                                                    "Record not found!", 
                                                    "Error",
                                                    JOptionPane.ERROR_MESSAGE, 
                                                    null);
                }
            }
        }
	
	private void load_database_tables()
        {      
            if(this.comboBox != null && db_reference != null)
            {
                last_search = "";
                btnBack.setVisible(false);
                btnPrint.setVisible(false);
                btnRemoveRecord.setVisible(false);
                
                this.comboBox.removeAllItems();
                this.comboBox.addItem("Select table...");

                //SELECT name FROM sqlite_master WHERE type = "table"

                Connection temp_db_table = db_reference.get_database_structure();
                String sql = "SELECT name FROM sqlite_master WHERE type = 'table';";
                try
                {
                    Statement stmt = temp_db_table.createStatement();
                    stmt.execute(sql);
                    ResultSet rs = stmt.executeQuery(sql);

                //*

                while(rs.next())
                {
                    comboBox.addItem(rs.getString(1));
                }
                comboBox.setSelectedIndex(0);
                //*/

                }
                catch (SQLException esd) 
                    {
                    // TODO Auto-generated catch block		
                    esd.printStackTrace();
                    }
            }
        }
	
	public void load_table_content()
        {
            if(comboBox.getSelectedItem() != null && !comboBox.getSelectedItem().equals("Select table...") && table != null)
            {
                last_search = "";
                btnBack.setVisible(false);
                btnPrint.setVisible(false);
                btnRemoveRecord.setVisible(false);

                System.out.println(comboBox.getSelectedItem().toString());
                /* vyhledat databazovou tabulku */
                Connection db_table =  db_reference.get_database_structure();

                /* table not found */
                if(db_table != null)
                {
                    try
                    {
                            /* load record item keys */
                            ArrayList<String> array = new ArrayList<String>();

                            String sql = "SELECT * FROM '"+comboBox.getSelectedItem().toString()+"';";

                                    Statement stmt = db_table.createStatement();
                                    stmt.execute(sql);
                                    ResultSet rs    = stmt.executeQuery(sql);
                                    ResultSetMetaData rsmd = rs.getMetaData();
                                    int i = 1;

                                    /* naateni nazvu polozek databazoveho zaznamu */
                                    record_choose_id.removeAllItems();
                                    while(i <= rsmd.getColumnCount())
                                    {
                                            array.add(rsmd.getColumnName(i));
                                            record_choose_id.addItem(rsmd.getColumnName(i));
                                            i++;
                                    }

                                    record_choose_id.setSelectedIndex(0);

                                    DefaultTableModel dtm = new DefaultTableModel(0,0)
                                    {
                                                    private static final long serialVersionUID = 1L;

                                                    @Override
                                                    public boolean isCellEditable(int row, int column) 
                                                    {
                                                        return false;
                                                    }
                                    };
                            dtm.setColumnIdentifiers(array.toArray());

                            array.clear();

                                    while(rs.next())
                                            {
                                            i = 1;
                                            while(i <= rsmd.getColumnCount())
                                            {
                                                    array.add(rs.getString(rsmd.getColumnLabel(i)));
                                                            i++;
                                                    }
                                            dtm.addRow(array.toArray());
                                            array.clear();
                                            } 

                                    this.table.setModel(dtm);

                                    /* automatic resizing of columns */
                                    if(dtm.getColumnCount() >= 5)
                                    {
                                            for(int index = 0; index < dtm.getColumnCount(); index++)
                                            {

                                                    table.getColumnModel().getColumn(index).setPreferredWidth((int)((getWidth()/100.0*70.0)/5.0));
                                            }
                                    }
                                    else
                                    {
                                            for(int index = 0; index < dtm.getColumnCount(); index++)
                                                {
                                                     table.getColumnModel().getColumn(index).setPreferredWidth((int)((getWidth()/100.0*70.0)/((double)dtm.getColumnCount())));
                                                }
                                    }
                            }
                            catch (SQLException esd) 
                                    {
                                    // TODO Auto-generated catch block		
                                    esd.printStackTrace();
                                    }
                    }
                    else
                    {
                            table.setModel(new DefaultTableModel(0, 0));
                            System.out.println("clean");
                    }
                    /* load rekords  */
                }
                else
                {
                    table.setModel(new DefaultTableModel(0, 0));
                    System.out.println("clean");
                }
	    }
}
