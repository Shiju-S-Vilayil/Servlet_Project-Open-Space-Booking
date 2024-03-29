package com.bos.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.bos.controller.Customerdashboard;
import com.bos.db.DBConnection;

public class SeatBookingService {
	int maxseatcount = 0;
	int weekday = 0;
	int weekend = 0;

	private Connection con = DBConnection.connection();

	public int addReservedSeats(List seats, int id, int noofvisit) {
		List<String> FormattedDays = new ArrayList<String>();
		List<String> dow = new ArrayList<String>();
		List<Integer> DayofWeek1 = new ArrayList<Integer>();

		try {
			Statement st = con.createStatement();
			String sql = "select premium.premium_seatcount as maxseatcount ,premium.weekday as weekday, premium.weekend as weekend from premium inner join premium_users on premium.id=premium_users.premium_id where premium_users.premium_customer_id="
					+ id + "";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				maxseatcount = rs.getInt("maxseatcount");
				weekday = rs.getInt("weekday");
				weekend = rs.getInt("weekend");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		List<String> listSeat = seats;

		// Filtering Selected seats from dashboard calender
		for (String value : listSeat) {
			int day = Integer.parseInt(value.substring(0, 2));
			int month = Integer.parseInt(value.substring(2, 4));
			int year = Integer.parseInt(value.substring(4));
			LocalDate date = LocalDate.of(year, month, day);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedDate = date.format(formatter);
			DayOfWeek dayOfWeek = date.getDayOfWeek();
			String dayType = (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) ? "Weekend" : "Weekday";
			if (dayType.equals("Weekend")) {

				DayofWeek1.add(weekend);
				FormattedDays.add(formattedDate);
				dow.add(dayType);

			} else {
				DayofWeek1.add(weekday);
				FormattedDays.add(formattedDate);
				dow.add(dayType);
			}
		}

		boolean flag = false;
		if (noofvisit == 0) {
			flag = true;
		} else {
			flag = false;
		}

		String sql = "";
		try {
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH);
			String verbalDate = currentDate.format(formatter);
			noofvisit = noofvisit + 1;
			int status = 0;
			
				for (int i = 0; i < seats.size(); i++) {

					sql = "INSERT into seatreserved (seatcodecol,customerid,status,date,charges,booking_status,dow,verbaldate) values('"
							+ seats.get(i) + "'," + id + ",'" + noofvisit + "','" + FormattedDays.get(i) + "',"
							+ DayofWeek1.get(i) + ",0,'" + dow.get(i) + "','" + verbalDate + "');";
					Statement stmt = con.createStatement();
					status = stmt.executeUpdate(sql);

				}
				String seatcode = seats.get(0).toString();
				String monthid = seatcode.substring(2, 4);

				if (flag) {
					sql = "INSERT into reservedlog (seatcodecol,customerid,status,monthid) values('" + seats.get(0) + "',"
							+ id + ",'" + noofvisit + "','" + monthid + "');";
				} else {
					sql = "UPDATE reservedlog SET status=" + noofvisit + " where monthid='" + monthid + "'";
				}

				Statement stmt = con.createStatement();
				stmt.executeUpdate(sql);
			
			

			return 1;

		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;

	}

}
