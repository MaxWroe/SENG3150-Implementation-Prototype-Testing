<%--
  JSP for viewing bookings and cancelling it
  SENG3150 Group 3
  Date: 26/05/2020
  Time: 11:12 am
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


        <!-- user's booking/s-->
        <div class="my-bookings">

            <!--check if no booking -->
            <c:if test="${empty booking}">
                <h4>${message}</h4>
            </c:if>

            <!-- parse all booked flights -->
            <c:forEach items="${booking}" var ="booking">

                <!-- checks if one-way or return -->
                <c:if test="${booking.returnTrip == 0}">
                    <h4>ONE-WAY</h4>
                </c:if>
                <c:if test="${booking.returnTrip == 1}">
                    <h4>RETURN</h4>
                </c:if>

                <!-- checks group size -->
                <c:if test="${booking.groupSize > 1}">
                    <h4>GROUP-BOOKING</h4>
                </c:if>

                <!-- flight details -->

                <h4>Booking ID: </h4> <p><c:out value= "${booking.bookingID}"></c:out></p><br>

                <h4>First Name: </h4> <p><c:out value="${booking.firstName}"></c:out> </p>
                <h4>Last Name: </h4>  <p><c:out value="${booking.lastName}"></c:out></p>


                <h4>Leg 1</h4>
                <h4>Airline: </h4> <p><c:out value= " ${booking.airlineCode}"></c:out></p><br>
                <h4>Flight number: </h4><p><c:out value= " ${booking.flightNumber}"></c:out></p> <br>
                <h4>Departure time: </h4><p><c:out value="${booking.departureTime}"></c:out></p> <br>
                <h4>Destination: </h4><p><c:out value="${}"></c:out></p> <br>
                <h4>Arrival time:  </h4><p><c:out value="${}"></c:out></p> <br>
                <h4>Class type: </h4><p><c:out value="${booking.classCode}"></c:out></p>


                <!-- check if leg 2 -->
                <c:if test="${not empty booking.flightNumber2}">
                    <h4>Leg 2</h4>
                    <h4>Airline: </h4> <p><c:out value= " ${booking.airlineCode2}"></c:out></p><br>
                    <h4>Flight number: </h4><p><c:out value= " ${booking.flightNumber2}"></c:out></p> <br>
                    <h4>Departure time: </h4><p><c:out value="${booking.departureTime2}"></c:out></p> <br>
                    <h4>Destination: </h4><p><c:out value="${}"></c:out></p> <br>
                    <h4>Arrival time:  </h4><p><c:out value="${}"></c:out></p> <br>
                    <h4>Class type: </h4><p><c:out value="${booking.classCode2}"></c:out></p>
                </c:if>

                <!-- check if leg 3 exist-->
                <c:if test="${not empty booking.flightNumber3}">
                    <h4>Leg 3</h4>
                    <h4>Airline: </h4> <p><c:out value= " ${booking.airlineCode3}"></c:out></p><br>
                    <h4>Flight number: </h4><p><c:out value= " ${booking.flightNumber3}"></c:out></p> <br>
                    <h4>Departure time: </h4><p><c:out value="${booking.departureTime3}"></c:out></p> <br>
                    <h4>Destination: </h4><p><c:out value="${}"></c:out></p> <br>
                    <h4>Arrival time:  </h4><p><c:out value="${}"></c:out></p> <br>
                    <h4>Class type: </h4><p><c:out value="${booking.classCode3}"></c:out></p>
                </c:if>

                <!-- check if leg 4 exist -->
                <c:if test="${not empty booking.flightNumber4}">
                    <h4>Leg 4</h4>
                    <h4>Airline: </h4> <p><c:out value= " ${booking.airlineCode4}"></c:out></p><br>
                    <h4>Flight number: </h4><p><c:out value= " ${booking.flightNumber4}"></c:out></p> <br>
                    <h4>Departure time: </h4><p><c:out value="${booking.departureTime4}"></c:out></p> <br>
                    <h4>Destination: </h4><p><c:out value="${}"></c:out></p> <br>
                    <h4>Arrival time:  </h4><p><c:out value="${}"></c:out></p> <br>
                    <h4>Class type: </h4><p><c:out value="${booking.classCode4}"></c:out></p>
                </c:if>


                <h4>Booking Date: </h4><p><c:out value= " ${booking.bookingDate}"></c:out></p> <br>

                <!-- cancel booking -->
                <div class="cancel-booking">
                <form id="cancelForm" method="post" action="${pageContext.request.contextPath}/manageBooking/cancel">

                    <!-- userID -->
                    <input type ="hidden" id="userID" name="userID" value="<%=session.getAttribute("userId")%>"/>
                    <!-- booking ID -->
                    <input type ="hidden" id="bookingID" name="bookingID" value="<c:out value= "${booking.bookingID}"></c:out>" />

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

            <!-- cancel booking -->
            <div class="cancel-booking">
                <form id="cancelForm1" method="post" action="${pageContext.request.contextPath}/manageBooking/cancel">
                    <!-- userID -->
                    <input type ="hidden" id="userID1" name="userID1" value="<%=session.getAttribute("userId")%>"/>

                    <button type="submit">Cancel</button>
                </form>
            </div>

        </div>

        <!-- cancel a booking-->
       <!-- <button id="cancelBooking" type="submit" onclick="displayForm('cancelForm')"> Cancel a booking </button> -->


    </div>
</main>

</body>
</html>
