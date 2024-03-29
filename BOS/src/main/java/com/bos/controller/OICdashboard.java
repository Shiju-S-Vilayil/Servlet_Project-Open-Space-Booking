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
import com.mysql.cj.protocol.Resultset;

/**
 * Servlet implementation class OICdashboard
 */
@WebServlet("/OICdashboard")
public class OICdashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Connection con;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OICdashboard() {
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
			out.println("</head>");
			out.println("<body>");
			out.println();
			out.println("\t<section id='sidebar'>");
			out.println("\t\t<a href='#' class='brand'>");
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
			int count1=0;
			int amount1=0;
			out.println("\t\t</nav>");
			out.println("\t\t<main>");
			out.println("\t\t\t<div class='head-title'>");
			out.println("\t\t\t\t<div class='left'>");
			out.println("\t\t\t\t\t<ul class='breadcrumb'>");
			out.println("\t\t\t\t\t\t<li>");
			out.println("\t\t\t\t\t\t\t<a href='OICdahboard'>Dashboard</a>");
			out.println("\t\t\t\t\t\t</li>");
			out.println("\t\t\t\t\t\t<li><i class='bx bx-chevron-right' ></i></li>");
			out.println("\t\t\t\t\t\t<li>");
			out.println("\t\t\t\t\t\t\t<a class='active' href='#'>Home</a>");
			out.println("\t\t\t\t\t\t</li>");
			out.println("\t\t\t\t\t</ul>");
			out.println("\t\t\t\t</div>");
			out.println("\t\t\t</div>");
			out.println();
			try {
				con = DBConnection.connection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select count(seatcodecol) as count ,sum(charges) as amount  from seatreserved where booking_status=1;");
				while (rs.next()) {
					 count1=rs.getInt("count");
					 amount1=rs.getInt("amount");
					
				}


		
			} catch (Exception e) {
				System.out.println(e);
			}
			
			out.println("\t\t\t<ul class='box-info'>");
			out.println("\t\t\t\t<li>");
			out.println("\t\t\t\t\t<i class='bx bxs-calendar-check' ></i>");
			out.println("\t\t\t\t\t<span class='text'>");
			out.println("\t\t\t\t\t\t<h3>"+count1+"</h3>");
			out.println("\t\t\t\t\t\t<p>Approved Requests</p>");
			out.println("\t\t\t\t\t</span>");
			out.println("\t\t\t\t</li>");
			int count=0;
			int amount=0;
			out.println("\t\t\t\t<li>");
//			select seatreserved.count(seatcodecol) as count ,sum(charges) as amount  from seatreserved where booking_status=1;
			try {
				con = DBConnection.connection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select count(seatcodecol) as count ,sum(charges) as amount  from seatreserved;");
				while (rs.next()) {
					
					count=rs.getInt("count");
					amount=rs.getInt("amount");
					
				}


		
			} catch (Exception e) {
				System.out.println(e);
			}
			
			
			out.println("\t\t\t\t\t<i class='bx bxs-group' ></i>");
			out.println("\t\t\t\t\t<span class='text'>");
			out.println("\t\t\t\t\t\t<h3>"+count+"</h3>");
			out.println("\t\t\t\t\t\t<p>Visitors</p>");
			out.println("\t\t\t\t\t</span>");
			out.println("\t\t\t\t</li>");
			out.println("\t\t\t\t<li>");
			out.println("\t\t\t\t\t<i class='bx bxs-dollar-circle' ></i>");
			out.println("\t\t\t\t\t<span class='text'>");
			out.println("\t\t\t\t\t\t<h3>$"+amount+"</h3>");
			out.println("\t\t\t\t\t\t<p>Total Sales</p>");
			out.println("\t\t\t\t\t</span>");
			out.println("\t\t\t\t</li>");
			out.println("\t\t\t</ul>");
			out.println();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
