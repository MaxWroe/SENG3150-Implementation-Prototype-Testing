<%--
  JSP for submitting reviews
  SENG3150 Group 3
  Date: 4/06/2020
  Time: 11:32 am
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Submit Review</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css">

    <%--<script src="/js/dynamicLink.js"></script>--%>
</head>
<body>
<!-- session handler -->
<%--<jsp:include page="sessionHandlerUser.jsp"/>--%>

<!-- Site header -->
<jsp:include page="../header.jsp"/>


<main class="main-content">
    <div class="card-body">
        <h1>Submit a Review</h1>

        <!-- review form -->
        <form id="submitReviewForm" method="post" action="/submitReview">

            <!-- name -->
            <label for="name">Name: </label>
            <input id="name" name ="name" required/> <br>

            <!-- rating -->
            <label for="rating">Rating: </label>
            <input class="slider" id="rating" name ="rating" type="range" min="1" max="5" value="3" required/>
            <span id="ratingValue"></span>
            <br>

            <!-- review type -->
            <label for="reviewType">Review Type: </label>
            <input id="reviewType" name ="reviewType" required/>Airport, website or airline?</input> <br>

            <!-- comment  -->
            <label for="comment">Comment: </label><br>
            <input id="comment" name="comment" rows="4" cols="50" required>Type review here... </input> <br>

            <!-- userID -->
            <input type ="hidden" id="userID" name="userID" value="<%=session.getAttribute("userId")%>"/>
            <input type="submit" value="Submit Review"/>

        </form>

        <!-- displaying range value -->
        <script>
            var rating = document.getElementById("rating");
            var output = document.getElementById("ratingValue");
            output.innerHTML = rating.value;

            rating.oninput = function() {
                output.innerHTML = this.value;
            }
        </script>
    </div>
</main>

</body>
</html>
