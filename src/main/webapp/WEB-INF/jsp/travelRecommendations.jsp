<%--
  JSP for displaying travel recommendations.
  SENG3150 Group 3
  Date: 22/05/2020
  Time: 4:35 pm
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Travel Recommendations</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/dynamicLink.js"></script>
</head>
<body>
<!-- Session checker -->
<jsp:include page="sessionHandlerGuest.jsp"/>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <!-- possible content: deals, popular destinations, quiet (high seat capacity) flights -->
    <h1>Travel Recommendations</h1>
    <h4>Deals</h4>
    <div class="recommendation">
        <p>Here are some hand selected flights.</p>
        <div class="recommendation-display-window">
            <div class="flight-result-return-windows">
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
                    <form action="/bookingtemp" method="post">
                        <button type="submit">View</button>
                    </form>
                </div>
            </div>
            <!-- Departure flight 2 -->
            <div class="flight-result-return-windows">
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
                    <form action="/bookingtemp" method="post">
                        <button type="submit">View</button>
                    </form>
                </div>
            </div>
            <!-- Departure flight 3 -->
            <div class="flight-result-return-windows">
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
                    <form action="/bookingtemp" method="post">
                        <button type="submit">View</button>
                    </form>
                </div>
            </div>
            <!-- Departure flight 4 -->
            <div class="flight-result-return-windows">
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
                    <form action="/bookingtemp" method="post">
                        <button type="submit">View</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <h4>Common Searches</h4>
    <div class="recommendation">
        <p>Here are some common searches.</p>
        <div class="recommendation-display-window">
            <div class="filled-search">
                <h4>Winter School Holiday (NSW)</h4>
                <p>Monday 6 July to Friday 17 July</p>
                <button onclick="window.location.href='/search?type=return&departureDate=2020-07-06&returnDate=2020-07-13';">Search</button>
            </div>
            <div class="filled-search">
                <h4>Newcastle University Mid-Semester 2 Break</h4>
                <p>Monday 28 September to Friday 9 October</p>
                <button onclick="window.location.href='/search?type=return&departureDate=2020-09-28&returnDate=2020-10-09';">Search</button>
            </div>
            <div class="filled-search">
                <h4>Australia Long Weekend (Queen's Birthday)</h4>
                <p>Saturday 6 June to Monday 8 June</p>
                <button onclick="window.location.href='/search?type=return&departureDate=2020-09-28&returnDate=2020-10-09';">Search</button>
            </div>
        </div>
    </div>
</main>
</body>
</html>
