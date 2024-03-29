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
 * Servlet implementation class CustAvailableSlots
 */
@WebServlet("/CustAvailableSlots")
public class CustAvailableSlots extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String seatCout = "";
	private Connection con;
	public static int noofvisit = 0;
	public static String currentmonth = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustAvailableSlots() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int rate=0;
		PrintWriter out = response.getWriter();
		int customerId = 0;
		HttpSession session = request.getSession(false);
		String roleid = (String) session.getAttribute("roleid1");
		if(roleid!=null)
		{
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			String customername = "";
			try {
				con = DBConnection.connection();
				Statement st = con.createStatement();
				ResultSet set = st.executeQuery("select * from customerinfo where customerid='" + roleid + "'");
				while (set.next()) {
					customername = set.getString("customername");
					customerId = set.getInt("customerid");
				}

			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			out.println("<!DOCTYPE html>");
			out.println("<html lang='en'>");

			out.println("	<meta charset='UTF-8'>");

			out.println("	<meta name='viewport' content='width=device-width, initial-scale=1.0'>");

			out.println("	<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>");
			out.println(
					"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>");

			out.println("	<link rel='stylesheet' href='custstyle.css'>");

			out.println("	<title>Customer Profile</title>");

			out.println("</head>");

			out.println("<body>");

			out.println("	<section id='sidebar'>");

			out.println("		<a href='#' class='brand'>");

			out.println("			<i class='bx bxs-smile'></i>");

			out.println("			<span class='text'>" + customername.toUpperCase() + "</span>");

			out.println("		</a>");

			out.println("		<ul class='side-menu top'>");

			out.println("<li>");

			out.println("				<a href='Customerdashboard'>");

			out.println("					<i class='bx bxs-dashboard' ></i>");

			out.println("					<span class='text'>Seat Booking</span>");

			out.println("				</a>");

			out.println("			</li>");

			out.println("			<li>");
			out.println("			<li class='active'>");

			out.println("				<a href='CustAvailableSlots'>");

			out.println("					<i class='bx bxs-doughnut-chart' ></i>");

			out.println("					<span class='text'>Available Slots</span>");
			out.println("				</a>");
			out.println("			</li>");

			out.println("</ul>");

			out.println("<ul class='side-menu'>");

			out.println("<li>");

			out.println("<a href='CustProfile'>");

			out.println("<i class='bx bxs-cog' ></i>");

			out.println("	<span class='text'>Profile</span>");

			out.println("	</a>");

			out.println("</li>");

			out.println("<li>");

			out.println("	<a href='Logout' class='logout'>");

			out.println("	<i class='bx bxs-log-out-circle' ></i>");

			out.println("	<span class='text'>Logout</span>");

			out.println("	</a>");

			out.println("	</li>");

			out.println("	</ul>");

			out.println("</section>");

			out.println("	<section id='content'>");

			out.println("	<nav>");

			out.println("<i class='bx bx-menu' ></i>");

			out.println("<input type='checkbox' id='switch-mode' hidden>");

			out.println("	</nav>");

			out.println("<main>");

			out.println("	<div class='head-title'>");

			out.println("<div class='left'>");

			out.println("	<h1>Booking Details</h1>");

			out.println("<ul class='breadcrumb'>");

			out.println("		</ul>");

			out.println("	</div>");

			out.println("</div>");
			out.println("<section class='home'>");

			out.println("  <div class='text'> ");
			try 
			{
				String sql="select sum(charges) as rate from seatreserved where customerid='"+customerId+"'";
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(sql);
				while(rs.next())
				{
					rate=rs.getInt("rate");
				}
				
			} catch (Exception e)
			{
				System.out.println(e);
			}

			/// TableContent Here

							out.println("<h5 style='color:red'>Total Cost of Booking : $"+rate+"</h5>");

			out.println("    <div class='container'>");

			out.println("        <div class='table-responsive'>");

			out.println("<table class='table table-dark table-hover mt-3'>");

			out.println("<thead class='thead-dark'>");

			out.println("    <tr>");

			out.println(" <th scope='col'>Booking date</th>");

			out.println("   <th scope='col'>Slot Date</th>");

			out.println("       <th scope='col'>Charges</th>");

			out.println("            <th scope='col'>Day of Week</th>");

			out.println("                <th scope='col'>Booking status</th>");

			out.println("       </tr>");

			out.println("    </thead>");

			out.println("      <tbody>");
			
			try 
			{
				String sql="select * from seatreserved where customerid='"+customerId+"'";
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next())
				{
					out.println("       <tr>");

					out.println("    <td>"+rs.getString("verbaldate")+"</td>");

					out.println("   <td>"+rs.getString("date")+"</td>");

					out.println("    <td>$"+rs.getInt("charges")+"</td>");

					out.println(" <td>"+rs.getString("dow")+"</td>");
					if(rs.getInt("booking_status")==0)
					{
						out.println(" <td><span style='color:green'>In Progress</span></td>");
					}
					else if(rs.getInt("booking_status")==1)
					{
						out.println(" <td><span style='color:red'>Approved</span></td>");
					}
					else
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

			out.println("	</section>");

			out.println("<script src='custscript.js'></script>");

			out.println("</body>");

			out.println("</html>");
		}
		else
		{
			response.sendRedirect("login.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
