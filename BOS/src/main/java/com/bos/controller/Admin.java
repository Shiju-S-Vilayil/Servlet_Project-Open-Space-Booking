package com.bos.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		String roleid = (String) session.getAttribute("roleid1");
		if (roleid != null) {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

			PrintWriter out = response.getWriter();
			try {
				out.println("<!DOCTYPE html>");
				out.println("<html lang='en'>");
				out.println("<head>");
				out.println("\t<meta charset='UTF-8'>");
				out.println(
						"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>");

				out.println("\t<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
				out.println("\t<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>");
				out.println("\t<link rel='stylesheet' href='OICstyle.css'>");
				out.println("\t<title>AdminHub</title>");
				out.println("</head>");
				out.println("<body>");
				out.println();
				out.println("\t<section id='sidebar'>");
				out.println("\t\t<a href='#' class='brand'>");
				out.println("\t\t\t<i class='bx bxs-smile'></i>");
				out.println("\t\t\t<span class='text'>ADMIN</span>");
				out.println("\t\t</a>");
				out.println("\t\t<ul class='side-menu top'>");
				out.println("\t\t\t<li class='active'>");
				out.println("\t\t\t\t<a href='Admin'>");
				out.println("\t\t\t\t\t<i class='bx bxs-shopping-bag-alt' ></i>");
				out.println("\t\t\t\t\t<span class='text'>Report</span>");
				out.println("\t\t\t\t</a>");
				out.println("\t\t\t</li>");
				out.println("\t\t\t<li >");
				out.println("\t\t\t\t<a href='AdminaddOIC'>");
				out.println("\t\t\t\t\t<i class='bx bxs-shopping-bag-alt' ></i>");
				out.println("\t\t\t\t\t<span class='text'>Add new Staff</span>");
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

				out.println("    <div class='container'>");

				out.println("        <div class='table-responsive'>");

				out.println("<table class='table table-success table-striped'>");

				out.println("<thead class='thead-dark'>");

				out.println("    <tr>");

				out.println(" <th scope='col'>Application ID</th>");

				out.println("   <th scope='col'>Name of Applicant</th>");

				out.println("       <th scope='col'>Trade Description</th>");
				out.println("       <th scope='col'>Trade Description other</th>");

				out.println("            <th scope='col'>Total days Booked</th>");

				out.println("                <th scope='col'>Date of Booking</th>");
				out.println("                <th scope='col'>Status</th>");
				out.println("                <th scope='col'>Date of Case submitted</th>");
				out.println("                <th scope='col'>Last date of follow-up by OIC</th>");
				out.println("       </tr>");

				out.println("    </thead>");

				out.println("      <tbody>");
				
				
				
				try 
				{
				Connection con=DBConnection.con;
				String sql = "SELECT COUNT(seatcodecol) AS seatcount, seatreserved.seatcodecol, seatreserved.date,seatreserved.oic_date, seatreserved.verbaldate,seatreserved.booking_status, seatreserved.charges, customerinfo.customerid, customerinfo.customername, customerpersonal.ut,customerpersonal.ut_other, customerpersonal.tel_type, tel_type " +
			             "FROM seatreserved " +
			             "JOIN customerinfo ON seatreserved.customerid = customerinfo.customerid " +
			             "JOIN customerpersonal ON customerpersonal.customerid = customerinfo.customerid " +
			             "WHERE seatreserved.booking_status = 1 " +
			             "GROUP BY seatreserved.seatcodecol, seatreserved.date, seatreserved.verbaldate, seatreserved.charges,seatreserved.oic_date,customerinfo.customerid, customerinfo.customername, customerpersonal.ut, customerpersonal.tel_type, tel_type,customerpersonal.ut_other";

				con=DBConnection.con;
	            	Statement st=con.createStatement();
	            	ResultSet rs=st.executeQuery(sql);
	            	while(rs.next())
	            	{
	            		
	            		
	            		out.println("       <tr>");

	    				out.println("    <td>"+rs.getInt("customerid")+"</td>");

	    				out.println("   <td>"+rs.getString("customername")+"</td>");

	    				out.println("    <td>"+rs.getString("ut")+"</td>");
	    							if(rs.getString("ut_other")==null)
	    							{
	    								out.println(" <td>NA</td>");
	    							}
	    							else
	    							{
	    								out.println("<td>"+rs.getString("ut_other")+"</td>");
	    							}

	    				out.println(" <td>"+rs.getString("seatcount")+"</td>");
	    				out.println(" <td>"+rs.getString("verbaldate")+"</td>");
	    				out.println(" <td>"+rs.getString("booking_status")+"</td>");
	    				LocalDateTime dateTime = LocalDateTime.parse(rs.getString("oic_date"), DateTimeFormatter.ISO_DATE_TIME);
	    		        
	    		        // Format the LocalDateTime object into a string with the desired format
	    		        String formattedDateString = dateTime.format(DateTimeFormatter.ISO_DATE);
	    				
	    				
	    				out.println(" <td>"+formattedDateString+"</td>");
	    				out.println(" <td>"+formattedDateString+"</td>");
	    				out.println("   </tr>");
	            		
	            		
	            		
	            	}
				} 
				catch (Exception e) 
				{
				System.out.println(e);
				}

				

				out.println("  </tbody>");

				out.println(" </table>");

				out.println(" </div>");

				out.println(" </div>");

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

		} else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
