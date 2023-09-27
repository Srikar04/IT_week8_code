import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;

public class Q8 {
	private static Properties getConnectionData(){
		Properties props = new Properties();
		
		String fileName = "src/main/java/db.properties";
		
		try(FileInputStream in = new FileInputStream(fileName)){
			props.load(in);
		}catch(IOException ex) {
			Logger lgr = Logger.getLogger(Q8.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return props;
	}
	
	/*
	 *  Database Used : IT
	 *  Contains tables : Employees, Department
	 */
	
	public static void main(String args[]) {
		Properties props = getConnectionData();
		String url = props.getProperty("db.url");
		String usr = props.getProperty("db.user");
		String passwd = props.getProperty("db.passwd");
		
		Scanner sc = new Scanner(System.in);
		
		boolean exit = false;
		try(Connection con = DriverManager.getConnection(url,usr,passwd)){
			while (!exit) {
	            System.out.println("Select an option:");
	            System.out.println("1. Enter age and date");
	            System.out.println("2. Exit");
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    System.out.println("Enter the age:");
	                    int Age = sc.nextInt();
	                    sc.nextLine();
	                    System.out.println("Enter the Date (yyyy-MM-dd):");
	                    String dateStr = sc.nextLine();
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                    String query = "SELECT * FROM Employees WHERE JoiningDate > '" + dateStr + "' AND "
	                            + "DATEDIFF(CURDATE(), DateOfBirth) / 365 >= " + Age;
	                    try (Statement st = con.createStatement()) {
	                        ResultSet rs = st.executeQuery(query);
	                        while (rs.next()) {
	                            Date dob = rs.getDate(4);
	                            String formattedDOB = dateFormat.format(dob);
	                            Date joinDate = rs.getDate(5);
	                            String formattedJoinDate = dateFormat.format(joinDate);
	                            System.out.printf("EmployeeId: %d, EmployeeName: %s, JobTitle: %s, DateofBirth: %s, JoiningDate: %s, Salary: %d, DepartmentId:%d %n",
	                                    rs.getInt(1),
	                                    rs.getString(2),
	                                    rs.getString(3),
	                                    formattedDOB,
	                                    formattedJoinDate,
	                                    rs.getInt(6),
	                                    rs.getInt(7)
	                            );
	                        }
	                    }catch (Exception ex) {
	                        ex.printStackTrace();
	                    }
	                    break;
	                case 2:
	                    exit = true;
	                    break;
	                default:
	                    System.out.println("Invalid option. Please select again.");
	            }
	        }
		}catch(SQLException ex){
			Logger lgr = Logger.getLogger(Q8.class.getName());
			lgr.log(Level.SEVERE,ex.getMessage(),ex);
		}
		
		sc.close();
	}
}