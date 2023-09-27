import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UploadData{

	public String insertEmployeeData(int employeeId, String employeeName, String jobTitle, String dateOfBirth, String joiningDate, double salary, int departmentId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		
		String url = "jdbc:mysql://localhost:3306/IT?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String password="srikar4103";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String template = "INSERT INTO Employees VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement inserter = con.prepareStatement(template);
            
            inserter.setInt(1, employeeId);
            inserter.setString(2, employeeName);
            inserter.setString(3, jobTitle);
            inserter.setDate(4, java.sql.Date.valueOf(dateOfBirth));
            inserter.setDate(5, java.sql.Date.valueOf(joiningDate));
            inserter.setDouble(6, salary);
            inserter.setInt(7, departmentId);

            inserter.executeUpdate();
            inserter.close();
            
            return "Record Added Successfully in Employee Table";
        } catch (SQLException e) {
        	 e.printStackTrace();
             return "Error: Unable to insert data. \n" + e.getMessage();
        }
    }
	
	public String insertDepartmentData(int departmentId, String departmentName, String location){	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		
		String url = "jdbc:mysql://localhost:3306/IT?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String password="srikar4103";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String template = "INSERT INTO Department VALUES (?, ?, ?)";
            PreparedStatement inserter = con.prepareStatement(template);

            inserter.setInt(1, departmentId);
            inserter.setString(2, departmentName);
            inserter.setString(3, location);

            inserter.executeUpdate();
            inserter.close();

            return "Record Added Successfully in Depratment Table";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Unable to insert data. \n" + e.getMessage();
        }
    }

}