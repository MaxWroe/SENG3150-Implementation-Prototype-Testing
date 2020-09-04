<%--
  JSP for sending ticket enquiries and updating them
  SENG3150 Group 3
  Date: 26/05/2020
  Time: 11:12 am
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Support</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">

    <%--<script src="/js/dynamicLink.js"></script> --%>
</head>
<body>

<!-- session handler -->
<%--<jsp:include page="sessionHandlerUser.jsp"/>--%>

<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>Customer Support</h1>
        <h4>My Enquiries</h4>

        <div class="enquiries-container">
            <!-- user's enquiries-->
            <div class="my-enquiries">
                <c:forEach items="${enquiries}" var="enquiries">
                    <h4>Ticket title: </h4> <p><c:out value= "${enquiries.ticketTitle}"></c:out></p> <br>
                    <h4>Ticket enquiry: </h4><p><c:out value= " ${enquiries.ticketEnquiry}"></c:out></p> <br>
                    <h4>Email address: </h4> <p><c:out value= " ${enquiries.emailAddress}"></c:out></p> <br>
                    <h4>Booking Number: </h4> <p><c:out value= " ${enquiries.bookingNumber}"></c:out></p> <br>
                    <h4>Submission Date: </h4><p><c:out value= " ${enquiries.submissionDate}"></c:out></p> <br>
                    <h4>Agent Answer: </h4><p><c:out value= "${enquiries.agentAnswer}"></c:out></p> <br>

                    <!--update ticket -->
                    <div class="update-enquiry">
                        <form id="updateForm" method="post" action="/customerSupport/update" >
                            <!-- ticket update -->
                            <label for="update">Ticket update: </label><br>
                            <textarea id="update" name="ticketEnquiry" rows="4" cols="50" required>... </textarea> <br>

                            <button id="updateEnquiry" type="submit"> Update </button>
                        </form>
                    </div>
                </c:forEach>
            </div>


            <!-- temp enquiries -->
            <div class="my-enquiries">
                    <h4>Ticket title: </h4> <p>Help!</p> <br>
                    <h4>Ticket enquiry: </h4> <p>Cannot cancel a booking</p> <br>
                    <h4>Email address: </h4> <p> Test@test.test</p> <br>
                    <h4>Booking Number: </h4> <p> 1</p> <br>
                    <h4>Submission Date: </h4>  <p>22/05/2020</p> <br>
                    <h4>Agent Answer: </h4> <p>No clue</p> <br>

                <div class="update-enquiry">
                    <form id="updateForm1" method="post" action="/customerSupport/update" >
                        <!-- ticket update -->
                        <label for="update">Ticket update: </label><br>
                        <textarea id="update1" name="ticketEnquiry" rows="4" cols="50" required>... </textarea> <br>

                        <button id="updateEnquiry1" type="submit"> Update </button>
                    </form>
                </div>

            </div>

            <div class="my-enquiries">
                <h4>Ticket title: </h4> <p>Help!</p> <br>
                <h4>Ticket enquiry: </h4> <p>Cannot cancel a booking</p> <br>
                <h4>Email address: </h4> <p> Test@test.test</p> <br>
                <h4>Booking Number: </h4> <p> 1</p> <br>
                <h4>Submission Date: </h4>  <p>22/05/2020</p> <br>
                <h4>Agent Answer: </h4> <p>No clue</p> <br>

                <div class="update-enquiry">
                    <form id="updateForm2" method="post" action="/customerSupport/update" >
                        <!-- ticket update -->
                        <label for="update">Ticket update: </label><br>
                        <textarea id="update2" name="ticketEnquiry" rows="4" cols="50" required>... </textarea> <br>

                        <button id="updateEnquiry2" type="submit"> Update </button>
                    </form>
                </div>

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

                <!-- userID -->
                <input type ="hidden" id="userID" name="userID" value="<%=session.getAttribute("userId")%>"/>
                <input type="submit" value="Submit Enquiry"/>

            </form>

        </div>
    </div>

</main>

</body>
</html>
