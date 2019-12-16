/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HORACEK
 */
public class csv_backup_synchronizer extends Thread
{
    private String output_address;
    private int file_count;
    
    public csv_backup_synchronizer()
    {
        file_count = 0;
    }
    
    public void set_output_address(String output_address)
    {
        this.output_address = output_address;           
    }
    
    static boolean path_exists(String path)
    {
        File f = new File(path);
        
        return (f.exists() && f.isDirectory());
    }
    
    private File[] check_direcotry_content()
    {
        File folder = new File("./csv_backup");
        return folder.listFiles();
    }
    
    private void copy(String fileName)
    {
        try 
        {
            InputStream is = new FileInputStream("./csv_backup/"+fileName);
            System.out.println();
            OutputStream os = new FileOutputStream(output_address+"/"+fileName);
            System.out.println("./csv_backup/"+fileName+" -> "+output_address+"/"+fileName);
            byte[] buffer = new byte[1024];
            int length;
            
            while ((length = is.read(buffer)) > 0) 
            {
                os.write(buffer, 0, length);
            }
            os.flush();
            is.close();
            os.close();
        } 
        catch(Exception e) 
        {
            System.out.println(e.getMessage());
        }        
    }
    
    @Override
    public void run()
    {
        try 
        {
            while(true)
            {
                File[] content = check_direcotry_content();
                file_count = content.length;
                
                while(file_count > 0)
                {
                    if (path_exists(output_address))
                    {
                        copy(content[file_count-1].getName());
                        content[file_count-1].delete();
                        file_count --;
                    }
                    else
                    {
                         sleep(100);
                    }
                }
                sleep(1000);
            }
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(csv_backup_synchronizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
