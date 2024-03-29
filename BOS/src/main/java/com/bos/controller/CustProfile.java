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
 * Servlet implementation class CustProfile
 */
@WebServlet("/CustProfile")
public class CustProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String seatCout = "";
	private Connection con;
	public static int noofvisit = 0;
	public static String currentmonth = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int customerId = 0;
		HttpSession session = request.getSession(false);
		String roleid = (String) session.getAttribute("roleid1");
		if(roleid!=null)
		{
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

			String customername = "";
			response.setContentType("text/html");

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

			out.println("	<link rel='stylesheet' href='custstyle.css'>");

			out.println("	<title>Customer Profile</title>");

			out.println("</head>");

			out.println("<body>");
			String jsonData = "[";

			try {
				out.println("<script>");

				con = DBConnection.connection();
				Statement st = con.createStatement();
				ResultSet set = st.executeQuery("select * from seatreserved");

				while (set.next()) {

					jsonData += "\"" + set.getString("seatcodecol") + "\"";
					jsonData += ",";
				}
				jsonData += "]";
				out.println("const seatReservedData = " + jsonData + ";");
				out.println("</script>");

			} catch (Exception e) {
				System.out.println(e);
			}

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


			out.println("				<a href='CustAvailableSlots'>");

			out.println("					<i class='bx bxs-doughnut-chart' ></i>");

			out.println("					<span class='text'>Available Slots</span>");
			out.println("				</a>");
			out.println("			</li>");

			out.println("</ul>");

			out.println("<ul class='side-menu'>");

			out.println("<li>");
			
			out.println("			<li class='active'>");

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

			out.println("			<a href='#' class='notification'>");

			out.println("	<i class='bx bxs-bell' ></i>");

			out.println("	<span class='num'>8</span>");

			out.println("		</a>");

			out.println("	</nav>");

			out.println("<main>");

			out.println("	<div class='head-title'>");

			out.println("<div class='left'>");

			out.println("	<h1>Booking button and other activities here</h1>");

			out.println("<ul class='breadcrumb'>");

			out.println("		</ul>");

			out.println("	</div>");

			out.println("</div>");
			out.println("<section class='home'>");

			out.println("  <div class='text'> ");

			out.println("<h1>Welcome to Profile Slots</h1>");
			
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
