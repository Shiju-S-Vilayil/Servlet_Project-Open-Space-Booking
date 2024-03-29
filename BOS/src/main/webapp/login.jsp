<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Open Space Booking</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'>
    <link rel="stylesheet" href="./signinCSS.css">
</head>
<body>
<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form action="GetCustomer" method="post" id="signup-form">
            <h1>Create Account</h1>
            <span>or use your email for registration</span>
            <input type="text" placeholder="Name" name="customerName" id="customerName" required />
            <input type="email" placeholder="Email" name="customerEmail" id="customerEmail" required />
            <input type="password" placeholder="Password" name="customerPassword" id="customerPassword" required />
            <button type="submit" id="signup-button">Sign Up</button>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form action="LoginServlet" method="post" id="signin-form">
            <h1>Sign in</h1>
            <span>or use your account</span>
            <input type="email" placeholder="Email" name="email" id="signin-email" required />
            <input type="password" placeholder="Password" name="password" id="signin-password" required />
            <a href="#">Forgot your password?</a>
            <button type="submit" id="signin-button">Sign In</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Welcome Back!</h1>
                <p>To keep connected with us please login with your personal info</p>
                <button class="ghost" id="signIn">Sign In</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Hello, Friend!</h1>
                <p>Enter your personal details and start journey with us</p>
                <button class="ghost" id="signUp">Sign Up</button>
            </div>
        </div>
    </div>
</div>
<script  src="./signinJS.js"></script>
    <% if ("invalid".equals(request.getParameter("error"))) { %>
    <script>
        alert("Invalid email or password. Please try again.");
    </script>
<% } %>
</body>
</html>
