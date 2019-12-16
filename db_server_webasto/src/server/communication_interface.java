package server;

import java.util.ArrayList;
import database.database_interface;
import db_server_webasto.share;
import gui.traceability_view;

public class communication_interface extends share
{
    private final int cmd_get_table_record;
    private final int cmd_get_table_record_item;

    private final int cmd_set_table_record_item;
    private final int cmd_add_table_record;
    private final int cmd_remove_table_record;
    private final int cmd_ping;
    private final int cmd_pa10b_state;
    private final int cmd_pa20b_state;
    private final int cmd_pa30r_state;
    private final int cmd_pa60r_state;
    private final int cmd_pa30b_state;
    private int cmd_fs_state;
    private final int cmd_generate_csv;
    private final int cmd_sql_query;
    
    private traceability_view traceability_reference;
    
    database_interface database_structure;
    
	public communication_interface()
	{
            cmd_ping= "PING".hashCode();
            cmd_remove_table_record = "REMOVE_TABLE_RECORD".hashCode();
            cmd_add_table_record = "ADD_TABLE_RECORD".hashCode();
            cmd_set_table_record_item = "SET_TABLE_RECORD_ITEM".hashCode();
            cmd_get_table_record_item = "GET_TABLE_RECORD_ITEM".hashCode();
            cmd_get_table_record = "GET_TABLE_RECORD".hashCode();
            cmd_pa10b_state = "PA10B_STATE".hashCode();
            cmd_pa20b_state = "PA20B_STATE".hashCode();
            cmd_pa30r_state = "PA30R_STATE".hashCode();
            cmd_pa60r_state = "PA60R_STATE".hashCode();
            cmd_pa30b_state = "PA30B_STATE".hashCode();
            cmd_generate_csv = "GENERATE_CSV".hashCode();
            cmd_sql_query = "SQL".hashCode();
	}

	 private ArrayList<String> get_command(String in)
	 {
	     int index = 1;
	     ArrayList<String> command = new ArrayList<String>();
	     String temp = new String();
	     temp += in.charAt(0);
	     
	     while(index < in.length())
	     {
	         if(in.charAt(index) == '~' && in.charAt(index-1) != '\\'  )
	         {
	            command.add(temp);
	            temp = "";
	         }
	         else
	         {
	             temp += in.charAt(index);
	         }
	       
	         index ++;
	     }
             
	     command.add(temp);
             
         return command;
	}
	 
	 public void set_traceability_reference(traceability_view traceability_reference)
	 {
		 this.traceability_reference = traceability_reference;
	 }
	 
	 public void set_database_reference(database_interface database_structure)
	 {
	      this.database_structure = database_structure;
	 } 
	   
	 public database_interface get_database_reference()
	 {
	     return this.database_structure;
	 }
	 
	public String parse_command(String command)
	{ 
	     ArrayList<String> command_list = get_command(command);
	     int sub_command_hash = command_list.get(0).hashCode();
	  
	     System.out.println(" input command: "+command_list.get(0)+" "+ command_list.get(0).hashCode()+ " ");
	        
	     
	     if(sub_command_hash == cmd_get_table_record)
	     {
	         if (command_list.size() == 4)
	         {
	             String temp = this.database_structure.get_record(command_list.get(1), command_list.get(2),command_list.get(3));
	             if(temp.equals(""))
	             {
	                    return "ERROR";
	                }
	                else
	                {
	                    return temp;
	                }
	            }
	            else
	            {
	                return "ERROR";
	            }
	        }
	        else if(sub_command_hash == cmd_get_table_record_item)
	        {
	            if (command_list.size() == 5)
	            {
	                String temp = this.database_structure.get_record_item(command_list.get(1), command_list.get(2), command_list.get(3),command_list.get(4));
	                System.out.println("data out: " + temp);
	                if(temp == null)
	                {
	                    return "ERROR";
	                }
	                else
	                {
	                    return temp;
	                }
	            }
	            else
	            {
	                return "ERROR";
	            }
	        }
	        else if(sub_command_hash == cmd_set_table_record_item)
	        {
	           if (command_list.size() == 6)
	           {
	                if(this.database_structure.set_record_item(command_list.get(1), command_list.get(2), command_list.get(3), command_list.get(4),command_list.get(5)) == true)
	                {
	                    return "OK";
	                }
	                else
	                {
	                    return "ERROR";
	                }
	           }
	           else
	           {
	               return "ERROR";
	           }
	        }
	        else if(sub_command_hash == cmd_add_table_record)
	        {
	        	
	        	//insert into enviroment (date_time, temperature, humidity) values ('13.10.2017*13:21', '12.56', '65.0');
	           if (command_list.size() == 4)
	           {
	                if(this.database_structure.add_record(command_list.get(1), command_list.get(2), command_list.get(3)) == true)
	                {
	                	System.out.println("OK");
	                    return "OK";
	                    
	                }
	                else
	                {
	                	System.out.println("NOT OK");
	                    return "ERROR";
	                }
	           }
	           else
	           {
	        	   System.out.println("NOT OK - "+command_list.size());
	               return "ERROR";
	           }
	        }
	        else if(sub_command_hash == cmd_remove_table_record)
	        {
	           if (command_list.size() == 4)
	           {
	                if(this.database_structure.remove_record(command_list.get(1), command_list.get(2),command_list.get(3)) == true)
	                {
	                    return "OK";
	                }
	                else
	                {
	                    return "ERROR";
	                }
	           }
	           else
	           {
	               return "ERROR";
	           }
	        }
	        else if (sub_command_hash == cmd_pa10b_state)
	        {
                    this.traceability_reference.pa10b_set_station_labels(command_list.get(1),command_list.get(2),command_list.get(3), command_list.get(4), command_list.get(5));
	            return "OK";
	        }
	        else if(sub_command_hash == cmd_pa20b_state)
	        {
	        	this.traceability_reference.pa20b_set_station_labels(command_list.get(1), command_list.get(2));
	        	return "OK";
                }
	        else if(sub_command_hash == cmd_pa30r_state)
	        {
	        	this.traceability_reference.pa30r_set_station_labels(command_list.get(1), command_list.get(2), command_list.get(3), command_list.get(4), command_list.get(5));
	        	return "OK";
	        }
	        else if (sub_command_hash == cmd_pa60r_state)
	        {
	        	this.traceability_reference.pa60r_set_station_labels(command_list.get(1), command_list.get(2), command_list.get(3), command_list.get(4), command_list.get(5));
	        	return "OK";
	        }
	        else if(sub_command_hash == cmd_fs_state)
	        {
	        	this.traceability_reference.fs_set_station_labels(command_list.get(1), command_list.get(2), command_list.get(3), command_list.get(4), command_list.get(5), command_list.get(6));
	        	return "OK";
	        }
                else if (sub_command_hash == cmd_pa30b_state)
                {
                    this.traceability_reference.pa30b_set_station_labels(command_list.get(1), command_list.get(2), command_list.get(3), command_list.get(4), command_list.get(4), command_list.get(5));
                    return "OK";
                }
	        else if(sub_command_hash == cmd_sql_query)
	        {
                    if(this.database_structure.db_query(command_list.get(1)))
                    {
                        return "OK";
                    }
                    else
                    {
                        return "ERROR";
                    }
	        }
                else if (sub_command_hash == cmd_generate_csv)
                {
                    if(this.database_structure.generate_output_csv(command_list.get(1), command_list.get(2), command_list.get(3), command_list.get(4)))
                    {
                        return "OK";
                    }   
                    else
                    {
                        return "ERROR";
                    }
                }
	        else if (sub_command_hash == cmd_ping)
	        {
                    /* test pripojeni databaze na strane klienta */
                    return "OK";
	        }
	        else
	        {
	            /* osetreni proti neznamemu prikazu */
	           return "ERROR";
	        } 
	    }
}
