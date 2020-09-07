<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h1>Travel Recommendations</h1>
    <div class="recommendation-slideshow">
        <c:forEach items="${recommendationPackages}" var="recommendationPackage">
            <c:set var="holidayPackage" value="${recommendationPackage.hp}" scope="page"/>
            <c:set var="flightPlan" value="${recommendationPackage.fp}" scope="page"/>
            <div class="recommendation-display-window fade">
                <img src="/img/${holidayPackage.destination}-splash-pic.jpg" style="width:100%">

                <div class="recommendation-description">
                    <div class="recommendation-description-stamp">
                        <img src="/img/stamp-pic.jpg">
                    </div>
                    <div class="recommendation-description-underline">
                        <span class="rc-span">
                        ${holidayPackage.countryName}
                        <br>
                        ${holidayPackage.destination}
                        <br>
                        ${holidayPackage.description}
                        </span>
                    </div>

                    <c:if test="${not empty flightPlan}">
                        <p>Found flight plan for this package</p>
                        <%--
                        <form name="searchFlight" method="post" id="searchFlight" action="${pageContext.request.contextPath}/search"
                              style="display:contents">
                            <input type="hidden" id="type" name="type" value="oneway">
                            <input type="hidden" id="classCode" name="classCode" value="${recommendationPackage.cc}">
                            <input type="hidden" id="adults" name="adults" value="${recommendationPackage.groupSize}">
                            <input type="hidden" id="departureLocation" name="departureLocation" value="${recommendationPackage.departureLocation}">
                            <input type="hidden" id="arrivalLocation" name="arrivalLocation" value="${holidayPackage.destination}">
                            <input type="hidden" id="departureDate" name="departureDate" value="${flightPlan.departureDate}">
                            <button type="submit">Get flight plan</button>
                        </form>
                        --%>
                    </c:if>
                </div>
            </div>
            <%--
                <div class="recommendation-destination-image" style="background-image: url(&quot/img/${holidayPackage.destination}-splash-pic.jpg&quot)">
                    <div class="recommendation-destination-image-caption">
                        <h2 style="margin-top: 0;">${holidayPackage.countryName}</h2>
                        <p>${holidayPackage.destination}</p>
                    </div>
                </div>
                <div class="recommendation-description">
                    <p>${holidayPackage.description}</p>

                    <c:if test="${empty flightPlan}">
                    <p>Found flight plan for this package</p>

                    <form name="searchFlight" method="get" id="searchFlight" action="${pageContext.request.contextPath}/search" onsubmit="return validateForm()">
                        <input type="hidden" id="type" name="type" value="oneway">
                        <input type="hidden" id="classCode" name="classCode" value="${recommendationPackage.cc}">
                        <input type="hidden" id="adults" name="adults" value="${recommendationPackage.groupSize}">
                        <input type="hidden" id="departureLocation" name="departureLocation" value="${recommendationPackage}">
                        <input type="hidden" id="arrivalLocation" name="arrivalLocation" value="${holidayPackage.destination}">
                        <input type="hidden" id="departureDate" name="departureDate" value="${flightPlan.departureDate}">
                        <button type="submit">Get flight plan</button>
                    </form>
                    </c:if>
                </div>
                --%>
        </c:forEach>
    <%--
    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
    --%>
    </div>
    <br>

    <div style="text-align:center">
        <c:forEach items="${recommendationPackages}" var="recommendationPackage" varStatus="loop">
            <span class="dot" onclick="currentSlide(${loop.count})"></span>
        </c:forEach>
    </div>

    <script>
        var slideIndex = 1;
        showSlides(slideIndex);

        // Next/previous controls
        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        // Thumbnail image controls
        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        function showSlides(n) {
            var i;
            var slides = document.getElementsByClassName("recommendation-display-window");
            var dots = document.getElementsByClassName("dot");
            if (n > slides.length) {slideIndex = 1}
            if (n < 1) {slideIndex = slides.length}
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            for (i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" active", "");
            }
            slides[slideIndex-1].style.display = "grid";
            dots[slideIndex-1].className += " active";
        }

        var slideIndex = 0;
        autoShowSlides();

        function autoShowSlides() {
            var i;
            var slides = document.getElementsByClassName("recommendation-display-window");
            var dots = document.getElementsByClassName("dot");
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            slideIndex++;
            if (slideIndex > slides.length) {slideIndex = 1}
            for (i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" active", "");
            }
            slides[slideIndex-1].style.display = "block";
            dots[slideIndex-1].className += " active";
            //setTimeout(autoShowSlides, 4000);
        }
    </script>
</main>
</body>
</html>
