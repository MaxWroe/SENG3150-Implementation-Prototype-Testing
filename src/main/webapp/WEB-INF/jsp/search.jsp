<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: jfpr2
  Date: 19/05/2020
  Time: 2:53 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Page</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">

    <script src="/js/searchFormAssistor.js"></script>
    <script src="/js/flightSelectAssistor.js"></script>
    <script src="/js/dynamicLink.js"></script>
</head>
<body>
<!-- session handler -->
<jsp:include page="sessionHandlerGuest.jsp"/>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <!-- The current search criteria -->
    <div id="search-criteria">
        <h4 style="margin: auto;">Search Criteria</h4>
        <!-- Flight search fields -->
        <form name="searchFlight" method="get" id="searchFlight" action="/search" onsubmit="return validateForm()">
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

            <div class="search-form-group">
                <select id="classCode" name="classCode">
                    <option value="ECO">Economy</option>
                    <option value="PME">Premium Economy</option>
                    <option value="BUS">Business Class</option>
                    <option value="FIR">First Class</option>
                </select>
            </div>

            <div class="search-form-group">
                <input type="number" id="adults" name="adults" min="1" max="9" value="${param.adults}" required>
                <p>Adult/s</p>
                <input type="number" id="children" name="children" min="0" max="9" value="${param.children}" required>
                <p>Children</p>
            </div>
            <br>

            <div class="search-form-group">
                <div class="home-form-group">
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <input type="date" id="departureDate" name="departureDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
                           onchange="restrictDepart()" value="${param.departureDate}" required>
                </div>
            </div>

            <div id="search-form-return-date">
                <p>to</p>
                <input type="date" id="returnDate" name="returnDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
                       value="${param.returnDate}" disabled>
            </div>

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

    <!-- Result template for return trips -->
    <div class="flight-result-return">
        <div class="flight-result-return-departure" style="background: none">
            <h4 style="margin: 0;">Select a departure flight</h4>
        </div>
        <div class="flight-result-return-return" style="background: none">
            <h4 style="margin: 0;">Select a return flight</h4>
        </div>
    </div>

    <form name="bookFlight" method="get" id="bookFlight" action="/bookingtemp" onsubmit="return validateFlightSelection()">
        <input type="hidden" id="pageDirect" name="pageDirect" value="return">
        <input type="hidden" id="adultsBooked" name="adultsBooked" value="${param.adults}">
        <input type="hidden" id="childrenBooked" name="childrenBooked" value="${param.children}">
        <div class="flight-result-return">
            <!-- Departure flight -->
            <div id="flight-departure-results">
                <div class="flight-result-return-departure">
                    <div class="flight-result-time">
                        <p>Depart time</p>
                        <h4>00:00AM</h4>
                        <p>Arrival time</p>
                        <h4>00:00AM</h4>
                    </div>
                    <div class="flight-result-details">
                        <h4>Company</h4>
                        <h4>Stop Overs</h4>
                        <h4>Flight ID</h4>
                    </div>
                    <div class="flight-result-cost">
                        <h3>$120</h3>
                    </div>
                    <div class="flight-result-book">
                        <label for="flightid1">Select: </label>
                        <input type="radio" id="flightid1" name="departure" value="flightid1" onchange="updateCost('departure', '120')">
                    </div>
                </div>
                <!-- Departure flight 2 -->
                <div class="flight-result-return-departure">
                    <div class="flight-result-time">
                        <p>Depart time</p>
                        <h4>00:00AM</h4>
                        <p>Arrival time</p>
                        <h4>00:00AM</h4>
                    </div>
                    <div class="flight-result-details">
                        <h4>Company</h4>
                        <h4>Stop Overs</h4>
                        <h4>Flight ID</h4>
                    </div>
                    <div class="flight-result-cost">
                        <h3>$410</h3>
                    </div>
                    <div class="flight-result-book">
                        <label for="flightid3">Select: </label>
                        <input type="radio" id="flightid3" name="departure" value="flightid3" onchange="updateCost('departure', '410')">
                    </div>
                </div>
                <!-- Departure flight 3 -->
                <div class="flight-result-return-departure">
                    <div class="flight-result-time">
                        <p>Depart time</p>
                        <h4>00:00AM</h4>
                        <p>Arrival time</p>
                        <h4>00:00AM</h4>
                    </div>
                    <div class="flight-result-details">
                        <h4>Company</h4>
                        <h4>Stop Overs</h4>
                        <h4>Flight ID</h4>
                    </div>
                    <div class="flight-result-cost">
                        <h3>$111</h3>
                    </div>
                    <div class="flight-result-book">
                        <label for="flightid4">Select: </label>
                        <input type="radio" id="flightid4" name="departure" value="flightid4" onchange="updateCost('departure', '111')">
                    </div>
                </div>
            </div>

            <!-- Return flight -->
            <div id="flight-return-results">
                <div class="flight-result-return-return">
                    <div class="flight-result-time">
                        <p>Depart time</p>
                        <h4>00:00AM</h4>
                        <p>Arrival time</p>
                        <h4>00:00AM</h4>
                    </div>
                    <div class="flight-result-details">
                        <h4>Company</h4>
                        <h4>Stop Overs</h4>
                        <h4>Flight ID</h4>
                    </div>
                    <div class="flight-result-cost">
                        <h3>$20</h3>
                    </div>
                    <div class="flight-result-book">
                        <label for="flightid2">Select: </label>
                        <input type="radio" id="flightid2" name="return" value="flightid2" onchange="updateCost('return', '20')">
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div id="flight-result-return-book">
            <h4 id="booking-cost">Total cost: </h4>
            <button type="submit" id="return-flight-book">Book</button>
        </div>
    </form>

    <!-- If searched trip is one-way -->
    <c:if test = "${param.type eq 'oneway'}">
        <!-- Parse all returned flights -->

        <!-- Check if any flights returned -->
        <c:if test="${empty departureFlights}">
            <h4>No flights can be found that match the criteria!</h4>
        </c:if>

        <c:set var = "departureFlights" scope = "session" value = "${departureFlights}"/>

        <!-- For each flight returned display -->
        <c:forEach items="${departureFlights}" var="flightPlan">
            <div class="flight-result-oneway">
                <div class="flight-result-time">
                    <p>Depart time</p>
                    <h4>${flightPlan.departureDate}</h4>
                    <p>Arrival time</p>
                    <h4>${flightPlan.arrivalDate}</h4>
                </div>
                <div class="flight-result-details">
                    <p>${flightPlan.airlines}</p>
                    <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                    <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                </div>
                <div class="flight-result-cost">
                    <h3>$${flightPlan.price}</h3>
                </div>
                <div class="flight-result-book">
                    <form action="/bookingtemp" method="post">
                        <input type="hidden" id="${flight.position}" name="departure" value="${flight.position}">
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

        <form name="bookFlight" method="post" id="bookFlight" action="/bookingtemp" onsubmit="return validateFlightSelection()">
            <!--<input type="hidden" id="pageDirect" name="pageDirect" value="return">
            <input type="hidden" id="adultsBooked" name="adultsBooked" value="${param.adults}">
            <input type="hidden" id="childrenBooked" name="childrenBooked" value="${param.children}">-->
            <div class="flight-result-return">
                <!-- Parse all returned flights -->

                <!-- Check if any departure flights returned -->
                <c:if test="${empty departureFlights}">
                    <div class="flight-result-return-departure">
                        <h4>No depature flights can be found that match the criteria!</h4>
                    </div>
                </c:if>

                <!-- For each depature flight returned display -->
                <!--<div id="flight-departure-results">-->
                    <c:forEach items="${departureFlights}" var="flightPlan">
                        <div class="flight-result-return-windows">
                            <div class="flight-result-time">
                                <p>Depart time</p>
                                <h4>${flightPlan.departureDate}</h4>
                                <p>Arrival time</p>
                                <h4>${flightPlan.arrivalDate}</h4>
                            </div>
                            <div class="flight-result-details">
                                <p>${flightPlan.airlines}</p>
                                <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                                <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                            </div>
                            <div class="flight-result-cost">
                                <h3>$${flightPlan.price}</h3>
                            </div>
                            <div class="flight-result-book">
                                <c:forEach items="${flightPlan.flights}" var="flight">
                                    <input type="hidden" id="${flight.flightNumber}" name="departure${flightPlan}" value="${flight.flightNumber}">
                                </c:forEach>
                                <label for="${flightPlan}">Select: </label>
                                <input type="radio" id="${flightPlan}" name="departure" value="${flightPlan}" onchange="updateCost('departure', '${flightPlan.price}')">
                            </div>
                        </div>
                    </c:forEach>
                <!--</div>-->

                <!-- Check if any return flights returned -->
                <c:if test="${empty returnFlights.flights}">
                    <div class="flight-result-return-return">
                        <h4>No return flights can be found that match the criteria!</h4>
                    </div>
                </c:if>

                <!-- For each return flight returned display -->
                <!--<div id="flight-return-results">-->
                    <c:forEach items="${returnFlights}" var="flightPlan">
                        <div class="flight-result-return-windows">
                            <div class="flight-result-time">
                                <p>Depart time</p>
                                <h4>${flightPlan.departureDate}</h4>
                                <p>Arrival time</p>
                                <h4>${flightPlan.arrivalDate}</h4>
                            </div>
                            <div class="flight-result-details">
                                <p>${flightPlan.airlines}</p>
                                <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                                <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                            </div>
                            <div class="flight-result-cost">
                                <h3>$${flightPlan.price}</h3>
                            </div>
                            <div class="flight-result-book">
                                <c:forEach items="${flightPlan.flights}" var="flight">
                                    <input type="hidden" id="${flight.flightNumber}" name="return${flightPlan}" value="${flight.flightNumber}">
                                </c:forEach>
                                <label for="${flightPlan}">Select: </label>
                                <input type="radio" id="${flightPlan}" name="return" value="${flightPlan}" onchange="updateCost('return', '${flightPlan.price}')"></div>
                        </div>
                    </c:forEach>
                <!--</div>-->
            </div>
            <br>
            <!--
            <div id="flight-result-return-book">
                <h4 id="booking-cost">Total cost: </h4>
                <button type="submit" id="return-flight-book">Book</button>
            </div>
            -->
        </form>
    </c:if>
</main>
</body>
</html>
