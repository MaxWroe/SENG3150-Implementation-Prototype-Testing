<%--
  Created by IntelliJ IDEA.
  User: jfpr2
  Date: 22/05/2020
  Time: 4:43 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Flight Booking Page</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <script src="/js/dynamicLink.js"></script>
</head>


<!-- session handler -->
<%
   String userID = (String)session.getAttribute("userId");

    //checks if user is logged in
   if(userID == null) {
        %><body><%
   }else{
        %><body onload=userPage('/logout','Logout');> <%
   }%>


<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <h1>Flight Booking Details</h1>
    <div class="flight-booking">
        <div id="side-bar">
            <p>Price</p>
            <h4>$000</h4>
            <p>Date/Time</p>
            <h4>00/00/00 00:00AM</h4>
            <p>Seat Class</p>
            <h4>Economy</h4>
            <p>Seats Remaining</p>
            <h4>0</h4>
        </div>

        <!-- Check if user is logged in -->
        <% if(userID != null){%>
            <p>You're still logged in.</p>

            <div id="payment-details">
                <p>Payment Details</p>
                <form method="post">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name">
                    <br>
                    <label for="cardno">Card Number:</label>
                    <input type="number" id="cardno" name="cardno">
                    <br>
                    <label for="cardexpiry">Expiry Date:</label>
                    <input type="month" id="cardexpiry" name="cardexpiry">
                    <br>
                    <label for="cardcvc">CVC:</label>
                    <input type="number" id="cardcvc" name="cardcvc">
                </form>
            </div>


        <!-- If user is not logged in display log in form -->
        <%}else{%>
            <p>You're not logged in!</p>
        <%}%>


        <!-- extra details
        <p>Baggage Details</p>
        <p>Duration</p>

        <p>Cancellation Policy</p>
        <p>Travel Insurance</p>
        <p>Disability Support</p>
        <p>Amenities</p>
        <p>Food Services</p>
        -->
    </div>
</main>
</body>
</html>
