package com.bos.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminaddOIC
 */
@WebServlet("/AdminaddOIC")
public class AdminaddOIC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminaddOIC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		String roleid = (String) session.getAttribute("roleid1");
		if(roleid!=null)
		{
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

		PrintWriter out = response.getWriter();
		try {
			out.println("<!DOCTYPE html>");
			out.println("<html lang='en'>");
			out.println("<head>");
			out.println("\t<meta charset='UTF-8'>");
		    out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>");

			out.println("\t<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
			out.println("\t<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>");
			out.println("\t<link rel='stylesheet' href='OICstyle.css'>");
			out.println("\t<title>AdminHub</title>");
			
			
			
			out.println("<style>");
			out.println("    body {");
			out.println("        font-family: Arial, sans-serif;");
			out.println("        background-color: #f4f4f4;");
			out.println("        margin: 0;");
			out.println("        padding: 0;");
			out.println("        display: flex;");
			out.println("        justify-content: center;");
			out.println("        align-items: center;");
			out.println("        height: 100vh;");
			out.println("    }");
			out.println("");
			out.println("    .form-container {");
			out.println("        background-color: #fff;");
			out.println("        border-radius: 10px;");
			out.println("        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
			out.println("        padding: 20px;");
			out.println("        width: 300px;");
			out.println("    }");
			out.println("");
			out.println("    input[type=\"text\"],");
			out.println("    input[type=\"email\"],");
			out.println("    input[type=\"password\"] {");
			out.println("        width: 100%;");
			out.println("        padding: 10px;");
			out.println("        margin: 8px 0;");
			out.println("        border: 1px solid #ccc;");
			out.println("        border-radius: 5px;");
			out.println("        box-sizing: border-box;");
			out.println("    }");
			out.println("");
			out.println("    input[type=\"submit\"] {");
			out.println("        background-color: #4caf50;");
			out.println("        color: white;");
			out.println("        padding: 10px 20px;");
			out.println("        border: none;");
			out.println("        border-radius: 5px;");
			out.println("        cursor: pointer;");
			out.println("    }");
			out.println("");
			out.println("    input[type=\"submit\"]:hover {");
			out.println("        background-color: #45a049;");
			out.println("    }");
			out.println("");
			out.println("    .error-message {");
			out.println("        color: #ff0000;");
			out.println("        margin-bottom: 10px;");
			out.println("    }");
			out.println("</style>");
			out.println("</head>");
			
			out.println("<body>");
			out.println();
			out.println("\t<section id='sidebar'>");
			out.println("\t\t<a href='#' class='brand'>");
			out.println("\t\t\t<i class='bx bxs-smile'></i>");
			out.println("\t\t\t<span class='text'>ADMIN</span>");
			out.println("\t\t</a>");
			out.println("\t\t<ul class='side-menu top'>");
			out.println("\t\t\t<li>");
			out.println("\t\t\t\t<a href='Admin'>");
			out.println("\t\t\t\t\t<i class='bx bxs-shopping-bag-alt' ></i>");
			out.println("\t\t\t\t\t<span class='text'>Report</span>");
			out.println("\t\t\t\t</a>");
			out.println("\t\t\t</li>");
			out.println("\t\t\t<li class='active'>");
			out.println("\t\t\t\t<a href='Admin'>");
			out.println("\t\t\t\t\t<i class='bx bxs-shopping-bag-alt' ></i>");
			out.println("\t\t\t\t\t<span class='text'>Add new Officer in Charge</span>");
			out.println("\t\t\t\t</a>");
			out.println("\t\t\t</li>");
			out.println("\t\t</ul>");
			out.println("\t\t<ul class='side-menu'>");
			out.println("\t\t\t<li>");
			out.println("\t\t\t</li>");
			out.println("\t\t\t<li>");
			out.println("\t\t\t\t<a href='Logout' class='logout'>");
			out.println("\t\t\t\t\t<i class='bx bxs-log-out-circle' ></i>");
			out.println("\t\t\t\t\t<span class='text'>Logout</span>");
			out.println("\t\t\t\t</a>");
			out.println("\t\t\t</li>");
			out.println("\t\t</ul>");
			out.println("\t</section>");
			out.println();
			out.println("\t<section id='content'>");
			out.println("\t\t<nav>");
			out.println();
			out.println("\t\t</nav>");
			out.println("\t\t<main>");
			
			out.println("    <div class=\"form-container\">");
			out.println("        <h4>OIC Registration</h4>");
			out.println("        <form id=\"signup-form\" method='post' action='OICinfo' onsubmit=\"return validateForm()\">");
			out.println("            <div class=\"error-message\" id=\"name-error\"></div>");
			out.println("            <input type=\"text\" id=\"name\" name=\"name\" placeholder=\"Name\">");
			out.println("            <div class=\"error-message\" id=\"email-error\"></div>");
			out.println("            <input type=\"email\" id=\"email\" name=\"email\" placeholder=\"Email\">");
			out.println("            <div class=\"error-message\" id=\"password-error\"></div>");
			out.println("            <input type=\"password\" id=\"password\" name=\"password\" placeholder=\"Password\">");
			out.println("            <input type=\"submit\" value=\"Submit\">");
			out.println("        </form>");
			out.println("    </div>");
			out.println("");
			out.println("    <script>");
			out.println("        function validateForm() {");
			out.println("            var name = document.getElementById(\"name\").value;");
			out.println("            var email = document.getElementById(\"email\").value;");
			out.println("            var password = document.getElementById(\"password\").value;");
			out.println("            var nameError = document.getElementById(\"name-error\");");
			out.println("            var emailError = document.getElementById(\"email-error\");");
			out.println("            var passwordError = document.getElementById(\"password-error\");");
			out.println("            var isValid = true;");
			out.println("");
			out.println("            nameError.innerHTML = \"\";");
			out.println("            emailError.innerHTML = \"\";");
			out.println("            passwordError.innerHTML = \"\";");
			out.println("");
			out.println("            if (name === \"\") {");
			out.println("                nameError.innerHTML = \"Name is required\";");
			out.println("                isValid = false;");
			out.println("            }");
			out.println("");
			out.println("            if (email === \"\") {");
			out.println("                emailError.innerHTML = \"Email is required\";");
			out.println("                isValid = false;");
			out.println("            } else if (!isValidEmail(email)) {");
			out.println("                emailError.innerHTML = \"Invalid email format\";");
			out.println("                isValid = false;");
			out.println("            }");
			out.println("");
			out.println("            if (password === \"\") {");
			out.println("                passwordError.innerHTML = \"Password is required\";");
			out.println("                isValid = false;");
			out.println("            } else if (password.length < 6) {");
			out.println("                passwordError.innerHTML = \"Password must be at least 6 characters\";");
			out.println("                isValid = false;");
			out.println("            }");
			out.println("");
			out.println("            return isValid;");
			out.println("        }");
			out.println("");
			out.println("        function isValidEmail(email) {");
			out.println("            var emailRegex = /^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$/;");
			out.println("            return emailRegex.test(email);");
			out.println("        }");
			out.println("    </script>");
	
			out.println("\t\t</main>");
			out.println();
			out.println("\t</section>");
			out.println();
			out.println("	<script src='OICscript.js'></script>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
		
		}
		else
		{
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
