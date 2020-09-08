<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.sql.Date" %><%--
  JSP for booking a specific flight plan.
  SENG3150 Group 3
  Date: 22/05/2020
  Time: 4:43 pm
--%>
<html>
<head>
    <title>Flight Booking Page</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/dynamicLink.js"></script>
</head>
<body>
<!-- Session handler -->
<%--<jsp:include page="sessionHandlerGuest.jsp"/>--%>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <h1>Flight Booking Details</h1>
    <!-- chosen flight plans -->
    <c:set var="departureFlightPlan" scope="page" value="${departureFlightPlan}"/>
    <c:set var="returnFlightPlan" scope="page" value="${returnFlightPlan}"/>
    <div class="flight-booking">
        <div id="side-bar">
            <h4>Departure Flight</h4>
            <h5>Price</h5>
            <p>$${departureFlightPlan.price}</p>
            <h5>Date/Time</h5>
            <p><fmt:formatDate value='${departureFlightPlan.departureDate}' type='date'/></p>
            <h5>Seat Class</h5>
            <p>${param.classBooking}</p>
            <h5>Seats Remaining</h5>
            <p>${departureFlightPlan.numberAvailableSeats}</p>
            <c:if test = "${param.tripType eq 'return'}">
            <h4>Return Flight</h4>
            <h5>Price</h5>
            <p>$${returnFlightPlan.price}</p>
            <h5>Date/Time</h5>
            <p><fmt:formatDate value='${returnFlightPlan.departureDate}' type='date'/></p>
            <h5>Seat Class</h5>
            <p>${param.classBooking}</p>
            <h5>Seats Remaining</h5>
            <p>${returnFlightPlan.numberAvailableSeats}</p>
            </c:if>
        </div>

        <div class="booking-details">
            <form method="post" action="${pageContext.request.contextPath}/bookFlight">
                <input type="hidden" id="adultsBooking" name="adultsBooking" value="${adultsBooked}">
                <input type="hidden" id="childrenBooking" name="childrenBooking" value="${childrenBooked}">

                <h4>Passenger Details</h4>
                <br>
                <!-- Autofill first adult from logged in account -->
                <label for="adultFirstName1">Booking Adult Name:</label>
                <input type="text" id="adultFirstName1" name="adultFirstName1" value="${fName}" readonly required>
                <input type="text" id="adultLastName1" name="adultLastName1" value="${lName}" readonly required>
                <label for="adultDOB1">DOB:</label>
                <input type="date" id="adultDOB1" name="adultDOB1" value="${dob}" readonly required>
                <br>
                <!-- Get details of all booking passengers -->
                <c:forEach var = "i" begin = "2" end = "${adultsBooked}">
                    <label for="adultFirstName<c:out value = "${i}"/>">Adult <c:out value = "${i}"/> Name:</label>
                    <input type="text" id="adultFirstName<c:out value = "${i}"/>" name="adultFirstName<c:out value = "${i}"/>" placeholder="First" required>
                    <input type="text" id="adultLastName<c:out value = "${i}"/>" name="adultLastName<c:out value = "${i}"/>" placeholder="Last" required>
                    <label for="adultDOB<c:out value = "${i}"/>">DOB:</label>
                    <input type="date" id="adultDOB<c:out value = "${i}"/>" name="adultDOB<c:out value = "${i}"/>" required>
                    <br>
                </c:forEach>
                <c:if test="${childrenBooked > 0}">
                    <h4>Name of Children</h4>
                    <br>
                </c:if>
                <c:forEach var = "i" begin = "1" end = "${childrenBooked}">
                    <label for="childFirstName<c:out value = "${i}"/>">Child <c:out value = "${i}"/> Name:</label>
                    <input type="text" id="childFirstName<c:out value = "${i}"/>" name="childFirstName<c:out value = "${i}"/>" placeholder="First" required>
                    <input type="text" id="childLastName<c:out value = "${i}"/>" name="childLastName<c:out value = "${i}"/>" placeholder="Last" required>
                    <label for="childDOB<c:out value = "${i}"/>">DOB:</label>
                    <input type="date" id="childDOB<c:out value = "${i}"/>" name="childDOB<c:out value = "${i}"/>" required>
                    <br>
                </c:forEach>

                <h4>Flight Details</h4>
                <table id="flight-details-table">
                    <tr>
                        <th>Departure Leg</th>
                    </tr>
                    <tr>
                        <th>Departure Location & Time</th>
                        <th>Stop Over Location & Time</th>
                        <th>Destination Location & Time</th>
                    </tr>
                <c:forEach items="${departureFlightPlan.flights}" var="flightPlanFlights" varStatus="flightsLoop">
                    <tr>
                        <td>${flightPlanFlights.departureCode},
                            <fmt:formatDate value='${flightPlanFlights.departureDate}' type='date' pattern='yyyy/MM/dd HH:mm'/></td>
                        <c:choose>
                            <c:when test="${not empty flightPlanFlights.stopOverCode}">
                                <td>${flightPlanFlights.stopOverCode},
                                    <fmt:formatDate value='${flightPlanFlights.arrivalStopOverTime}' type='date' pattern='yyyy/MM/dd HH:mm'/></td>
                            </c:when>
                            <c:otherwise>
                                <td>No Stop Over</td>
                            </c:otherwise>
                        </c:choose>
                        <td>${flightPlanFlights.destination},
                            <fmt:formatDate value='${flightPlanFlights.arrivalDate}' type='date' pattern='yyyy/MM/dd HH:mm'/></td>
                    </tr>
                </c:forEach>
                <c:if test = "${not empty returnFlightPlan}">
                    <tr>
                        <th>Return Leg</th>
                    </tr>
                <c:forEach items="${returnFlightPlan.flights}" var="flightPlanFlights" varStatus="flightsLoop">
                    <tr>
                        <td>${flightPlanFlights.departureCode},
                            <fmt:formatDate value='${flightPlanFlights.departureDate}' type='date' pattern='yyyy/MM/dd HH:mm'/></td>
                        <c:choose>
                            <c:when test="${not empty flightPlanFlights.stopOverCode}">
                                <td>${flightPlanFlights.stopOverCode},
                                    <fmt:formatDate value='${flightPlanFlights.arrivalStopOverTime}' type='date' pattern='yyyy/MM/dd HH:mm'/></td>
                            </c:when>
                            <c:otherwise>
                                <td>No Stop Over</td>
                            </c:otherwise>
                        </c:choose>
                        <td>${flightPlanFlights.destination},
                            <fmt:formatDate value='${flightPlanFlights.arrivalDate}' type='date' pattern='yyyy/MM/dd HH:mm'/></td>
                    </tr>
                </c:forEach>
                </c:if>
                </table>

                <h4>Payment Details</h4>
                <br>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
                <br>
                <label for="cardno">Card Number:</label>
                <input type="number" id="cardno" name="cardno" maxlength="16" minlength="16" required>
                <br>
                <label for="cardexpiry">Expiry Date:</label>
                <input type="month" id="cardexpiry" name="cardexpiry" required>
                <br>
                <label for="cardcvc">CVC:</label>
                <input type="number" id="cardcvc" name="cardcvc" maxlength="3" required>

                <br>
                <button type="submit" style="width: 50%;">Book</button>
            </form>
        </div>

        <div class="booking-details">
            <%--
            <!-- Did not have time to complete
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
            -->
            --%>
        </div>
    </div>
</main>
</body>
</html>
