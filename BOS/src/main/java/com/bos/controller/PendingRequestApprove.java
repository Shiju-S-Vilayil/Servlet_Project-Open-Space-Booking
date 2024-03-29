package com.bos.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bos.db.DBConnection;

/**
 * Servlet implementation class PendingRequestApprove
 */
@WebServlet("/PendingRequestApprove")
public class PendingRequestApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingRequestApprove() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seatcodecol=request.getParameter("col");
		HttpSession session = request.getSession(false);
		String roleid = (String) session.getAttribute("roleid1");
		try 
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
			   LocalDateTime now = LocalDateTime.now();  
		   
			String sql="update  seatreserved set booking_status=1,oic='"+roleid+"',oic_date='"+now+"' where seatcodecol='"+seatcodecol+"'";
			con=DBConnection.con;
			Statement st=con.createStatement();
			int i=st.executeUpdate(sql);
			if(i==1)
			{
				response.sendRedirect("OICpending?status=800");
			}
			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
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
