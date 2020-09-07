<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  SENG3150 Group 3
  Date: 6/09/2020
  Time: 10:18 pm
--%>
<c:set var="tripType" scope="page" value="${param.type}"/>
<c:choose>
    <c:when test="${tripLeg eq 'depart'}">
        <c:set var = "flightList" scope = "page" value = "${flights.flightPlansDeparting}"/>
    </c:when>
    <c:when test="${tripLeg eq 'return'}">
        <c:set var = "flightList" scope = "page" value = "${flights.flightPlansReturning}"/>
    </c:when>
</c:choose>

<!-- For each flight returned display -->
<ul class="flight-list">
<c:forEach items="${flightList}" var="flightPlan" varStatus="loop">
    <li data-price="${flightPlan.price}" data-duration="${flightPlan.durationTotal}" data-stopovers="${flightPlan.numberStopOvers}"
        data-capacity="${flightPlan.numberAvailableSeats}"
        <c:forEach items="${flightPlan.airlines}" var="airline">data-airline="${airline}"</c:forEach>>

        <div class="<c:choose><c:when test="${flightPlan.containsSponsored()}">flight-plan-display-sponsored</c:when><c:otherwise>flight-plan-display</c:otherwise></c:choose>">
            <div class="flight-result-depart-time">
                <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${flightPlan.departureDate}" var="parsedDate" />

                <h4><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate}" /></h4>
                <p><fmt:formatDate type = "date" value = "${parsedDate}" /></p>
            </div>
            <div class="flight-result-stopovers">
                <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                <table>
                    <tr>
                        <c:forEach items="${flightPlan.flights}" var="flightPlanFlights" varStatus="flightsLoop">
                            <c:if test="${flightsLoop.count eq 1}">
                                <td>${flightPlanFlights.departureCode}</td>
                            </c:if>
                            <td>&#8594</td>
                            <c:if test = "${not empty flightPlanFlights.stopOverCode}">
                                <td>${flightPlanFlights.stopOverCode}</td>
                                <td>&#8594</td>
                            </c:if>
                            <td>${flightPlanFlights.destination}</td>
                        </c:forEach>
                    </tr>
                </table>
                <c:set var="hours" scope="page" value="${flightPlan.durationTotal / 60}"/>
                <fmt:parseNumber var="hours" integerOnly="true" type="number" value="${hours}" />
                <c:set var="minutes" scope="page" value="${flightPlan.durationTotal % 60}"/>
                <p>Total duration: ${hours} hours ${minutes} minutes</p>
            </div>
            <div class="flight-result-arrival-time">
                <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${flightPlan.arrivalDate}" var="parsedDate" />

                <h4><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate}" /></h4>
                <p><fmt:formatDate type = "date" value = "${parsedDate}" /></p>
            </div>
            <div class="flight-result-cost">
                <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                <c:choose>
                    <c:when test="${tripType eq 'oneway'}">
                        <!-- Information to send to booking controller -->
                        <form action="${pageContext.request.contextPath}/bookingPage" method="post">
                            <input type="hidden" id="onewayBooking" name="tripType" value="oneway">
                            <input type="hidden" id="onewayAdultsBooking" name="adultsBooking" value="${param.adults}">
                            <input type="hidden" id="onewayChildrenBooking" name="childrenBooking" value="${param.children}">
                            <input type="hidden" id="onewayClassBooking" name="classBooking" value="${param.classCode}">
                            <!-- Position of the specific flight plan within the FlightHolder flightPlansDeparting list -->
                            <input type="hidden" id="flightPlan${loop.count}" name="flightPlanPosition" value="${loop.count}">
                            <button type="submit">$${flightPlan.price}</button>
                        </form>
                    </c:when>
                    <c:when test="${tripType eq 'return'}">
                        <c:choose>
                            <c:when test="${tripLeg eq 'depart'}">
                                <!-- Information to send to booking controller -->
                                <!-- Position of the specific flight plan within the FlightHolder flightPlansDeparting list -->
                                <label for="departureFlightPlan${loop.count}">Select($${flightPlan.price}): </label>
                                <input type="radio" id="departureFlightPlan${loop.count}" name="departureFlightPlanPosition" value="${loop.count}"
                                       onchange="updateCost('departure', '${flightPlan.price}')">
                            </c:when>
                            <c:when test="${tripLeg eq 'return'}">
                                <!-- Information to send to booking controller -->
                                <!-- Position of the specific flight plan within the FlightHolder flightPlansDeparting list -->
                                <label for="returnFlightPlan${loop.count}">Select($${flightPlan.price}): </label>
                                <input type="radio" id="returnFlightPlan${loop.count}" name="returnFlightPlanPosition" value="${loop.count}"
                                       onchange="updateCost('return', '${flightPlan.price}')">
                            </c:when>
                        </c:choose>
                    </c:when>
                </c:choose>
                <c:if test="${flightPlan.containsSponsored()}">
                    <br>
                    <i>Sponsored</i>
                </c:if>
            </div>
        </div>
    </li>
</c:forEach>
</ul>