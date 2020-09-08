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

    <%--<script src="${pageContext.request.contextPath}/js/flightSelectAssistor.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/dynamicLink.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<!-- Session handler -->
<%--<jsp:include page="sessionHandlerGuest.jsp"/>--%>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <!-- The current search criteria -->
    <div id="search-grid">
        <div id="search-overlay">
            <jsp:include page="searchForm.jsp"/>
        </div>
        <script>
            function on() {
                document.getElementById("search-overlay").style.display = "block";
            }
        </script>
        <div id="results-search">
            <table>
                <tr>
                    <td>Search Criteria: <button onclick="on()">Change Search</button></td>
                    <td>${param.type}, ${param.classCode}</td>
                    <td>${param.adults} Adult/s, ${param.children} Children</td>
                    <td>${param.departureLocation} to ${param.arrivalLocation}</td>
                    <fmt:parseDate pattern="yyyy-MM-dd" value="${param.departureDate}" var="parsedDate" />
                    <td>Depart - <fmt:formatDate type="date" value="${parsedDate}" /></td>
                    <c:if test="${param.type eq 'return'}">
                        <fmt:parseDate pattern="yyyy-MM-dd" value="${param.returnDate}" var="parsedDate" />
                        <td>Return - <fmt:formatDate type="date" value="${parsedDate}" /></td>
                    </c:if>
                </tr>
            </table>

            <script>
                // Check if incoming search trip type, set type select field and return date div
                var trip = "${param.type}";

                // on load if search criteria being passed
                $(document).ready(function() {
                    // Set fields to incoming search parameters
                    $("#classCode").val("${param.classCode}");
                    $("#adults").val("${param.adults}");
                    $("#children").val("${param.children}");
                    $("#departureLocation").val("${param.departureLocation}");
                    $("#arrivalLocation").val("${param.arrivalLocation}");
                    $("#departureDate").val("${param.departureDate}");
                    $("#returnDate").val("${param.returnDate}");

                    $("#departureDateRange").val("${param.departureDateRange}");

                    // check if departure range option is selected
                    if($('#departureDateRange').val() !== "")
                    {
                        $("#departure-range").prop( "checked", true );
                        $("#departureDateRange").prop( "disabled", false );

                        $(this).toggleClass("up");
                        $('.extra-search-fields').toggle();

                        restrict_departure_range();

                        // change visibility of departure date range div
                        $("#departure-range-div").toggle(this.checked);
                        // make departure date range input required if option selected
                        $('#departureDateRange').prop("required", true );
                    }
                    // check if return range option is selected
                    if($('#returnDateRange').val() !== "")
                    {
                        $("#return-range").prop( "checked", true );
                        $("#returnDateRange").prop( "disabled", false );

                        $(this).toggleClass("up");
                        $('.extra-search-fields').toggle();

                        restrict_departure_range();

                        // change visibility of departure date range div
                        $("#return-range-div").toggle(this.checked);
                        // make departure date range input required if option selected
                        $('#returnDateRange').prop( "required", true );
                    }

                    if (trip === "oneway")
                    {
                        $("#type").val("oneway");
                        $("#form-group-return-date").hide();
                    }
                    else if (trip === "return")
                    {
                        $("#type").val("return");
                        $("#form-group-return-date").style.display = 'inline';
                        $("#returnDate").prop("required", true);
                        $("#returnDate").prop("disabled", false);
                    }
                });
            </script>
        </div>
        <div id="search-criteria">
            <!-- Apply sorting -->
            <div id="sort-criteria">
                <h4 style="display: inline;padding-right: 50px;">Sort Criteria</h4>
                <p style="display: inline">Sort by:</p>
                <select class="sortType">
                    <option value="price">Price</option>
                    <option value="duration">Total Duration</option>
                    <option value="capacity">Capacity</option>
                    <option value="stopovers">Stop Overs</option>
                </select>
                <select class="sortMethod">
                    <option value="descending">Descending</option>
                    <option value="ascending">Ascending</option>
                </select>
                <button id="sort">Sort</button>
            </div>
            <script>
                let method;
                let type;
                function sort_li(a, b) {
                    if(method === "descending")
                    {
                        return ($(b).data(type)) > ($(a).data(type)) ? 1 : -1;
                    }
                    if(method === "ascending")
                    {
                        return ($(b).data(type)) < ($(a).data(type)) ? 1 : -1;
                    }
                }
                $("#sort").on('click', function(){
                    method = $('select.sortMethod').children("option:selected").val();
                    type = $('select.sortType').children("option:selected").val();

                    $(".flight-list").children('li').sort(sort_li).appendTo(".flight-list");
                });
            </script>
        </div>
        <div id="search-sidebar">
            <h4>Side filters</h4>
            <p>Stop Overs</p>
            <label for="stopsFilter"></label>
            <input type="range" min="0" max="7" class="vHorizon" name="stopsFilter" id="stopsFilter">
            <p id="stops-filter-display"></p>
            <p>Airlines</p>
            <div id="airlineFilterDiv">
            </div>
        </div>
        <script>
            // on load occupy search filters
            $(document).ready(function() {
                // array of airlines and their id
                let airlines = [
                    ['AA', 'American Airlines'],
                    ['AC', 'Air Canada'],
                    ['AF', 'Air France'],
                    ['AI', 'Air India'],
                    ['AM', 'Air Mexico'],
                    ['AR', 'Aerolineas Argentinas'],
                    ['AY', 'Finnair'],
                    ['BA', 'British Airways'],
                    ['CA', 'Air China'],
                    ['CI', 'China Airlines'],
                    ['CO', 'Continental Airlines'],
                    ['CX', 'Cathay Pacific Airways'],
                    ['DJ', 'Virgin Blue'],
                    ['DL', 'Delta Air Lines'],
                    ['EI', 'Aer Lingus'],
                    ['EK', 'Qatar Airways'],
                    ['IB', 'Iberia'],
                    ['JL', 'Japan Airlines'],
                    ['JQ', 'Jetstar Airlines'],
                    ['KE', 'Korean Airlines'],
                    ['KL', 'KLM-Royal Dutch Airlines'],
                    ['LH', 'Lufthansa'],
                    ['LY', 'El Al Israel Airlines'],
                    ['MH', 'Malaysia Airlines'],
                    ['MS', 'Egyptair'],
                    ['MX', 'Mexicana de Aviacion'],
                    ['NA', 'North American Airlines'],
                    ['NW', 'Northwest Airlines'],
                    ['NZ', 'Air New Zealand'],
                    ['OS', 'Austrian Airlines'],
                    ['PR', 'Philippine Airlines'],
                    ['QF', 'Qantas Airways'],
                    ['QR', 'Emirates Airlines'],
                    ['RJ', 'Royal Jordanian'],
                    ['SA', 'South African'],
                    ['SK', 'SAS-Scandinavian Airlines'],
                    ['SQ', 'Singapore Airlines'],
                    ['SU', 'Aeroflot'],
                    ['TG', 'Thai Airways'],
                    ['TK', 'Turkish Airlines'],
                    ['TW', 'Trans World Airlines'],
                    ['UA', 'United Airlines'],
                    ['VH', 'Aeropostal Alas de Venezuela'],
                    ['VS', 'Virgin Atlantic Airways']
                ];
                // array of all airlines returned from search
                let returnedAirlines = $(".flight-list li").map(function () {
                    return $(this).data("airline");
                }).get();
                // don't display airlines not in search in side filters
                for (let i = airlines.length - 1; i >= 0; i--) {
                    if (!returnedAirlines.includes(airlines[i][0])) {
                        airlines.splice(i, 1);
                        console.log(airlines);
                    }
                }
                // create airline filter inputs
                for (let i = 0; i < airlines.length; i++) {
                    let label = "<label for=\"" + airlines[i][0] + "\">" + airlines[i][1] + " </label>";
                    let input = "<input type=\"checkbox\" id=\"" + airlines[i][0] + "\" value=\"" + airlines[i][0] + "\" name=\"airlineFilter\">";
                    $("#airlineFilterDiv").append(label);
                    $("#airlineFilterDiv").append(input);
                }

                // array of every stopover amount returned from search
                let returnedStopovers = $(".flight-list li").map(function () {
                    return $(this).data("stopovers");
                }).get();
                // set max amount on stops filter
                $("#stopsFilter").attr("max", Math.max.apply(null, returnedStopovers));
                $("label[for='stopsFilter']").text(Math.max.apply(null, returnedStopovers));
            });
        </script>
        <script>
            $(document).on('input change', '#stopsFilter', function() {
                $("label[for='stopsFilter']").text($(this).val());
            });

            var $select = $("#search-sidebar input");
            $select.change(function () {
            //$select.on('input', function () {
                var include = "";

                $select.each(function () {
                    if ($(this).prop("checked"))
                    {
                        var val = $(this).val();

                        switch ($(this).attr("name")) {
                            case "airlineFilter":
                                include += "[data-airline='" + val + "']";
                                break;
                        }
                    }
                    if ($(this).attr("name") === "stopsFilter")
                    {
                        var val = $(this).val();
                        include += "[data-stopovers='" + val + "']";
                    }
                });
                if (include === "")
                {
                    $(".flight-list li").show();
                }
                else {
                    $(".flight-list li").hide();
                    $(".flight-list li").filter(include).show();
                }
            });
        </script>

        <!-- If searched trip is one-way -->
        <c:if test = "${param.type eq 'oneway'}">
            <!-- Parse all returned flights -->

            <!-- Check if any flights returned -->
            <c:if test="${empty flights.flightPlansDeparting}">
                <h4>No flights can be found that match the criteria!</h4>
            </c:if>

            <!-- Set flights returned (FlightPlan list) to session scope -->
            <c:set var = "departureFlights" scope = "session" value = "${flights.flightPlansDeparting}"/>

            <!-- Display flight plans -->
            <c:set var="tripLeg" value="depart" scope="request"/>
            <jsp:include page="flightPlansDisplay.jsp"/>
        </c:if>

        <!-- If searched trip is return -->
        <c:if test = "${param.type eq 'return'}">
            <div id="flight-result-return-trip">
                <div id="flight-result-departure-header">
                    <h4 style="margin: 0;">Select a departure flight</h4>
                </div>
                <div id="flight-result-return-header">
                    <h4 style="margin: 0;">Select a return flight</h4>
                </div>


                <!-- Set flights returned (FlightHolders) to session scope -->
                <c:set var = "departureFlights" scope = "session" value = "${flights.flightPlansDeparting}"/>
                <c:set var = "returnFlights" scope = "session" value = "${flights.flightPlansReturning}"/>

                <!-- Information to send to booking controller -->
                <form method="post" action="${pageContext.request.contextPath}/bookingPage" onsubmit="return validateFlightSelection()"
                      style="display:contents">
                    <input type="hidden" id="returnBooking" name="tripType" value="return">
                    <input type="hidden" id="returnAdultsBooking" name="adultsBooking" value="${param.adults}">
                    <input type="hidden" id="returnChildrenBooking" name="childrenBooking" value="${param.children}">
                    <input type="hidden" id="returnClassBooking" name="classBooking" value="${param.classCode}">

                    <!-- Parse all returned flights -->
                    <c:choose>
                    <%-- Check if any departure flights returned --%>
                    <c:when test="${empty flights.flightPlansDeparting}">
                        <div class="flight-result-departure-window">
                            <h4>No departure flights can be found that match the criteria!</h4>
                        </div>
                    </c:when>
                    <c:otherwise>
                    <%-- For each departure flight returned display --%>
                    <div class="flight-result-departure-window">
                        <%-- Display flight plans --%>
                        <c:set var="tripLeg" value="depart" scope="request"/>
                        <jsp:include page="flightPlansDisplay.jsp"/>
                    </div>
                    </c:otherwise>
                    </c:choose>
                    <c:choose>
                    <%-- Check if any return flights returned --%>
                    <c:when test="${empty flights.flightPlansReturning}">
                        <div class="flight-result-return-window">
                            <h4>No return flights can be found that match the criteria!</h4>
                        </div>
                    </c:when>
                    <c:otherwise>
                    <%-- For each return flight returned display --%>
                    <div class="flight-result-return-window">
                        <%-- Display flight plans --%>
                        <c:set var="tripLeg" value="return" scope="request"/>
                        <jsp:include page="flightPlansDisplay.jsp"/>
                    </div>
                    </c:otherwise>
                    </c:choose>
                    <br>
                    <div id="flight-result-return-book">
                        <h4 id="booking-cost">Total cost: </h4>
                        <button type="submit" id="return-flight-book">Book</button>
                    </div>
                </form>
                <script>
                    function validateFlightSelection()
                    {

                        var checkDepart = document.querySelector('input[name="departureFlightPlanPosition"]:checked');
                        var checkReturn = document.querySelector('input[name="returnFlightPlanPosition"]:checked');

                        if (checkDepart == null || checkReturn == null)
                        {
                            alert("Please select a departure flight AND a return flight.");
                            return false;
                        }
                    }

                    var departureCost = 0;
                    var returnCost = 0;

                    function updateCost(travel, amount)
                    {
                        if (travel === "departure")
                        {
                            departureCost = amount;
                        }
                        if (travel === "return")
                        {
                            returnCost = amount;
                        }

                        document.getElementById("booking-cost").innerText = "Total cost: $" + (Number(departureCost) + Number(returnCost));
                    }
                </script>
            </div>
        </c:if>
    </div>
</main>
</body>
</html>
