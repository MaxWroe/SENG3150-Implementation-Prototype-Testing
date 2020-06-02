<%--
  Created by IntelliJ IDEA.
  User: jfpr2
  Date: 22/05/2020
  Time: 4:35 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Travel Recommendations</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <script src="/js/dynamicLink.js"></script>
</head>
<body>
<!-- session checker -->
<jsp:include page="sessionHandlerGuest.jsp"/>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<!-- Page content -->
<main class="main-content">
    <!-- stuff like deals, popular destinations, quiet (high seat capacity) flights? -->
    <h1>Travel Recommendations</h1>
    <h4>Deals</h4>
    <div id="deals">
        <p>Here are some hand selected flights.</p>
        <div id="deals-display-window">
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
</main>
</body>
</html>
