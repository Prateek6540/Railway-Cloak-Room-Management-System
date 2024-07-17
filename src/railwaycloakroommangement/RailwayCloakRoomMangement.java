/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package railwaycloakroommangement;
import java.util.*;
import java.time.*;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author prate
 */
public class RailwayCloakRoomMangement {
    Connection connection = null;
    PreparedStatement prp = null;
    ResultSet result =null;
    
    public LocalDateTime convert_time(String data,String colName){
        LocalDateTime dateTime = LocalDateTime.parse(data.replace(" ", "T"));
         
        
        return dateTime;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
     Login l =  new Login();
     l.setVisible(true);

//          LocalDateTime currentTime = LocalDateTime.now();
        
        // Print the current date and time
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter date and time (YYYY-MM-DD HH:MM): ");
//        String dateTimeInput = scanner.nextLine();
//        
//        System.out.println("Current Date and Time: " + currentTime);
    }
    
}
