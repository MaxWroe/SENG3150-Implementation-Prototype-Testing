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
<body>
<!-- session handler -->
<jsp:include page="sessionHandlerGuest.jsp"/>
<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <h1>Flight Booking Details</h1>
    <div class="flight-booking">
        <div id="side-bar">
            <h4>Departure Flight</h4>
            <p>Price</p>
            <h4>$${departureFlight.cost}</h4>
            <p>Date/Time</p>
            <h4>${departureFlight.date} ${departureFlight.time}</h4>
            <p>Seat Class</p>
            <h4>${departureFlight.classCode}</h4>
            <p>Seats Remaining</p>
            <h4>${departureFlight.seats}</h4>
            <c:if test = "${param.pageDirect eq 'return'}">
            <h4>Return Flight</h4>
            <p>Price</p>
            <h4>$${returnFlight.cost}</h4>
            <p>Date/Time</p>
            <h4>${returnFlight.date} ${returnFlight.time}</h4>
            <p>Seat Class</p>
            <h4>${returnFlight.classCode}</h4>
            <p>Seats Remaining</p>
            <h4>${returnFlight.seats}</h4>
            </c:if>
        </div>

        <!-- Check if user is logged in -->
        <%
        String userID = (String)session.getAttribute("userId");
        if(userID != null){%>
            <p>You're still logged in.</p>

            <div class="booking-details">
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
        <div class="booking-details">
            <p>You're not logged in!</p>
            <form id="loginForm" method="post" action="">
                <!-- email address -->
                <label for="email">Email address</label>
                <input id="email" type="email" name ="email" required/> <br>
                <!-- password -->
                <label for="password">Password</label>
                <input type ="password" id="password" name ="password" required/> <br>
                <input type="submit" value="Login"/>
            </form>
        </div>
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
