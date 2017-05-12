
import java.io.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nathasha
 */

//Logic for your address book
public class AddressBook {
    
    HashMap<String,ArrayList<String>> map;
    String fileName;
    
    //read from file and create Address Book
    public AddressBook(String path){
        
        this.fileName = path;
        try{

            BufferedReader br = new BufferedReader(new FileReader(fileName));

            map = new HashMap<>();

            // Put file read to HashMap
            String line;
            while ((line = br.readLine()) != null) {
                    String[] item = line.split(",");

                    ArrayList<String> list = new ArrayList<String>();

                    for(String h:item) list.add(h);

                    map.put(item[0], list);

            }
            

            } catch (Exception e){ 				// if file not found
                    System.out.println("File not found!");
            }
    }
    
    //search details of the requested contact in the address book
    public String search(String name){
        
	String content;
        if (!(map.get(name) == null)){
            ArrayList<String> temp = map.get(name);
            content = String.join(",", temp);
            
        } else {
            content = "Contact not Found!";
        }
        return content;
    }
    
    public String addNew(String name, String details){
 

        // Get Details from user
        String[] item = details.split(",");

        ArrayList<String> list = new ArrayList<String>();
        
        list.add(name);
        for(String h:item) list.add(h);

        // Put into the list synchronized
        
        synchronized(this){
            map.put(name, list);
        }
        
        System.out.println(name + details);

        // Write map to the file
        return name +","+ details;
 
    }
    
    public void writeToFile(){
       
        
        // Write map to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            
            System.out.println(fileName);

            for (String key:map.keySet()){

                ArrayList<String> temp = map.get(key);
                
                String content = String.join(",", temp);
                bw.write(content);
                bw.write("\n");

            }
            
            bw.close();
            System.out.println("Done");

        } catch (Exception e){              // if file not found
            System.out.println("File Error");
            System.out.println(e);
        }


        
    }
    
}


