<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Open Space Booking</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver"  
     url="jdbc:mysql://localhost/bos"  
     user="root"  password="root"/>
<sql:query dataSource="${db}" var="seatReservedData">  
SELECT * from seatreserved;  
</sql:query>
<script>
    var seatReservedData = [];

    <%-- Iterate through each row of the SQL query result and add it to the JavaScript array --%>
    <c:forEach var="row" items="${seatReservedData.rows}">
        var rowData = {};
        <%-- Iterate through each column of the row --%>
        <c:forEach var="column" items="${row}">
            <%-- Add each column key-value pair to the rowData object --%>
            rowData["${column.key}"] = "${column.value}";
        </c:forEach>
        <%-- Add the rowData object to the seatReservedData array --%>
        seatReservedData.push(rowData);
    </c:forEach>
</script>
    <div class="calendar-container">
        <div class="month-selector">
            <label for="month">Select Month:</label>
            <select id="month">
                <option value="1">January</option>
                <option value="2">February</option>
                <option value="3">March</option>
                <!-- Add other months here -->
            </select>
        </div>
        <div id="calendar" class="calendar"></div>
        <div id="booking-details" class="booking-details">
        
        </div>
            <script src="script.js"> </script>
        <form id="booking-form" action="GetData" method="post">
            <input type="hidden" id="selectedSeats" name="selectedSeats" >
            <button type="submit" id="book-button" class="btn btn-success">Book</button>
        </form>
    </div>

</body>
</html>
