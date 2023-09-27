
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Q7{
	private static Properties getConnectionData(){
		Properties props = new Properties();
		
		String fileName = "src/main/java/db.properties";
		
		try(FileInputStream in = new FileInputStream(fileName)){
			props.load(in);
		}catch(IOException ex) {
			Logger lgr = Logger.getLogger(Q7.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return props;
	}
	
	/*
	 *  Database Used : IT
	 *  Contains tables : Employees, Department
	 */
	
	public static void main(String[] args) {
		Properties props = getConnectionData();
		
		String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.passwd");
        
        
        Scanner sc = new Scanner(System.in);
            
        try(Connection con = DriverManager.getConnection(url,user,password)){
        	while(true) {
        		System.out.println("Enter 1 to add a record in EMPLOYEE table");
        		System.out.println("Enter 2 to add a record in DEPRATMENT table");
        		System.out.println("Enter any other to exit");
        		int choice = sc.nextInt();
        		if(choice == 1) {
        	        String template = "INSERT INTO Employees values(?,?,?,?,?,?,?)";
        	        PreparedStatement inserter = con.prepareStatement(template);
        	        
        			System.out.println("Enter the Employee Id");
        			inserter.setInt(1,sc.nextInt());
        			
        			System.out.println("Enter the Employee Name");
                    sc.nextLine(); // Consume the newline character
                    inserter.setString(2, sc.nextLine());
                    
                    System.out.println("Enter the Job Title");
                    inserter.setString(3, sc.nextLine());
                    
                    System.out.println("Enter the Date of Birth (yyyy-MM-dd)");
                    inserter.setDate(4, java.sql.Date.valueOf(sc.next()));
                    
                    System.out.println("Enter the Joining Date (yyyy-MM-dd)");
                    inserter.setDate(5, java.sql.Date.valueOf(sc.next()));
                    
                    System.out.println("Enter the Salary");
                    inserter.setDouble(6, sc.nextDouble());
                    
                    System.out.println("Enter the Department ID");
                    inserter.setInt(7, sc.nextInt());
                    
                    inserter.executeUpdate();
                    inserter.close();
        			
                    System.out.println("Record Succesfully added");
        		}else if(choice == 2) {
        			String template = "INSERT INTO Department VALUES(?, ?, ?)";
                    PreparedStatement inserter = con.prepareStatement(template);
                    
                    System.out.println("Enter the Department ID");
                    inserter.setInt(1, sc.nextInt());
                    
                    sc.nextLine();
                    
                    System.out.println("Enter the Department Name");
                    inserter.setString(2, sc.nextLine());

                    System.out.println("Enter the Department Location");
                    inserter.setString(3, sc.nextLine());
                    
                    inserter.executeUpdate();
                    
                    System.out.println("Record Added Succesfully");
                    inserter.close();
        		}
        		else{
        			break;
        		}
        	}
        	
        } catch (SQLException ex) {
        	Logger lgr = Logger.getLogger(Q7.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
        
        sc.close();
        		
	}
}