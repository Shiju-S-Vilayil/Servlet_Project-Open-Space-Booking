package com.bos.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bos.db.DBConnection;

/**
 * Servlet implementation class OICrejected
 */
@WebServlet("/OICrejected")
public class OICrejected extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OICrejected() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		    out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>");

			out.println("\t<meta charset='UTF-8'>");
			out.println("\t<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
			out.println("\t<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>");
			out.println("\t<link rel='stylesheet' href='OICstyle.css'>");
			out.println("\t<title>AdminHub</title>");
			out.println("</head>");
			out.println("<body>");
			out.println();
			out.println("\t<section id='sidebar'>");
			out.println("\t\t<a href='OICdashboard' class='brand'>");
			out.println("\t\t\t<i class='bx bxs-smile'></i>");
			out.println("\t\t\t<span class='text'>OIC</span>");
			out.println("\t\t</a>");
			out.println("\t\t<ul class='side-menu top'>");
			out.println("\t\t\t<li class='active'>");
			out.println("\t\t\t\t<a href='OICdashboard'>");
			out.println("\t\t\t\t\t<i class='bx bxs-dashboard' ></i>");
			out.println("\t\t\t\t\t<span class='text'>Dashboard</span>");
			out.println("\t\t\t\t</a>");
			out.println("\t\t\t</li>");
			out.println("\t\t\t<li>");
			out.println("\t\t\t\t<a href='OICpending'>");
			out.println("\t\t\t\t\t<i class='bx bxs-shopping-bag-alt' ></i>");
			out.println("\t\t\t\t\t<span class='text'>Pending Booking Requests</span>");
			out.println("\t\t\t\t</a>");
			out.println("\t\t\t</li>");
			out.println("\t\t\t<li>");
			out.println("\t\t\t\t<a href='OICapproved'>");
			out.println("\t\t\t\t\t<i class='bx bxs-doughnut-chart' ></i>");
			out.println("\t\t\t\t\t<span class='text'>Approved Booking Requests</span>");
			out.println("\t\t\t\t</a>");
			out.println("\t\t\t</li>");
			out.println("\t\t\t<li>");
			out.println("\t\t\t\t<a href='OICrejected'>");
			out.println("\t\t\t\t\t<i class='bx bxs-message-dots' ></i>");
			out.println("\t\t\t\t\t<span class='text'>Rejected Booking Requests</span>");
			out.println("\t\t\t\t</a>");
			out.println("\t\t\t</li>");
			out.println();
			out.println("\t\t</ul>");
			out.println("\t\t<ul class='side-menu'>");
			out.println("\t\t\t<li>");
			out.println("\t\t\t</li>");
			out.println("\t\t\t<li>");
			out.println("\t\t\t\t<a href='#' class='logout'>");
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
			out.println("<h5 style='color:#008080'>Rejected Request</h5>");
			out.println("\t\t</main>");
			out.println();
			 out.println("    <div class='container'>");

		        out.println("        <div class='table-responsive'>");

		            out.println("<table class='table table-success table-striped'>");
		          
		            out.println("<thead class='thead-dark'>");
		            out.println("    <tr>");
			          
	               
	                out.println("   <th scope='col'>Slot Date</th>");
	             
	                out.println("       <th scope='col'>Charges</th>");
	         
	                out.println("            <th scope='col'>Day of Week</th>");
	    
	                out.println("                <th scope='col'>Booking status</th>");

	                out.println("       </tr>");
	       
	              out.println("    </thead>");
	        
	            out.println("      <tbody>");
		            try 
		            {
		            	String sql="select * from seatreserved where booking_status=2";
		            	con=DBConnection.con;
		            	Statement st=con.createStatement();
		            	ResultSet rs=st.executeQuery(sql);
		            	while(rs.next())
		            	{
		            		 out.println("       <tr>");
		      		      
				            
				                out.println("   <td>"+rs.getString("date")+"</td>");
				             
				                out.println("    <td>"+rs.getInt("charges")+"</td>");
				            
				                out.println(" <td>"+rs.getString("dow")+"</td>");
				               
				                if(rs.getInt("booking_status")==2)
				                {
				                	out.println(" <td><span style='color:red'>Rejected</span></td>");
				                }
				                
				               
				                out.println("   </tr>");
		            	}
						
					} catch (Exception e) 
		            {
						System.out.println(e);
					}
		            
		              
		      
		               
		           
		              out.println("  </tbody>");
		              
		          
		            out.println(" </table>");
		         
		          out.println(" </div>");
		       
		        out.println(" </div>");
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
