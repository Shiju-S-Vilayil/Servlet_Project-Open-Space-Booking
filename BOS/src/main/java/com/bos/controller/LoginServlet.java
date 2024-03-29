package com.bos.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bos.service.CustomerService;
import com.bos.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService ser=new LoginService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		System.out.println(email);
		System.out.println(password);
		String role;
		String roleid;
		List<String> list=ser.login(email,password);
		 if (list == null || list.isEmpty()) {
	            response.sendRedirect("login.jsp?error=invalid");
	            return;
	        }
		 else
		 {
			 role=list.get(0);
				roleid=list.get(1);
				System.out.println(role);
				System.out.println(roleid);
				HttpSession session=request.getSession();
				  
				switch(role)
				{
				case "ROLE_CUSTOMER":
			
					session.setAttribute("roleid1", roleid);
					 String value1="300";
					 String redirectUrl = "Customerdashboard?arg1=" + value1;
					    response.sendRedirect(redirectUrl); 
					    list.clear();
					break;
				case "ROLE_OIC":
					
					session.setAttribute("roleid1", roleid);
					 String value2="300";
					 String redirectUrl1 = "OICdashboard?arg1=" + value2;
					    response.sendRedirect(redirectUrl1); 
					    list.clear();
					break;
					
				case "ROLE_ADMIN":
					session.setAttribute("roleid1", roleid);
					 String value3="300";
					 String redirectUrl2 = "Admin?arg1=" + value3;
					    response.sendRedirect(redirectUrl2); 
					    list.clear();

					break;
				}
				
			 
		 }
		
	}

}
