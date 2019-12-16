package database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @author HORACEK
 *
 */
public final class database_interface 
{
	private Connection db;
        private String csv_address;
        
	public database_interface(String db_address)
	{
            this.csv_address = new String();
            
		System.out.println("Object created");
		
		try 
		{
                    System.out.println(db_address);
                    db = DriverManager.getConnection("jdbc:sqlite:"+db_address);
                        
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Connection to SQLite has been established.");

                String db_file_backup_addr = this.get_record_item("settings", "network", "db_file_backup_address", "ID");
                Timer timer = new Timer();
                
                timer.scheduleAtFixedRate(new TimerTask() 
                {
                    @Override
                    public void run() 
                    {
                        if(db_file_backup_addr.length() > 0)
                        {
                            //String file_name = new SimpleDateFormat("yyyy-MM-dd--HH-mm-ss").format(Calendar.getInstance().getTime());
                            //file_name = "db_backup_"+file_name+".db";

                            datebase_backup(db_file_backup_addr+"/db_backup.db");
                        }
                    }
              }, 24*60*60*1000, 24*60*60*1000); //every 24 hours
	}
        
        public void set_csv_address(String csv_address)
        {
            this.csv_address = csv_address;
        }
        
	public Connection get_database_structure()
	{
		return this.db;
	}
	
	public void set_database_structure(Connection db)
	{
		this.db = db;
	}

	synchronized public boolean db_query(String sql)
	{
		try 
		{
                    Statement stmt = this.db.createStatement();
                    System.out.println(sql);
                    stmt.execute(sql);
		} 
		catch (SQLException e) 
		{
			return false;
		}
		
		return true;
	}
	
	synchronized public boolean remove_db_table(String name)
	{			
		Statement stmt;
		try 
		{
			stmt = this.db.createStatement();
			stmt.execute("DROP TABLE'"+name+"';");
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block		
			e.printStackTrace();
			return false;
		}
	}
	
	synchronized public boolean add_record(String db_table, String table_items, String data)
	{
		String sql = "INSERT INTO '"+db_table+"' ("+table_items+") VALUES ("+data+");";
		System.out.println(sql);
		Statement stmt;
		try 
		{
			stmt = this.db.createStatement();
			stmt.execute(sql);
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block		
			e.printStackTrace();
			return false;
		}
	}

	synchronized public boolean remove_record(String db_table, String key, String record)
	{
		String sql = "DELETE FROM "+db_table+" WHERE "+key+" = '"+record+"';";
		System.out.println(sql);
		Statement stmt;
		try 
		{
			stmt = this.db.createStatement();
			stmt.execute(sql);
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block		
			e.printStackTrace();
			return false;
		}
	}
	
	synchronized public String get_record(String db_table, String key, String record)
	{
		String sql = "SELECT * FROM "+db_table+" WHERE "+key+" = '"+record+"';";
		
		Statement stmt;
		try 
		{
			stmt = this.db.createStatement();
			stmt.execute(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			String result = "";
			
			while(rs.next())
			{
				int i = 1;
				while(i <= rsmd.getColumnCount())
				{
					if(i!=1)
					{
						result += ", "; 
					}
					result += "'"+ rs.getString(i)+"'";
					i++;
				}
			}
			
			return result;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block		
			e.printStackTrace();
			return null;
		}
	}

	synchronized public String get_record_item(String db_table, String record, String item, String key)
	{
		String sql = "SELECT "+item+" FROM "+db_table+" WHERE  "+key+" = '"+record+"';";
		System.out.println(sql);
		Statement stmt;
		
		try 
		{
			stmt = this.db.createStatement();
			stmt.execute(sql);
			ResultSet rs    = stmt.executeQuery(sql);
			if (rs.next())
			{
				return rs.getString(1);
			}
			else
			{
				return null;
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block		
			e.printStackTrace();
			return null;
		}
	}
	
	synchronized public boolean set_record_item(String db_table, String record, String key, String item, String new_data)
	{
		String sql = "UPDATE "+db_table+" SET "+item+" = '"+new_data+"' WHERE "+record+" = '"+key+"';";	
		System.out.println(sql);
		Statement stmt;
		
		try 
		{
			stmt = this.db.createStatement();
			stmt.execute(sql);
			
			return true;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block		
			e.printStackTrace();
			return false;
		}
	}

        private void fill_array(char[] array_1, char[] array_2, int start, int end)
        {
            int i = start;
            while(i < end)
            {
                array_1[i] = array_2[i];
                System.out.print(array_1[i]);
                i++;
            }
            System.out.print("\n");
        }
        
        private boolean database_file_copy(String input_address, String output_address)
        {
            InputStream is;
	    OutputStream os;
            
	    try 
            {
	        is = new FileInputStream(input_address);
	        os = new FileOutputStream(output_address);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) 
                {
	            os.write(buffer, 0, length);
	        }
	        is.close();
	        os.close();   
	    } 
	    catch(IOException err)
	    {
                return false;
	    }
            
            return true;
        }
        
        public boolean datebase_restore(String input_address)
        {
               return database_file_copy(input_address, "./db/db_content.db");
        }
        
        public boolean datebase_backup(String output_address)
        {
            return database_file_copy("./db/db_content.db", output_address);
        }
        
        synchronized public boolean generate_output_csv(String id, String table, String temperature, String humidity)
        {
            try 
            {
                String sql = "SELECT * FROM '"+table+"' WHERE ID='"+id+"';";
                System.out.println(sql);
                
                Statement stmt = this.db.createStatement();
                stmt.execute(sql);
                ResultSet rs = stmt.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();

                
                
                if(rs.next())
                {
                    short frame_kind;
                
                    if (table.equals("glued_frame_582"))
                    {
                        frame_kind = 2;
                    }
                    else if (table.equals("glued_frame_581"))
                    {
                        frame_kind = 1;
                    }
                    else if (table.equals("glued_frame_AU326_1"))
                    {
                        frame_kind = 3;
                    }
                    else
                    {
                        return false;
                    }
                    
                    Object param[] = new Object[18];

                    
/*¨
** Read csv parameters from database
** 0 - frame code*
** 1 - frame order*
** 2 - birth label content*
** 3 - glue application date time*
** 4 - left cover code*
** 5 - right cover code*
** 6 - pa60r time*
** 7 - csv file name*
** 8 - formated date*
** 9 - formated time*
** 10 - pa30 primer*
** 12 - primer batch
** 11 - primer part number
** 15 - primer fill date
** 16 - primer fill time
** 13 - primer expiration date
** 14 - primer expiration time
** 17 - ID
*/

                 

                    int i = 1;
                    while(i <= rsmd.getColumnCount())
                    {   
                        if(i == 2)
                        {
                            param[0] = new char[rs.getString(rsmd.getColumnLabel(i)).length()];
                            fill_array((char[]) param[0], rs.getString(rsmd.getColumnLabel(i)).toCharArray(), 0 , rs.getString(rsmd.getColumnLabel(i)).length()); 
                        }
                        else if(i == 1)
                        {
                            param[17] = new char[rs.getString(rsmd.getColumnLabel(i)).length()];
                            fill_array((char[]) param[17], rs.getString(rsmd.getColumnLabel(i)).toCharArray(), 0 , rs.getString(rsmd.getColumnLabel(i)).length()); 
                        }
                        else if(i == 3)
                        {
                            param[1] = new char[rs.getString(rsmd.getColumnLabel(i)).length()];
                            fill_array((char[]) param[1], rs.getString(rsmd.getColumnLabel(i)).toCharArray(), 0, rs.getString(rsmd.getColumnLabel(i)).length()); 
                        }
                        else if(i == 7)
                        {
                            param[10] = new char[rs.getString(rsmd.getColumnLabel(i)).length()];
                            fill_array((char[]) param[10], rs.getString(rsmd.getColumnLabel(i)).toCharArray(), 0, rs.getString(rsmd.getColumnLabel(i)).length()); 
                        }
                        
                        
                        if (frame_kind == 2)
                        {
                            if(i == 13)
                            {
                                param[3] = new char[rs.getString(rsmd.getColumnLabel(i)).length()];
                                fill_array((char[]) param[3], rs.getString(rsmd.getColumnLabel(i)).toCharArray(), 0, rs.getString(rsmd.getColumnLabel(i)).length());  
                            }
                            else if(i == 14 ||i == 15)
                            {
                                param[i-10] = new char[rs.getString(rsmd.getColumnLabel(i)).length()];
                                fill_array((char[]) param[i-10], rs.getString(rsmd.getColumnLabel(i)).toCharArray(), 0, rs.getString(rsmd.getColumnLabel(i)).length()); 
                            }
                        }
                        else if(frame_kind == 1 || frame_kind == 3)
                        {
                            if(i == 6)
                            {
                                param[6] = new char[rs.getString(rsmd.getColumnLabel(i)).length()];
                                fill_array((char[]) param[6], rs.getString(rsmd.getColumnLabel(i)).toCharArray(), 0, rs.getString(rsmd.getColumnLabel(i)).length()); 
                            }
                        }
               
                        
                        i++;
                    }
                    
                    /* build mcode */
                    if(frame_kind == 1)
                    {
                        String temp = ("AU581*1762975*"+new String(((char[])param[6]))+"*"+new String(((char[])param[1])));
                        param[2] = temp.toCharArray(); 
                    }
                    else if(frame_kind == 2)
                    {
                        String temp = ("AU582*1762976*"+new String(((char[])param[3]))+"*"+new String(((char[])param[1])));
                        param[2] = temp.toCharArray();
                    }
                    else if (frame_kind == 3)
                    {
                        String temp = ("AU326-1*1768362*"+new String(((char[])param[6]))+"*"+new String(((char[])param[1])));
                        param[2] = temp.toCharArray();
                    }
                        
                    /* pars primer information */
                    i = 0;
                    int j = 0;
                    int part = 0;
                    
                    
                    /* twice entering, firest count characters to copy, secound allocation amount of memory and copy characters */
                    while(i < ((char[])param[10]).length-1)
                    {
                        if(((char[]) param[10])[i] == '*')
                        {
                            int k = 0;
                            if(part == 2)
                            {
                                param[part+11] = new char[10];
                                while(((char[])param[10])[j] != ' ')
                                {
                                   ((char[])param[part+11])[k] = ((char[])param[10])[j];
                                   j++;
                                   k++;
                                }
                                
                                j++;
                                part++;
                                k = 0;
                                param[part+11] = new char[5];
                                while(j<i)
                                {
                                   ((char[])param[part+11])[k] = ((char[])param[10])[j];
                                   j++;
                                   k++;
                                }
                            }
                            else
                            {
                                param[part+11] = new char[i-j];
                                
                                while(j<i)
                                {
                                    ((char[])param[part+11])[k] = ((char[])param[10])[j];
                                    j++;
                                    k++;
                                }
                            }
                            j = i+1;
                            part++;
                        }
                        
                       
                            
                        i++;
                    }
                    
                    /* pars csv file name */
                        param[7] = new char[22];
                        param[8] = new char[8];
                        param[9] = new char[6];
                        
                        int m=0;
                        int  n = 0;

                        while(n < "172_".length())
                        {
                            ((char[])param[7])[m] = ("172_".toCharArray())[n];
                           
                            m++;
                            n++;
                        }
                        
                        n = 6;
                        while(n < 10)
                        {
                            ((char[])param[7])[m] = ((char[])param[(frame_kind == 1 || frame_kind == 3) ? 6 : 3])[n];
                            ((char[])param[8])[m-4] = ((char[])param[(frame_kind == 1 || frame_kind == 3) ? 6 : 3])[n];
                            m++;
                            n++;
                        }
                        
                        n = 3;
                        while(n < 5)
                        {
                            ((char[])param[7])[m] = ((char[])param[(frame_kind == 1 ||frame_kind == 3) ? 6 : 3])[n];
                            ((char[])param[8])[m-4] = ((char[])param[(frame_kind == 1 || frame_kind == 3) ? 6 : 3])[n];
                            m++;
                            n++;
                        }
                        
                        n = 0;
                        while(n < 2)
                        {
                            ((char[])param[7])[m] = ((char[])param[(frame_kind == 1 || frame_kind == 3) ? 6 : 3])[n];
                            ((char[])param[8])[m-4] = ((char[])param[(frame_kind == 1 || frame_kind == 3) ? 6 : 3])[n];
                            m++;
                            n++;
                        }
                        
                        n = 11;
                        int k = m;
                        while(n < 13)
                        {
                            ((char[])param[7])[m] = ((char[])param[(frame_kind == 1 || frame_kind == 3) ? 6 : 3])[n];
                            ((char[])param[9])[m-k] = ((char[])param[(frame_kind == 1 || frame_kind == 3) ? 6 : 3])[n];
                            m++;
                            n++;
                        }
                        
                        n = 14;
                        while(n < 16)
                        {
                            ((char[])param[7])[m] = ((char[])param[(frame_kind == 1 || frame_kind == 3) ? 6 : 3])[n];
                            ((char[])param[9])[m-k] = ((char[])param[(frame_kind == 1 || frame_kind == 3) ? 6 : 3])[n];
                            m++;
                            n++;
                        }

                        n = 0;
                        while(n < "00".length())
                        {
                            ((char[])param[7])[m] = ("00".toCharArray())[n];
                            ((char[])param[9])[m-k] = ("00".toCharArray())[n];
                            m++;
                            n++;
                        }

                        n = 0;
                        while(n < ".csv".length())
                        {
                            ((char[])param[7])[m] = (".csv".toCharArray())[n];
                            m++;
                            n++;
                        }                    
                    
                    OutputStreamWriter writer;
                    if(csv_backup_synchronizer.path_exists(csv_address) == true)
                    {
                        writer = new OutputStreamWriter(new FileOutputStream(csv_address+"/"+new String(((char[])param[7]))),"UTF-8");
                    }
                    else
                    {
                        writer = new OutputStreamWriter(new FileOutputStream("./csv_backup/"+new String(((char[])param[7]))),"UTF-8");
                    }
                    
                    for(i = 1; i <= 2578; i++)
                    {
                        switch(i)
                        {
                            case 1:
                                writer.write(((char[])param[8]));
                                break;
                            case 2:
                                writer.write(((char[])param[9]));
                                break;
                            case 3:
                                writer.write("00000172");
                                break;
                            case 4:
                                writer.write("00000172");
                                break;
                            case 5:
                                if(frame_kind == 1)
                                {
                                    writer.write("1762975A");
                                }
                                else if (frame_kind == 2)
                                {
                                    writer.write("1762976A");
                                }
                                else if(frame_kind == 3)
                                { 
                                    writer.write("1768362A");
                                }
                                break;
                            case 6:
                                writer.write(((char[])param[17]));
                                break;
                            case 7:
                                writer.write("OK");
                                break;
                            case 9:
                                writer.write("1");
                                break;
                            case 10:
                                writer.write("DA");
                                break;        
                            case 8:
                                writer.write("200");
                                break;
                            case 1263:
                                writer.write(((char[])param[0]));
                                break;
                            case 1268:
                                 writer.write(((char[])param[2]));
                                break;
                            case 1288:
                                if(frame_kind == 2)
                                {
                                    writer.write(((char[])param[4]));
                                }
                               break;
                            case 1273:
                                if (frame_kind == 2)
                                {
                                    writer.write(((char[])param[5]));
                                }
                                break;
                            case 1278:
                                if (frame_kind == 2)
                                {
                                    writer.write(((char[])param[3]));
                                }
                                break;
                            case 1283:
                                if (frame_kind == 2)
                                {
                                    writer.write("0");
                                }
                                break;
                            case 265:
                                writer.write(temperature);
                                break;
                            case 269:
                                writer.write(humidity);
                                break;
                            case 1333:
                                writer.write("0");
                            case 2553:
                                writer.write(((char[])param[12]));
                                break;
                            case 2548:
                                writer.write(((char[])param[11]));
                                break;
                            case 2563:
                                writer.write(((char[])param[15]));
                                break;
                            case 2568:
                                writer.write(((char[])param[16]));
                                break;
                            case 2573:
                                writer.write(((char[])param[13]));
                                break;
                            case 2578:
                                writer.write(((char[])param[14]));
                                break;
                        }
                        
                        writer.write(',');
                    }

                    writer.flush();
                    writer.close();
                }
                else
                {
                    return false;
                }

                return true;
            } 
    
            catch(Exception e)
            {
                e.printStackTrace();
                return false;
            }
            // TODO Auto-generated catch block

        }
}
