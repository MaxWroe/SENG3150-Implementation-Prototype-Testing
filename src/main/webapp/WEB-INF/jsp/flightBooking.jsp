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
        String userID = "work";//(String)session.getAttribute("userId");
        if(userID != null){%>
        <div class="booking-details">
            <form method="post" action="">
                <h4>Passenger Details</h4>
                <br>
                <!-- Autofill first adult from logged in account -->
                <label for="adultName1">Booking Adult Name:</label>
                <input type="text" id="adultName1" name="adultName1" value="${sessionScope.userName}" readonly required>
                <label for="adultAge1">Age:</label>
                <input type="number" id="adultAge1" name="adultAge1" min="18" max="150" value="${sessionScope.userAge}" readonly required>
                <br>
                <!-- Get details of all booking passengers -->
                <c:forEach var = "i" begin = "2" end = "${param.adultsBooked}">
                    <label for="adultName<c:out value = "${i}"/>">Adult <c:out value = "${i}"/> Name:</label>
                    <input type="text" id="adultName<c:out value = "${i}"/>" name="adultName<c:out value = "${i}"/>" required>
                    <label for="adultAge<c:out value = "${i}"/>">Age:</label>
                    <input type="number" id="adultAge<c:out value = "${i}"/>" name="adultAge<c:out value = "${i}"/>" min="18" max="150" required>
                    <br>
                </c:forEach>
                <c:if test="${param.childrenBooked > 0}">
                    <h4>Name of Children</h4>
                    <br>
                </c:if>
                <c:forEach var = "i" begin = "1" end = "${param.childrenBooked}">
                    <label for="childName<c:out value = "${i}"/>">Child <c:out value = "${i}"/> Name:</label>
                    <input type="text" id="childName<c:out value = "${i}"/>" name="childName<c:out value = "${i}"/>" required>
                    <label for="childAge<c:out value = "${i}"/>">Age:</label>
                    <input type="number" id="childAge<c:out value = "${i}"/>" name="childAge<c:out value = "${i}"/>" min="1" max="17" required>
                    <br>
                </c:forEach>

                <h4>Payment Details</h4>
                <br>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
                <br>
                <label for="cardno">Card Number:</label>
                <input type="number" id="cardno" name="cardno" required>
                <br>
                <label for="cardexpiry">Expiry Date:</label>
                <input type="month" id="cardexpiry" name="cardexpiry" required>
                <br>
                <label for="cardcvc">CVC:</label>
                <input type="number" id="cardcvc" name="cardcvc" required>
                <br>
                <button type="submit" style="width: 50%;">Book</button>
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

        <div class="booking-details">
            <h4>Extra Details</h4>
            <h4>Departure Flight</h4>
            <p>Ticket Transferrable: ${departureFlight.transferrable}</p>
            <br>
            <p>Ticket Refundable: ${departureFlight.refundable}</p>
            <br>
            <p>Ticket Exchangeable: ${departureFlight.exchangeable}</p>
            <br>
            <p>Earned Frequent Flyer Points: ${departureFlight.frequentFlyerPoints}</p>
            <br>
            <c:if test = "${param.pageDirect eq 'return'}">
            <h4>Return Flight</h4>
            <p>Ticket Transferrable: ${returnFlight.transferrable}</p>
            <br>
            <p>Ticket Refundable: ${returnFlight.refundable}</p>
            <br>
            <p>Ticket Exchangeable: ${returnFlight.exchangeable}</p>
            <br>
            <p>Earned Frequent Flyer Points: ${returnFlight.frequentFlyerPoints}</p>
            <br>
            </c:if>
        </div>
    </div>
</main>
</body>
</html>
