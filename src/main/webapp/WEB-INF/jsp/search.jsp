<%--
  JSP for searching, sorting and displaying flight plans.
  SENG3150 Group 3
  Date: 19/05/2020
  Time: 2:53 pm
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Page</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">

    <script src="${pageContext.request.contextPath}/js/searchFormAssistor.js"></script>
    <script src="${pageContext.request.contextPath}/js/flightSelectAssistor.js"></script>
    <script src="${pageContext.request.contextPath}/js/dynamicLink.js"></script>
</head>
<body>
<!-- Session handler -->
<jsp:include page="sessionHandlerGuest.jsp"/>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <!-- The current search criteria -->
    <div id="search-criteria">
        <h4 style="margin: auto;">Search Criteria</h4>
        <!-- Flight search fields -->
        <form name="searchFlight" method="get" id="searchFlight" action="${pageContext.request.contextPath}/search" onsubmit="return validateForm()">
            <!-- Starting airport -->
            <div class="search-form-group">
                <input list="destinations" name="departureLocation" id="departureLocation" value="${param.departureLocation}" required>
                <p>to</p>
                <input list="destinations" name="arrivalLocation" id="arrivalLocation" value="${param.arrivalLocation}" required>
            </div>

            <!-- Airport destinations -->
            <datalist id="destinations">
                <option value="ADL">Adelaide - ADL</option>
                <option value="AMS">Amsterdam - AMS</option>
                <option value="ATL">Atlanta - ATL</option>
                <option value="BKK">Bangkok - BKK</option>
                <option value="BNE">Brisbane - BNE</option>
                <option value="CBR">Canberra - CBR</option>
                <option value="CDG">Paris - Charles De Gaulle - CDG</option>
                <option value="CNS">Cairns - CNS</option>
                <option value="DOH">Doha - DOH</option>
                <option value="DRW">Darwin - DRW</option>
                <option value="DXB">Dubai - DXB</option>
                <option value="FCO">Rome-Fiumicino - FCO</option>
                <option value="GIG">Rio De Janeiro - GIG</option>
                <option value="HBA">Hobart - HBA</option>
                <option value="HEL">Helsinki - HEL</option>
                <option value="HKG">Hong Kong - HKG</option>
                <option value="HNL">Honolulu - HNL</option>
                <option value="JFK">New York - JFK - JFK</option>
                <option value="JNB">Johannesburg - JNB</option>
                <option value="KUL">Kuala Lumpur - KUL</option>
                <option value="LAX">Los Angeles - LAX</option>
                <option value="LGA">New York - Laguardia - LGA</option>
                <option value="LGW">London-Gatwick - LGW</option>
                <option value="LHR">London-Heathrow - LHR</option>
                <option value="MAD">Madrid - MAD</option>
                <option value="MEL">Melbourne - MEL</option>
                <option value="MIA">Miami - MIA</option>
                <option value="MUC">Munich - MUC</option>
                <option value="NRT">Tokyo - Narita - NRT</option>
                <option value="OOL">Gold Coast - OOL</option>
                <option value="ORD">Chicago - OHare Intl. - ORD</option>
                <option value="ORY">Paris - Orly - ORY</option>
                <option value="PER">Perth - PER</option>
                <option value="SFO">San Francisco - SFO</option>
                <option value="SIN">Singapore - SIN</option>
                <option value="SYD">Sydney - SYD</option>
                <option value="VIE">Vienna - VIE</option>
                <option value="YYZ">Toronto - YYZ</option>
            </datalist>

            <div class="search-form-group">
                <select id="type" name="type" onchange="showDiv('search-form-return-date', 'returnDate', this)">
                    <option value="oneway">One-way</option>
                    <option value="return">Return</option>
                </select>
            </div>

            <!-- Ticket class -->
            <div class="search-form-group" value="${param.classCode}">
                <select id="classCode" name="classCode">
                    <option value="ECO">Economy</option>
                    <option value="PME">Premium Economy</option>
                    <option value="BUS">Business Class</option>
                    <option value="FIR">First Class</option>
                </select>
            </div>

            <div class="search-form-group">
                <!-- Adult passengers -->
                <input type="number" id="adults" name="adults" min="1" max="9" value="${param.adults}" required>
                <p>Adult/s</p>
                <!-- Children passengers -->
                <input type="number" id="children" name="children" min="0" max="9" value="${param.children}" required>
                <p>Children</p>
            </div>
            <br>

            <!-- Depart date -->
            <div class="search-form-group">
                <div class="home-form-group">
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <input type="date" id="departureDate" name="departureDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
                           onchange="restrictDepart()" value="${param.departureDate}" required>
                </div>
            </div>

            <!-- Return date -->
            <div id="search-form-return-date">
                <p>to</p>
                <input type="date" id="returnDate" name="returnDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
                       value="${param.returnDate}" disabled>
            </div>

            <!-- Apply sorting -->
            <div id="sort-criteria">
                <h4 style="display: inline;padding-right: 50px;">Sort Criteria</h4>
                <p>Sort by:</p>
                <select id="sortby" name="sortby">
                    <option value="price">Price</option>
                    <option value="time">Time</option>
                    <option value="capacity">Capacity</option>
                    <option value="stopovers">Stop Overs</option>
                </select>
                <select id="sortMethod" name="sortMethod">
                    <option value="descending">Descending</option>
                    <option value="ascending">Ascending</option>
                </select>
            </div>

            <button class="btn btn-lg btn-outline-success text-uppercase" type="submit">Go</button>
        </form>
        <script>
            // Check if incoming search trip type, set type select field and return date div
            var trip = "${param.type}";

            if (trip === "oneway")
            {
                document.getElementById("type").value = "oneway";
            }
            else if (trip === "return")
            {
                document.getElementById("type").value = "return";
                document.getElementById("search-form-return-date").style.display = 'inline';
                document.getElementById("returnDate").required = true;
                document.getElementById("returnDate").disabled = false;
            }

            // Set class type to incoming type
            document.getElementById("classCode").value = "${param.classCode}";
        </script>
    </div>

    <!-- If searched trip is one-way -->
    <c:if test = "${param.type eq 'oneway'}">
        <!-- Parse all returned flights -->

        <!-- Check if any flights returned -->
        <c:if test="${empty departureFlights.flightPlans}">
            <h4>No flights can be found that match the criteria!</h4>
        </c:if>

        <!-- Set flights returned (FlightHolder) to session scope -->
        <c:set var = "departureFlights" scope = "session" value = "${departureFlights}"/>

        <!-- For each flight returned display -->
        <c:forEach items="${departureFlights.flightPlans}" var="flightPlan">
            <div class="flight-result-oneway">
                <div class="flight-result-time">
                    <p>Depart time</p>
                    <h4>${flightPlan.departureDate}</h4>
                    <p>Arrival time</p>
                    <h4>${flightPlan.arrivalDate}</h4>
                </div>
                <div class="flight-result-details">
                    <%--
                    <p>${flightPlan.airlines}</p>
                    --%>
                    <br>
                    <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                    <br>
                    <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                </div>
                <div class="flight-result-cost">
                    <h3>$${flightPlan.price}</h3>
                </div>
                <div class="flight-result-book">
                    <!-- Information to send to booking controller -->
                    <form action="${pageContext.request.contextPath}/flightBookingOneway" method="post">
                        <input type="hidden" id="onewayBooking" name="trip" value="oneway">
                        <input type="hidden" id="onewayAdultsBooking" name="onewayAdultsBooking" value="${param.adults}">
                        <input type="hidden" id="onewayChildrenBooking" name="onewayChildrenBooking" value="${param.children}">
                        <input type="hidden" id="onewayClassBooking" name="onewayClassBooking" value="${param.classCode}">
                        <!-- Position of the specific flight plan within the FlightHolder FlightPlans list -->
                        <input type="hidden" id="onewayFlightPlan${flightPlan.position}" name="flightPlan" value="${flightPlan.position}">
                        <button type="submit">Book</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </c:if>

    <!-- If searched trip is return -->
    <c:if test = "${param.type eq 'return'}">
        <div class="flight-result-return">
            <div class="flight-result-return-departure" style="background: none">
                <h4 style="margin: 0;">Select a departure flight</h4>
            </div>
            <div class="flight-result-return-return" style="background: none">
                <h4 style="margin: 0;">Select a return flight</h4>
            </div>
        </div>

        <!-- Set flights returned (FlightHolders) to session scope -->
        <c:set var = "departureFlights" scope = "session" value = "${departureFlights}"/>
        <c:set var = "returnFlights" scope = "session" value = "${returnFlights}"/>

        <!-- Information to send to booking controller -->
        <form method="post" action="${pageContext.request.contextPath}/flightBookingReturn" onsubmit="return validateFlightSelection()">
            <input type="hidden" id="returnBooking" name="trip" value="return">
            <input type="hidden" id="returnAdultsBooking" name="returnAdultsBooking" value="${param.adults}">
            <input type="hidden" id="returnChildrenBooking" name="returnChildrenBooking" value="${param.children}">
            <input type="hidden" id="returnClassBooking" name="returnClassBooking" value="${param.classCode}">

            <div class="flight-result-return">
                <!-- Parse all returned flights -->

                <!-- Check if any departure flights returned -->
                <c:if test="${empty departureFlights.flightPlans}">
                    <div class="flight-result-return-windows">
                        <h4>No departure flights can be found that match the criteria!</h4>
                    </div>
                </c:if>

                <!-- For each departure flight returned display -->
                <div id="flight-departure-results">
                    <c:forEach items="${departureFlights.flightPlans}" var="flightPlan">
                        <div class="flight-result-return-windows">
                            <div class="flight-result-time">
                                <p>Depart time</p>
                                <h4>${flightPlan.departureDate}</h4>
                                <p>Arrival time</p>
                                <h4>${flightPlan.arrivalDate}</h4>
                            </div>
                            <div class="flight-result-details">
                                <%--
                                <p>${flightPlan.airlines}</p>
                                --%>
                                <br>
                                <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                                <br>
                                <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                            </div>
                            <div class="flight-result-cost">
                                <h3>$${flightPlan.price}</h3>
                            </div>
                            <div class="flight-result-book">
                                <!-- Position of the specific flight plan within the FlightHolder FlightPlans list -->
                                <label for="returnDepartureFlightPlan${flightPlan.position}">Select: </label>
                                <input type="radio" id="returnDepartureFlightPlan${flightPlan.position}" name="departure" value="${flightPlan.position}" onchange="updateCost('departure', '${flightPlan.price}')">
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <!-- Check if any return flights returned -->
                <c:if test="${empty returnFlights.flightPlans}">
                    <div class="flight-result-return-windows">
                        <h4>No return flights can be found that match the criteria!</h4>
                    </div>
                </c:if>

                <!-- For each return flight returned display -->
                <div id="flight-return-results">
                    <c:forEach items="${returnFlights.flightPlans}" var="flightPlan">
                        <div class="flight-result-return-windows">
                            <div class="flight-result-time">
                                <p>Depart time</p>
                                <h4>${flightPlan.departureDate}</h4>
                                <p>Arrival time</p>
                                <h4>${flightPlan.arrivalDate}</h4>
                            </div>
                            <div class="flight-result-details">
                                <%--
                                <p>${flightPlan.airlines}</p>
                                --%>
                                <br>
                                <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                                <br>
                                <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                            </div>
                            <div class="flight-result-cost">
                                <h3>$${flightPlan.price}</h3>
                            </div>
                            <div class="flight-result-book">
                                <!-- Position of the specific flight plan within the FlightHolder FlightPlans list -->
                                <label for="returnReturnFlightPlan${flightPlan.position}">Select: </label>
                                <input type="radio" id="returnReturnFlightPlan${flightPlan.position}" name="return" value="${flightPlan.position}" onchange="updateCost('return', '${flightPlan.price}')"></div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <br>
            <div id="flight-result-return-book">
                <h4 id="booking-cost">Total cost: </h4>
                <button type="submit" id="return-flight-book">Book</button>
            </div>
        </form>
    </c:if>
</main>
</body>
</html>
