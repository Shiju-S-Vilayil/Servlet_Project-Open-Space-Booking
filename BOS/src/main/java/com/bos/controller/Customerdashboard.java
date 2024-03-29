package com.bos.controller;

import java.io.BufferedReader;
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
import com.bos.service.SeatBookingService;
import com.mysql.cj.xdevapi.JsonArray;

/**
 * Servlet implementation class Customerdashboard
 */
@WebServlet("/Customerdashboard")
public class Customerdashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String seatCout = "";
	private Connection con;
	public static int noofvisit = 0;
	public static String currentmonth = "";

	public Customerdashboard() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String roleid = (String) session.getAttribute("roleid1");
		if(roleid!=null)
		{
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			PrintWriter out = response.getWriter();
			String selectedMonth = request.getParameter("selectedMonth");

		   
		    if (selectedMonth != null) {
		        currentmonth = selectedMonth;
		    }
			noofvisit=0;
			String monthid=null;
		
			int customerId = 0;
		

			String query1 = "select status from reservedlog where seatcodecol LIKE '"+currentmonth+"' and customerid='" + roleid + "' ";

			try {
				con = DBConnection.connection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query1);
				while (rs.next()) {
					
					
						noofvisit = rs.getInt("status");
				}

				System.out.println("The status value from db-----------"+noofvisit);
		
			} catch (Exception e) {
				System.out.println(e);
			}
			

	         

			
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

	   out.print("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>");

	    out.println("	<link rel='stylesheet' href='custstyle.css'>");


	    out.println("	<title>Customer Profile</title>");
	  

	    out.println("</head>");

	out.println("<body>");
				String g=request.getParameter("arg1");
				if(g!=null)
				{
				//	System.out.println("Visit form arg1----------------------------------"+g);
					int visitstatus = Integer.parseInt(g);
					if(visitstatus==100)
					{
						
						out.print("<script>alert('cannot book more than two  times in a month');</script>");
					}
					else if(visitstatus==200)
					{
						
						out.print("<script>alert('Slots booking limited');</script>");
					}
					else if(visitstatus==202)
					{
						out.print("<script>alert('Seats Added Succesfully');</script>");
					}
					else if(visitstatus==300)
					{
						out.print("<script>alert(' Login Successfully');</script>");
					}
					else if(visitstatus==600)
					{
						out.print("<script>alert(' Please select atleast 2 slots');</script>");
					}
					else if(visitstatus==700)
					{
						out.print("<script>alert(' back to back slot booking not allowed');</script>");
					}
				}
	    String jsonData = "[";
		out.println("<script>");
//		  out.println("");
			try {
		

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

	            out.println("			<span class='text'>"+customername.toUpperCase()+"</span>");

	            out.println("		</a>");

	        out.println("		<ul class='side-menu top'>");

	            out.println("			<li class='active'>");

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
//			
//	            out.println("<li>");
//				
//	                out.println("<a href='CustProfile'>");
//					
//	                    out.println("<i class='bx bxs-cog' ></i>");
//						
//	                    out.println("	<span class='text'>Profile</span>");
//					
//	                    out.println("	</a>");
//				
//	                out.println("</li>");
//				
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

//	            out.println("<i class='bx bx-menu' ></i>");
			
	            try {

	                con = DBConnection.connection();
	                Statement st = con.createStatement();
	            
	                ResultSet set = st.executeQuery("select count(customerid) as count  from seatreserved where customerid='"
	                        + roleid + "' and seatcodecol LIKE '" + currentmonth + "'");
	                while (set.next()) {
//	                    System.out.println(set.getString("count"));
	                    seatCout = set.getString("count");
	                }

	            } catch (Exception e) {
	                System.out.println(e);
	            }



	     
	        out.println("	</nav>");
			
	            out.println("<main>");
	           
			
	                out.println("	<div class='head-title'>");
//	             System.out.println("value befor esending Getdata servlet**********-"+noofvisit);
	                    out.println("<div class='left'>");
	                    out.println("   <form action=\"GetData\" method=\"post\">\r\n"
	                    		+ "            <input type=\"hidden\" name=\"selectedSeats\" id=\"selectedSeats\" >\r\n"
	                    		+ "            <input type=\"hidden\" name=\"noofvisit\"  value=\""+noofvisit+"\">\r\n"
	                    		+ "            <input type=\"hidden\" name=\"customerId\" id=\"customerId\"  value=\""+customerId+"\">\r\n"
	                    		+ "            <button type=\"submit\" onclick=\"data123()\" id=\"book-button\" class=\"btn btn-success\" >Book</button>\r\n"
	                    		+ "        </form>");
//	                    out.println("	<h1 id='errormsg' style='color:red'>Booking Not allowed bcoz triple booking not allowed</h1>");
	                 
	                  
						
	                        out.println("<ul class='breadcrumb'>");
	                      
				
	                    out.println("		</ul>");
	             
	                out.println("	</div>");
	                out.println("<h1 id='errormsg' style='color:red; font-size:19px;'></h1>");

//	                System.out.println("noofvisit-----------------------" + noofvisit);
	                int a = Integer.parseInt(seatCout);
	                if (noofvisit == 2) {
//	                    System.out.println("1");
	                    out.print("<script> document.addEventListener('DOMContentLoaded', function() { document.getElementById('book-button').disabled=true; document.getElementById('errormsg').innerHTML = 'You can only book up to twice a month ...! please try another month for booking'; });</script>");
//	                    System.out.println("Value of noofvisit--------------" + noofvisit);                    
	                }
	                else if (a > 21) {
//	                    System.out.println("2");
	                    out.print("<script> document.getElementById('book-button').disabled=true;</script>");
	                }
	                else if (noofvisit < 3) {
//	                    System.out.println("3");
	                    out.print("<script> document.getElementById('book-button').disabled=false;</script>");
	                    // out.println("<script> document.getElementById('errormsg').innerHTML = '';</script>");
	                }
	                else {
//	                    System.out.println("4");
	                    out.print("<script> document.getElementById('book-button').disabled=false;</script>");
	                }
       
				
	            out.println("</div>");
	            out.println("<section class='home'>");

	out.println("  <div class='text'> ");


	out.println("   <div class='calendar-container'>");

	out.println(" <div class='month-selector'>");
	 out.println("<h5 style='color:#008080'>Please select Month and book minimum 2 Slots and Maximum 7  Slots </h5>");

	out.println("<label for='month'>Select Month:</label>");

	out.println("   <select id='month'>");

//			                out.println(" <option value='1'>January</option>");
//			                
//			                out.println("<option value='2'>February</option>");
	out.println("<option value='0'>------</option>");

	out.println("<option value='3'>March</option>");

	out.println("   <option value='4'>April</option>");

	out.println("<option value='5'>May</option>");

	out.println("<option value='6'>June</option>");

	out.println("  <option value='7'>July</option>");
	out.println("  <option value='8'>Aug</option>");
	out.println("  <option value='9'>Sep</option>");
	out.println("  <option value='10'>Oct</option>");
	out.println("  <option value='11'>Nov</option>");
	out.println("  <option value='12'>December</option>");
	out.println("  </select>");
//	            ajax*************************************************


	//out.println("<script>");
	//out.println("document.getElementById('month').addEventListener('change', function() {");
	//out.println("    var selectedMonth = this.value;");
	//out.println("    // Reload the page with the selected month as a parameter");
	//out.println("    window.location.href = 'Customerdashboard?selectedMonth=' + encodeURIComponent(selectedMonth);");
	//out.println("});");
	//out.println("</script>");








	out.println("   </div>");

	out.println("   <div id='calendar' class='calendar'>");

	out.println("      </div>");

	out.println("      <div id='booking-details' class='booking-details'>");

	out.println("      </div>");



	out.println("      <script src='s.js'> </script>");
	out.println("<script>");
	out.println("document.getElementById('month').addEventListener('change', function() {");

	out.println("    var selectedMonth = this.value;");
	out.println("    var xhr = new XMLHttpRequest();");
	out.println("    xhr.open('POST', 'Customerdashboard', true);");
	out.println("    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');");
	out.println("    xhr.onreadystatechange = function() {");
	out.println("        if (xhr.readyState == 4 && xhr.status == 200) {");
	out.println("            // Handle response if needed");
	out.println("        }");
	out.println("    };");
	out.println("    xhr.send('selectedMonth=' + encodeURIComponent(selectedMonth));");
	out.println("});");
	if(selectedMonth!=null)
	{
		doGet(request, response);
	}
	out.println("</script>");
	//out.println("<script>");
	//out.println("document.getElementById('month').addEventListener('change', function() {");
	//out.println("    var selectedMonth = this.value;");
	//out.println("    var url = window.location.href.split('?')[0];");
	//out.println("    url += '?selectedMonth=' + encodeURIComponent(selectedMonth);");
	//out.println("    window.location.href = url;");
	//out.println("generateCalendar(selectedMonth)");
	//out.println("});");
	out.println(" </div>");

	out.println("</section>");
	        out.println("	</main>");

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

		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		currentmonth = sb.toString();

//		System.out.println("Received string data: " + currentmonth);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("String data received successfully!");
String selectedMonth = request.getParameter("selectedMonth");

        // Perform further processing with the selected month value
//        System.out.println("Selected month: " + selectedMonth);
		doGet(request, response);

	}

}
