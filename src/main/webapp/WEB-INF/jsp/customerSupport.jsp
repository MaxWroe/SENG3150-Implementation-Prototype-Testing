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
    <title>Customer Support</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">

    <script src="/js/dynamicLink.js"></script>
</head>
<body onload=userPage("/home","Logout");>
<!-- Site header -->
<jsp:include page="header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>Customer Support</h1>
        <h4>My Enquiries</h4>

        <!-- user's enquiries-->
        <div class="my-enquiries">
            <c:forEach items="${enquiries}">
                <p>Ticket title:<c:out value= "${ticketTitle}"></c:out></p> <br>
                <p>Ticket enquiry:<c:out value= " ${ticketEnquiry}"></c:out></p> <br>
                <p>Email address:<c:out value= " ${emailAddress}"></c:out></p> <br>
                <p>Booking Number:<c:out value= " ${bookingNumber}"></c:out></p> <br>
                <p>Submission Date:<c:out value= " ${submissionDate}"></c:out></p> <br>
                <p>Agent Answer: <c:out value= "${agentAnswer}"></c:out></p> <br>
            </c:forEach>
        </div>

        <!-- temp enquiries -->
        <div class="my-enquiries">
                <p>Ticket title: Help!</p> <br>
                <p>Ticket enquiry: Cannot cancel a booking</p> <br>
                <p>Email address: test@test.test</p> <br>
                <p>Booking Number: 1</p> <br>
                <p>Submission Date: 22/05/2020</p> <br>
                <p>Agent Answer: No clue</p> <br>
        </div>


        <!-- submit an enquiry -->
        <button id="submitEnquiry" type="submit" onclick="displayForm('enquiryForm')"> Submit an enquiry </button>

        <form id="enquiryForm" method="post" action="/customerSupport/submit" style="display: none">

            <!--ticket title -->
            <label for="ticketTitle">Ticket Title: </label>
            <input id="ticketTitle" name="ticketTitle" required /> <br>

            <!-- ticket enquiry -->
            <label for="ticketEnquiry">Ticket Enquiry: </label><br>
            <textarea form ="enquiryForm" id="ticketEnquiry" name="ticketEnquiry" rows="4" cols="50" required>Type enquiry here... </textarea> <br>

            <!-- email address -->
            <label for="email">Email Address: </label>
            <input type="email" id="email" name="email" required /> <br>

            <!--booking number -->
            <label for="bookingNumber">Booking Number: </label>
            <input type="number" id="bookingNumber" name="bookingNumber" required /> <br>

            <input type="submit" value="Submit Enquiry"/>

        </form>

    </div>

</main>

</body>
</html>
