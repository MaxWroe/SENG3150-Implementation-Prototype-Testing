<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 4/06/2020
  Time: 4:25 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FAQs</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css">

    <script src="/js/dynamicLink.js"></script>
</head>
<body>
<!-- session handler -->
<jsp:include page="sessionHandlerGuest.jsp"/>
<!-- Site header -->
<jsp:include page="header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>Frequently Asked Questions</h1>

        <!-- frequently asked questions -->
        <div class="faqs-container">
            <div class ="faqs">
                <h4>What do I need to know before I make a booking?</h4>
                <p>It is very important to make sure that all the information given in the individual steps of the booking process is correct. Our booking process has various steps, which will give you the chance to review your data before you confirm and pay for your booking. In order to avoid problems after your booking has been made, please check that all your data (names and surnames, telephone numbers, email addresses, flight dates etc.) is correct.</p>

                <h4>Do I have to re-confirm my flight before I travel?</h4>
                <p>Flight timetables may be subject to change due to rescheduling by airlines, which is why we recommend calling your airline at least 72 hours before the scheduled departure of your flight in order to reconfirm your flight times.</p>

                <h4>I made a mistake in my booking. What should I do?</h4>
                <p>We would like to remind you that our ticketing process is automated and that once a booking has been confirmed, most tariffs do not allow for modifications or cancellations. If an airline or a tariff does allow for changes, we would ask you to request these in writing, and to be aware that you will be charged any corresponding fees.</p>
                <br><br>
                <p>Please bear in mind that once a booking has been made, it is not permitted to change the names of the travellers. In such cases, you would have to cancel the ticket (subject to any applicable fees) and buy a new one.</p>


            </div>
        </div>



    </div>
</main>

</body>
</html>
