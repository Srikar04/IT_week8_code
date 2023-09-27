import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Q10")
public class Q10 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response ) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(HtmlGenerator.getInputHtml());
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    
	    String entityType = request.getParameter("entityType");
	    
	    if (entityType.equals("Department")) {
	    	String deptIdStr = request.getParameter("departmentID");
	        int deptId = Integer.parseInt(deptIdStr);
	        String deptName = request.getParameter("departmentName");
	        String location = request.getParameter("location");
	        
	        // Create an instance of LoadData and insert department data.
	        UploadData dataLoader = new UploadData();
	        String resultMessage = dataLoader.insertDepartmentData(deptId, deptName, location);

	        out.println(HtmlGenerator.getInputHtml());
	        out.println("<p>" + resultMessage + "</p></body>\n" +
	            "</html>");
	    }else if (entityType.equals("Employee")) {
	        try {
	            int employeeId = Integer.parseInt(request.getParameter("employeeID"));
	            String employeeName = request.getParameter("employeeName");
	            String jobTitle = request.getParameter("jobTitle");
	            String dateOfBirthStr = request.getParameter("dateOfBirth");
	            String joiningDateStr = request.getParameter("joiningDate");
	            double salary = Double.parseDouble(request.getParameter("salary"));
	            int departmentId = Integer.parseInt(request.getParameter("departmentID"));

	            UploadData dataLoader = new UploadData();
	            String insertionResult = dataLoader.insertEmployeeData(employeeId, employeeName, jobTitle, dateOfBirthStr, joiningDateStr, salary, departmentId);

	            out.println(HtmlGenerator.getInputHtml());
	            out.println("<p>" + insertionResult + "</p>");
	        } catch (NumberFormatException e) {
	            out.println(HtmlGenerator.getInputHtml());
	            out.println("<p>Error: Employee ID, Salary, and Department ID must be valid numbers.</p>");
	        }
	    }else {
	    	out.println("Invalid Table name. Please try again.");
	    }
	    out.close();
	}
}
