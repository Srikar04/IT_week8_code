public class HtmlGenerator_2 {
    public static String getInputHtml() {
        return "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <title>Question 10</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <h1>Query for Information</h1>\n" +
            "\n" +
            "    <form action=\"Q10_2\" method=\"post\">\n" +
            "        <label for=\"age\">Enter Age:</label>\n" +
            "        <input type=\"number\" id=\"age\" name=\"age\">\n" +
            "        <br><br>\n" +
            "\n" +
            "        <label for=\"date\">Enter Date (yyyy-MM-dd):</label>\n" +
            "        <input type=\"date\" id=\"date\" name=\"date\">\n" +
            "        <br><br>\n" +
            "\n" +
            "        <button type=\"submit\">Submit</button>\n" +
            "    </form>\n" +
            "\n" +
            "    <script>\n" +
            "        document.querySelector('form').addEventListener('submit', function (e) {\n" +
            "			 var submit = true;\n"+	
            "            var ageInput = document.getElementById('age');\n" +
            "            var dateInput = document.getElementById('date');\n" +
            "            var validationMessage = document.getElementById('validationMessage');\n" +
            "            var age = parseInt(ageInput.value);\n" +
            "            var date = dateInput.value;\n" +
            "\n" +
            "            // Check if age is greater than zero\n" +
            "            if (age > 0) {\n" +
            "                // Check if the date is not in the future\n" +
            "                var currentDate = new Date();\n" +
            "                var enteredDate = new Date(date);\n" +
            "\n" +
            "                if (enteredDate > currentDate) {\n" +
            "					 submit = false;\n"+		
            "                    alert(\"Date should not be in the future.\");\n" +
            "                }\n" +
            "            } else {\n" +
            "					 submit = false;\n"+		
            "                 alert(\"Age should be greater than zero.\");\n" +
            "            }\n" +
            "            // Prevent the default form submission\n" +
            "			 if(!submit)\n"+
            "            e.preventDefault();\n" +
            "        });\n" +
            "    </script>\n";
    }
}
