import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


public class Q9 {
	private static Properties getConnectionData(){
		Properties props = new Properties();
		
		String fileName = "src/main/java/db.properties";
		
		try(FileInputStream in = new FileInputStream(fileName)){
			props.load(in);
		}catch(IOException ex) {
			Logger lgr = Logger.getLogger(Q9.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return props;
	}
	
	public static void main(String args[]) {
		Properties props = getConnectionData();
		String url = props.getProperty("db.url");
		String usr = props.getProperty("db.user");
		String passwd = props.getProperty("db.passwd");
		
		Scanner sc = new Scanner(System.in);
		
		try(Connection con = DriverManager.getConnection(url,usr,passwd)){
			try(Statement st = con.createStatement()){
				con.setAutoCommit(false);
				st.executeUpdate("DELETE from Department where DepartmentID = 104");
				st.executeUpdate("INSERT INTO Department VALUES (104, 'Management', 'New York')");
				
				ResultSet rs = st.executeQuery("SELECT EmployeeID FROM Employees ORDER BY JoiningDate LIMIT 3");
				List<Integer> seniorEmployeeIDs = new ArrayList<>();
				while (rs.next()) {
				    seniorEmployeeIDs.add(rs.getInt("EmployeeID"));
				}
				
				// Move each senior employee to the "Management" department
				for (int employeeID : seniorEmployeeIDs) {
					System.out.println("Employee with ID "+employeeID+" is being interviewed");
				    st.addBatch("UPDATE Employees SET DepartmentID = 104 WHERE EmployeeID = " + employeeID);
				    System.out.println("Employee with ID "+employeeID+" has passed the interviewed");
				}
				st.executeBatch();
                System.out.println("All changes are succesful");
				con.commit();
			}catch (SQLException ex) {
        		try {
        			ex.printStackTrace();
        			con.rollback();
        		} catch (SQLException ex1) {

        			Logger lgr = Logger.getLogger(Q9.class.getName());
        			lgr.log(Level.WARNING, ex1.getMessage(), ex1);
        		}
        	}
		}catch(SQLException ex){
			Logger lgr = Logger.getLogger(Q9.class.getName());
			lgr.log(Level.SEVERE,ex.getMessage(),ex);
		}
		sc.close();
	}
}
