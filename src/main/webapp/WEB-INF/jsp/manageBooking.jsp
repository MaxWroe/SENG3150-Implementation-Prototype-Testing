<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 26/05/2020
  Time: 11:12 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Booking</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">

    <script src="/js/dynamicLink.js"></script>
</head>

<!-- session checker -->
<%
    String userID = (String)session.getAttribute("userId");

    //checks if user is logged in
    if(userID == null) {
        %> <jsp:forward page="errorPage.jsp"></jsp:forward> <%
    }else{
        %><body onload=userPage('/logout','Logout');> <%
    }%>


<!-- Site header -->
<jsp:include page="header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>Manage Booking</h1>
        <h4>Booked flights</h4>

        <!-- user's booking/s-->

        <!-- parse all booked flights -->
        <div class="my-bookings">
            <c:forEach items="${something}">
                <p>Booking number:<c:out value= "${bookingNumber}"></c:out></p> <br>
                <p>Airline:<c:out value= " ${airline}"></c:out></p> <br>
                <p>Flight Details:<c:out value= " ${flightDetails}"></c:out></p> <br>
                <p>Date:<c:out value= " ${date}"></c:out></p> <br>
                <p>Time:<c:out value= " ${time}"></c:out></p> <br>
                <p>Location: <c:out value= "${location}"></c:out></p> <br>
                <p>Reviews:<c:out value= " ${reviews}"></c:out></p> <br>
            </c:forEach>
        </div>

        <!-- temp bookings -->
        <div class ="my-bookings">
            <p>Booking number: 1</p> <br>
            <p>Airline: Qantas</p> <br>
            <p>Flight Details: XGH524</p> <br>
            <p>Date: 22/5/2020</p> <br>
            <p>Time: 12:30am</p> <br>
            <p>Location: SYDNEY</p> <br>
            <p>Reviews: Nice! </p> <br>

        </div>

        <!-- cancel a booking-->
        <button id="cancelBooking" type="submit" onclick="displayForm('cancelForm')"> Cancel a booking </button>

        <form id="cancelForm" method="post" action="/manageBooking/cancel" style="display: none">

            <label for="bookingNumber">Booking number you want to cancel </label>

            <!-- temp options-->
            <select id="bookingNumber" name="bookingNumber">
                <option value="1"> 1 </option>
                <option value="2"> 2 </option>
                <option value="3"> 3 </option>

            </select><br>

            <!-- userID -->
            <input type ="hidden" id="userID" name="userID" value="<%=userID%>"/>

            <input type="submit" value="Cancel Booking"/>

        </form>
    </div>
</main>

</body>
</html>
