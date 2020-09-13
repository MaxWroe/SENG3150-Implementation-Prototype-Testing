<%--
  JSP for sending ticket enquiries and updating them
  SENG3150 Group 3
  Date: 26/05/2020
  Time: 11:12 am
--%>

<%@ page import="org.springframework.security.core.Authentication" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Support</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<%--<script src="/js/dynamicLink.js"></script> --%>
</head>
<body>

<!-- session handler -->
<%--<jsp:include page="sessionHandlerUser.jsp"/>--%>

<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">

        <h1>Customer Support</h1>
        <h4>My Enquiries</h4>

        <div class="wrap-stats">
            <!-- user's enquiries-->

                <c:forEach items="${enquiries}" var="enquiries">
                    <div class="manage-stats">
                        <div class="register-row">
                            <div class="booking-info-left" style="font-weight: bold"><c:out value= "${enquiries.title}"></c:out></div><br>
                            <div class="booking-input-left">Title</div>

                        </div>

                        <div class="register-row">
                            <div class="booking-input-right">Submitted Date</div><br>
                            <div class="booking-info-right"><c:out value= " ${enquiries.enquiryDate}"></c:out></div>
                        </div>

                        <br>
                        <hr>
                        <div class="register-row">
                            <div class="booking-input-center">Enquiry</div><br>
                            <div class="booking-input-center"><c:out value= " ${enquiries.description}"></c:out></div>
                        </div>

                        <br>
                        <hr>
                        <div class="register-row">
                            <div class="booking-input-center">Response</div><br>
                            <div class="booking-input-center"><c:out value= "${enquiries.enquiryResponse}"></c:out></div>
                        </div>


                     </div>
                    <%--
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
                    </div> --%>
                </c:forEach>

        </div>

    <div class="wrap-enquiry">

            <!-- submit an enquiry -->
            <form id="enquiryForm" method="post" action="/customerSupport/submit" >


                    <!--ticket title -->
                <div class="register-row">
                    <input class="input" placeholder="Subject" id="ticketTitle" name="ticketTitle" required /> <br>
                    <span class="symbol-input">
                        <i class="fa fa-ticket" aria-hidden="true"> </i>
                    </span>
                </div>

                <!-- ticket enquiry -->

                <div class="register-row">
                    <textarea class="input" placeholder="Enquiry" form ="enquiryForm" id="ticketEnquiry" name="ticketEnquiry" rows="4" cols="50" required></textarea> <br>
                    <span class="symbol-input">
                        <i class="fa fa-comments-o" aria-hidden="true"> </i>
                    </span>
                </div>
                <!--booking number -->

                <input type="hidden" id="bookingNumber" name="bookingNumber" value="1" />

                <!-- userID -->
                <input type ="hidden" id="email" name="email" value="<security:authentication property="principal.username" />"/>

                <div class="register-group">
                    <div class="register-row1">
                        <input class="input-submit" type="submit" value="Enquire"/>
                    </div>
                    <div class="register-row1">
                        <input class="input-submit" type="reset" value="Clear"/>
                    </div>
                </div>


            </form>
    </div>




</main>

</body>
</html>
