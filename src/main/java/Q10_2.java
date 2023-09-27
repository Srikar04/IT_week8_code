import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/Q10_2")
public class Q10_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(HtmlGenerator_2.getInputHtml());
		out.println("</body>\n" +
            "</html>");
		out.close();
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(HtmlGenerator_2.getInputHtml());
		
		int age = Integer.parseInt(request.getParameter("age"));
		String date = request.getParameter("date");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/IT?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String password="srikar4103";
		
		try(Connection con = DriverManager.getConnection(url,user,password)){
			String query = "SELECT * FROM Employees WHERE JoiningDate > '" + date+ "' AND "
                    + "DATEDIFF(CURDATE(), DateOfBirth) / 365 >= " + age;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try (Statement st = con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                out.append("<ul>");
                while (rs.next()) {
                    Date dob = rs.getDate(4);
                    String formattedDOB = dateFormat.format(dob);
                    Date joinDate = rs.getDate(5);
                    String formattedJoinDate = dateFormat.format(joinDate);
                    out.append("<li>");
                    out.append(String.format("EmployeeId: %d, EmployeeName: %s, JobTitle: %s, DateofBirth: %s, JoiningDate: %s, Salary: %d, DepartmentId:%d<br>",
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            formattedDOB,
                            formattedJoinDate,
                            rs.getInt(6),
                            rs.getInt(7)
                    ));
                    out.append("</li>");
                    out.append("<br>");
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
		}catch(SQLException ex){
			Logger lgr = Logger.getLogger(Q8.class.getName());
			lgr.log(Level.SEVERE,ex.getMessage(),ex);
		}
		out.println("</body>\n" +
	            "</html>");
		out.close();
	}
}
