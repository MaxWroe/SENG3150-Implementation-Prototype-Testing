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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<%--<script src="/js/dynamicLink.js"></script>--%>
</head>
<body>
<!-- session handler -->
<%--<jsp:include page="sessionHandlerUser.jsp"/>--%>

<!-- Site header -->
<jsp:include page="../header.jsp"/>


<main class="main-content">

        <h1>Submit a Review</h1>


    <div class="wrap-submit-review">
        <!-- review form -->
        <form id="submitReviewForm" method="post" action="/submitReview">

            <!-- name -->
         <div class="register-row">
            <input class="input" placeholder="Name" id="name" name ="name" required/>
             <span class="symbol-input">
                <i class="fa fa-user-circle-o" aria-hidden="true"> </i>
            </span>
         </div>

            <!-- rating -->
            <div class="register-row">
                <div class="register-gender">
                    <label for="rating">Rating: </label>
                    <input class="slider" id="rating" name ="rating" type="range" min="1" max="5" value="3" required/>
                    <span id="ratingValue"></span>
                </div>
                <br>
            </div>

            <!-- review type -->
            <div class="register-group">
            <div class="register-row1">
                <div class="register-gender">
                    <input type="radio" id="Website" name="reviewType" value="Website" style="margin-left: 20px; margin-top: 10px" checked/>
                    <label for="Website">Website</label>
                </div>
            </div>
            <div class="register-row1">
                <div class="register-gender">
                    <input type="radio" id="Flight" name="reviewType" value="Flight" style="margin-left: 20px; margin-top: 10px"/>
                    <label for="Flight">Flight</label>
                </div>
            </div>
            <div class="register-row1">
                <div class="register-gender">
                    <input type="radio" id="Airport" name="reviewType" value="Airport" style="margin-left: 20px; margin-top: 10px"/>
                    <label for="Airport">Airport</label>
                </div>
            </div>
            </div>

            <br>
            <!-- comment  -->
            <div class="register-row">
                 <textarea class="input" placeholder="Comment"id="comment" name="comment" rows="4" cols="50" required></textarea> <br>
                <span class="symbol-input">
                    <i class="fa fa-comments" aria-hidden="true"> </i>
                </span>
            </div>


            <!-- userID -->
            <input type ="hidden" id="userID" name="userID" value="<%=session.getAttribute("userId")%>"/>

            <div class="register-group">
                <div class="register-row1">
                    <input class="input-submit" type="submit" value="Submit"/>
                </div>
                <div class="register-row1">
                    <input class="input-submit" type="reset" Value="Clear"/>
                </div>
            </div>

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
