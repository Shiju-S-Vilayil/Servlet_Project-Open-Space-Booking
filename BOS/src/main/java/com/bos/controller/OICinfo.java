package com.bos.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bos.db.DBConnection;

/**
 * Servlet implementation class OICinfo
 */
@WebServlet("/OICinfo")
public class OICinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = DBConnection.connection();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OICinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("Hi");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String role="ROLE_OIC";
	        String password = request.getParameter("password");
	        int roleid=0;
	        try {

			
				Statement stmt = con.createStatement();

				int result = stmt.executeUpdate("insert into oicinfo(name,email,password,role) values('"+name+"','"+email+"','"+password+"','"+role+"')");
				if(result==1)
				{
					if(result==1)
					{
						try 
						{
							Statement stmt1 = con.createStatement();
							ResultSet set=stmt1.executeQuery("select max(id) as id  from oicinfo");
							while(set.next())
							{
								roleid=set.getInt("id");
							}
							Statement stmt2 = con.createStatement();
					String sqlLog="insert into log(email,password,role,roleid) values('"+email+"','"+password+"','"+role+"','"+roleid+"')";
					int result1 = stmt.executeUpdate(sqlLog);
					if(result1==1)
					{
						response.sendRedirect("Admin");
					}
						}
						catch (Exception e) {
							System.out.println(e);
						}
				
				}
				}
	        }
	        catch (Exception e) {
	        	System.out.println(e);
			System.out.println(e);
			}
	}

}
