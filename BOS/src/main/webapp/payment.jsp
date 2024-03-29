<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="paymentstyle.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
</head>
<body>

  <main class="main flow">
    <h1 class="main__heading">Open Spacing Booking Premium Membership</h1>
    <div class="main__cards cards">
      <div class="cards__inner">
          <div class="cards__card card">
            <h2 class="card__heading">Basic - Level 2</h2>
            <p class="card__price">Free</p>
            <ul role="list" class="card__bullets flow">
    <% String receivedData = (String)request.getParameter("data"); %>
              <li>10 Slots Bookings</li>
              <li>$40 Weekday </li>
              <li>$60 Weekend </li>
            </ul>
            <a href="Paymentfree?data=<%= receivedData %>" class="card__cta cta">Get Started</a>
          </div>     
  
          <div class="cards__card card">
            <h2 class="card__heading">Premium - Level 1</h2>
            <p class="card__price">$50.49</p>
            <ul role="list" class="card__bullets flow">
              <li>20 Slots Bookings</li>
              <li>$40 Weekday </li>
              <li>$60 Weekend </li>
            </ul>
           <a href="Payment?data=<%= receivedData %>" class="card__cta cta">Upgrade to Pro</a>

          </div>
        
      </div>
  
      <div class="overlay cards__inner"></div>
    </div>
  </main>
  <script src="paymentscript.js"></script>
</body>
</html>