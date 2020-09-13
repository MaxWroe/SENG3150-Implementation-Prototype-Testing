
<%--
  JSP for system reviews
  SENG3150 Group 3
  Date: 23/08/2020
  Time: 12:50 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reviews</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <%--<script src="/js/dynamicLink.js"></script>--%>

</head>
<body>
<!-- session handler -->
<%--<jsp:include page="sessionHandlerGuest.jsp"/>--%>
<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">

        <h1>FlightPub Reviews</h1>

    <!-- checks if no user reviews yet -->
    <c:if test="${empty review}">
        <h4>No Reviews</h4>
    </c:if>

    <div class="wrap-review">

        <!-- gets all review information -->

        <c:forEach items="${review}" var ="review">
            <div class="manage-review">
                <div class="register-group">
                    <div class="register-row1">
                        <div class="booking-info-left"><c:out value= " ${review.name}"></c:out></div><br>
                        <div class="booking-input-left">Reviewer</div>
                    </div>
                    <div class="register-row1">
                        <c:if test="${review.rating == 1}">
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div><br>
                            <div class="booking-input-right">Rating</div>
                        </c:if>
                        <c:if test="${review.rating == 2}">
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div>
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div><br>
                            <div class="booking-input-right">Rating</div>
                        </c:if>
                        <c:if test="${review.rating == 3}">
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div>
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div>
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div><br>
                            <div class="booking-input-right">Rating</div>
                        </c:if>
                        <c:if test="${review.rating == 4}">
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div>
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div>
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div>
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div><br>
                            <div class="booking-input-right">Rating</div>
                        </c:if>
                        <c:if test="${review.rating == 5}">
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div>
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div>
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div>
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div>
                            <div class="booking-info-right"><i class="fa fa-star" aria-hidden="true"></i></div><br>
                            <div class="booking-input-right">Rating</div>
                        </c:if>

                    </div>
                </div>
                <br>
                <div class="register-row">
                    <div class="booking-input-left">Review Date</div><br>
                    <div class="booking-info-left"><c:out value= " ${review.reviewDate}"></c:out></div>
                </div>
                <br>
                <hr>
                <div class="register-row">
                    <div class="booking-input-center" style="color: #bbbbbb">Comment</div><br>
                    <div class="booking-input-center"><c:out value= " ${review.comment}"></c:out></div>
                </div>


            </div>
        </c:forEach>

    </div>


</main>

</body>
</html>
