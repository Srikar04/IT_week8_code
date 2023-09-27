public class HtmlGenerator {

	public static String getInputHtml() {
	    // Define the original HTML content
	    String originalHtml = "<!DOCTYPE html>\n" +
	        "<html>\n" +
	        "<head>\n" +
	        "    <title>Question 10</title>\n" +
	        "</head>\n" +
	        "<body>\n" +
	        "    <h1>Upload Information</h1>\n" +
	        "\n" +
	        "    <!-- Dropdown (Combo Box) for selecting Employee or Department -->\n" +
	        "    <label for=\"table\">Select Table:</label>\n" +
	        "    <select id=\"table\">\n" +
	        "        <option value=\"Employee\">Employee</option>\n" +
	        "        <option value=\"Department\">Department</option>\n" +
	        "    </select>\n" +
	        "    <br><br>\n" +
	        "\n" +
	        "    <!-- Button to show form -->\n" +
	        "    <button onclick=\"generateForm()\">Show Form</button>\n" +
	        "    <br><br>\n" +
	        "\n" +
	        "    <!-- Container for the dynamically generated form fields -->\n" +
	        "     <div id=\"formContainer\"></div>\n" +
	        "     \n" +
	        "<script>\n" +
	        "    function generateForm() {\n" +
	        "        var entityType = document.getElementById('table').value;\n" +
	        "        var formContainer = document.getElementById('formContainer');\n" +
	        "\n" +
	        "        // Clear the form container\n" +
	        "        formContainer.innerHTML = '';\n" +
	        "\n" +
	        "        // Create a new form element\n" +
	        "        var form = document.createElement('form');\n" +
	        "        form.method = 'post';\n" +
	        "        form.action = 'Q10';\n" +
	        "\n" +
	        "        // Create and append form fields based on the selected entity type\n" +
	        "        if (entityType === 'Employee') {\n" +
	        "			 createFormField(form,'Entity Type','text','entityType');"+
	        "            createFormField(form, 'Employee ID', 'number', 'employeeID');\n" +
	        "            createFormField(form, 'Employee Name', 'text', 'employeeName');\n" +
	        "            createFormField(form, 'Job Title', 'text', 'jobTitle');\n" +
	        "            createFormField(form, 'Date of Birth', 'date', 'dateOfBirth');\n" +
	        "            createFormField(form, 'Joining Date', 'date', 'joiningDate');\n" +
	        "            createFormField(form, 'Salary', 'number', 'salary');\n" +
	        "            createFormField(form, 'Department ID', 'number', 'departmentID');\n" +
	        "        } else if (entityType === 'Department') {\n" +
	        "			 createFormField(form,'Entity Type','text','entityType');"+
	        "            createFormField(form, 'Department ID', 'number', 'departmentID');\n" +
	        "            createFormField(form, 'Department Name', 'text', 'departmentName');\n" +
	        "            createFormField(form, 'Location', 'text', 'location');\n" +
	        "        }\n" +
	        "\n" +
	        "        // Create and append the submit button\n" +
	        "        var submitButton = document.createElement('input');\n" +
	        "        submitButton.type = 'submit';\n" +
	        "        submitButton.value = 'Submit';\n" +
	        "\n" +
	        "        // Append the form and submit button to the form container\n" +
	        "        form.appendChild(submitButton);\n" +
	        "        formContainer.appendChild(form);\n" +
	        "		document.getElementById('entityType').value = entityType;"+
	        "		 form.addEventListener('submit',function(e){\n"+
			"		 e.preventDefault();\n"+
			"		 if(validateForm()){\n"+
			"			form.submit();\n"+
			"		}\n"+
			"}		); \n"+
	        "    }\n" +
	        "\n" +
	        "    function createFormField(form, label, type, name) {\n" +
	        "        var labelElement = document.createElement('label');\n" +
	        "        labelElement.textContent = label;\n" +
	        "\n" +
	        "        var inputElement = document.createElement('input');\n" +
	        "        inputElement.type = type;\n" +
	        "        inputElement.id = name;\n" +
	        "        inputElement.name = name;\n" +
	        "\n" +
	        "        form.appendChild(labelElement);\n" +
	        "        form.appendChild(inputElement);\n" +
	        "        form.appendChild(document.createElement('br')); // Add line breaks between fields\n" +
	        "    }\n" +
	        "function validateForm() {\n" +
	        "    var entityType = document.getElementById('table').value;\n" +
	        "    if (entityType === 'Employee') {\n" +
	        "        var employeeID = document.getElementById('employeeID').value;\n" +
	        "        var employeeName = document.getElementById('employeeName').value;\n" +
	        "        var jobTitle = document.getElementById('jobTitle').value;\n" +
	        "        var dateOfBirth = document.getElementById('dateOfBirth').value;\n" +
	        "        var joiningDate = document.getElementById('joiningDate').value;\n" +
	        "        var salary = document.getElementById('salary').value;\n" +
	        "\n" +
	        "        var containsNumbers = /\\d/;\n" +
	        "\n" +
	        "        if (containsNumbers.test(employeeName)) {\n" +
	        "            alert('Employee Name should not contain numbers.');\n" +
	        "            return false;\n" +
	        "        }\n" +
	        "        if (containsNumbers.test(jobTitle)) {\n" +
	        "            alert('Job Title should not contain numbers.');\n" +
	        "            return false;\n" +
	        "        }\n" +
	        "        if (employeeID === '' || employeeName === '' || jobTitle === '' || dateOfBirth === '' || joiningDate === '' || salary === '') {\n" +
	        "            alert('All fields are required.');\n" +
	        "            return false;\n" +
	        "        }\n" +
	        "        return true;\n" +
	        "    } else if (entityType === 'Department') {\n" +
	        "        var deptID = document.getElementById('departmentID').value;\n" +
	        "        var deptName = document.getElementById('departmentName').value;\n" +
	        "        var location = document.getElementById('location').value;\n" +
	        "\n" +
	        "        var containsSpecialCharacters = /[^,\\-a-zA-Z0-9]/;\n" +
	        "        var containsNumbers = /\\d/;\n" +
	        "\n" +
	        "        if (containsNumbers.test(deptName)) {\n" +
	        "            alert('Department Name should not contain numbers.');\n" +
	        "            return false;\n" +
	        "        }\n" +
	        "        if (containsSpecialCharacters.test(location)) {\n" +
	        "            alert('Location should contain only letters and numbers (no special characters).');\n" +
	        "            return false;\n" +
	        "        }\n" +
	        "        if (deptID === '' || deptName === '' || location === '') {\n" +
	        "            alert('All fields are required.');\n" +
	        "            return false;\n" +
	        "        }\n" +
	        "        return true;\n" +
	        "    }\n" +
	        "}"+
	        "</script>\n";
//	        "</body>\n" +
//	        "</html>";

	    return originalHtml;
	}


}
