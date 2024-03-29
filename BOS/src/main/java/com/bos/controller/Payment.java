package com.bos.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bos.db.DBConnection;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public Connection con=DBConnection.con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Get Payment Details");
		String roleid=request.getParameter("data");
//		System.out.println(roleid);
		try 
		{
			Statement stmt=con.createStatement();
			String sql="insert into premium_users (premium_customer_id,premium_id) values ('"+roleid+"',1)";
			int f=stmt.executeUpdate(sql);
			if(f==1)
			{
				HttpSession ses=request.getSession();
				ses.setAttribute("roleid1", roleid);
				response.sendRedirect("Customerdashboard");
			}
		} catch (Exception e) 
		{
		
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
