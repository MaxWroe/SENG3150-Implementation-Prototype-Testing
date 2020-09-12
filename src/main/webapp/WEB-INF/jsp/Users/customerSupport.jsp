<%@ page import="org.springframework.security.core.Authentication" %><%--
  JSP for sending ticket enquiries and updating them
  SENG3150 Group 3
  Date: 26/05/2020
  Time: 11:12 am
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
                    <h4>Ticket title: </h4> <p><c:out value= "${enquiries.title}"></c:out></p> <br>
                    <h4>Ticket enquiry: </h4><p><c:out value= " ${enquiries.description}"></c:out></p> <br>
                    <h4>Booking Number: </h4> <p><c:out value= " ${enquiries.bookingID}"></c:out></p> <br>
                    <h4>Submission Date: </h4><p><c:out value= " ${enquiries.enquiryDate}"></c:out></p> <br>
                    <h4>Agent Answer: </h4><p><c:out value= "${enquiries.enquiryResponse}"></c:out></p> <br>

                    <!--update ticket -->
                    <div class="update-enquiry">
                        <form id="updateForm" method="post" action="/customerSupport/update" >
                            <!-- ticket update -->
                            <label for="updateForm">Ticket update: </label><br>
                            <label for="ticketEnquiry">Ticket Enquiry Update: </label><br>
                            <textarea form ="updateForm" id="ticketEnquiryNew" name="ticketEnquiryNew" rows="4" cols="50" required></textarea> <br>
                            <input type ="hidden" id="ticketNumber" name="ticketID" value="${enquiries.enquiryID}" />
                            <button id="updateEnquiry" type="submit"> Update </button>
                        </form>
                    </div>
                </c:forEach>
            </div>


            <!-- submit an enquiry -->
            <form id="enquiryForm" method="post" action="/customerSupport/submit" >

                <!--ticket title -->
                <label for="ticketTitle">Ticket Title: </label>
                <input id="ticketTitle" name="ticketTitle" required /> <br>

                <!-- ticket enquiry -->
                <label for="ticketEnquiry">Ticket Enquiry: </label><br>
                <textarea form ="enquiryForm" id="ticketEnquiry" name="ticketEnquiry" rows="4" cols="50" required>Type enquiry here... </textarea> <br>

                <!--booking number -->
                <label for="bookingNumber">Booking Number: </label>
                <input type="number" id="bookingNumber" name="bookingNumber" required /> <br>

                <!-- userID -->
                <input type ="hidden" id="email" name="email" value="<security:authentication property="principal.username" />"/>
                <input type="submit" value="Submit Enquiry"/>

            </form>

        </div>
    </div>

</main>

</body>
</html>
