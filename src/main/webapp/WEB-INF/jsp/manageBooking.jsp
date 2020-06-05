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
<body>

<!-- session checker -->
<jsp:include page="sessionHandlerUser.jsp"/>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>Manage Booking</h1>
        <h4>Booked flights</h4>
        <h4>${message}</h4>

        <!-- user's booking/s-->

        <!-- parse all booked flights -->
        <div class="my-bookings">
            <c:forEach items="${booking}" var ="booking">

                <!-- checks if one-way or return -->
                <c:if test="${empty booking.airlineCode3}">
                    <h4>ONE-WAY</h4>
                </c:if>
                <c:if test="${not empty booking.airlineCode3}">
                    <h4>RETURN</h4>
                </c:if>

                <h4>Booking number: </h4> <p><c:out value= "${booking.bookingID}"></c:out></p><br>

                <h4>Airline from: </h4> <p><c:out value= " ${booking.airlineCode}"></c:out></p><br>
                <h4>Flight number from: </h4><p><c:out value= " ${booking.flightNumber}"></c:out></p> <br>
                <h4>Departure time: </h4><p><c:out value="${booking.departureTime}"></c:out></p> <br>

                <h4>Airline to: </h4><p><c:out value="${booking.airlineCode2}"></c:out></p> <br>
                <h4>Flight Number to: </h4> <p><c:out value= " ${booking.flightNumber2}"></c:out></p> <br>
                <h4>Arrival time: </h4><p><c:out value="${booking.departureTime2}" ></c:out></p> <br>

                <!-- if return -->
                <c:if test="${not empty booking.airlineCode3}">
                    <!-- return flights -->

                    <h4>Airline from: </h4><p><c:out value= " ${booking.airlineCode3}"></c:out></p> <br>
                    <h4>Flight number from: </h4><p><c:out value= " ${booking.flightNumber3}"></c:out></p> <br>
                    <h4>Departure time: </h4><p><c:out value="${booking.departureTime3}"></c:out></p> <br>

                    <h4>Airline to: </h4> <p><c:out value="${booking.airlineCode4}"></c:out></p> <br>
                    <h4>Flight Number to: </h4><p><c:out value= " ${booking.flightNumber4}"></c:out></p> <br>
                    <h4>Arrival time: </h4><p><c:out value="${booking.departureTime4}" ></c:out></p> <br>

                </c:if>

                <h4>Booking Date: </h4><p><c:out value= " ${booking.bookingDate}"></c:out></p> <br>

                <!-- cancel booking -->
                <div class="cancel-booking">
                <form id="cancelForm" method="post" action="${pageContext.request.contextPath}/manageBooking/cancel" style="display: none">

                    <!-- userID -->
                    <input type ="hidden" id="userID" name="userID" value="<%=session.getAttribute("userId")%>"/>
                    <!-- booking ID -->
                    <input type ="hidden" id="bookingID" name="bookingID" value="${booking.bookingID}"/>

                    <button type="submit">Cancel</button>

                </form>
                </div>

            </c:forEach>
        </div>

        <!-- temp bookings -->
        <div class ="my-bookings">
            <h4>Booking number: </h4> <p>1</p>  <br>
            <h4>Airline: </h4> <p>Qantas </p> <br>
            <h4>Flight Details: </h4> <p>XGH524</p>  <br>
            <h4>Date: </h4> <p>22/5/2020</p><br>
            <h4>Time: </h4>  <p>12:30am</p> <br>
            <h4>Location: </h4> <p>SYDNEY</p><br>
            <h4>Reviews: </h4> <p>Nice!</p> <br>

        </div>

        <!-- cancel a booking-->
       <!-- <button id="cancelBooking" type="submit" onclick="displayForm('cancelForm')"> Cancel a booking </button> -->


    </div>
</main>

</body>
</html>
