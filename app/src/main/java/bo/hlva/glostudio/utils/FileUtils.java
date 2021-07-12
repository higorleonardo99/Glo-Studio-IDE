package bo.hlva.glostudio.utils;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

/*
* Class Utils File
* @author HL Devs
*
*/

public class FileUtils {
    
    public static final String LOG_FILE = "log file error";
    
    /*
    * @method create file
    * @param parent -> parent file
    * @param fileName -> filename
    * @return File
    */
    public static File createNewFile(File parent,String fileName) {
        
        Path path = Paths.get(parent.getAbsolutePath()+File.separator+fileName);
        try {
            Files.createFile(path);
        } catch (IOException e) {
            
            Log.e(LOG_FILE,"error created file ->"+e.getMessage());
        }

        return new File(path.toAbsolutePath().toString());
    }
    
    /*
    * @method createfolder
    * @param parent -> parent folder
    * @param folderName -> foldername
    * @return File
    */
    public static File createNewFolder(File parent,String folderName){
        
        Path path = Paths.get(parent.getAbsolutePath()+File.separator+folderName);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            
            Log.e(LOG_FILE,"error created folder ->"+e.getMessage());
        }
        
        return new File(path.toAbsolutePath().toString());
    }
    
    /*
    * @method readfile
    * @param file -> file from read
    * @return StringBuilder
    */
    public static StringBuilder readFile(File file){
        
        if(file.isDirectory()) return null;
        
        BufferedReader reader;
        StringBuilder builder = new StringBuilder();
        try{
            reader = new BufferedReader(new FileReader(file));
            
            String line;
            while((line = reader.readLine()) != null){
                
                builder.append(line);
                builder.append("/n");
            }
            
            reader.close();
        }
        catch(IOException e){
            
            Log.e(LOG_FILE,"error read file ->"+e.getMessage());
        }    
        
        return builder;
    }
    
    /*
    * @method writerfile
    * @param file -> file writer
    * @param content -> content writer file
    */
    public static void writerFile(File file,String content){
        
        if(file.isDirectory()) return;
        
        BufferedWriter writer;
        try{
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.flush();
            writer.close();
        }
        catch(IOException e){
            
            Log.e(LOG_FILE,"error writer file ->"+e.getMessage());
        }
    }
    
}
