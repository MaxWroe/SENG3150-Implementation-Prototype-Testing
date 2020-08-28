<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: jfpr2
  Date: 19/05/2020
  Time: 3:02 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<!-- Navigation bar -->
<header class="navbar">
    <div class="nav-logo">
        <a href="/home">FlightPub</a>
    </div>
    <nav class="primary-nav">
        <ul>
            <li class="nav-link">
                <a href="/home">Flight Search</a>
            </li>
            <li class="nav-link">
                <a href="/recommendations">Travel Recommendations</a>
            </li>

            <li class="nav-link">
                <a href="/faqs">FAQs</a>
            </li>

            <li class="nav-link">
                <a href="/reviews">Reviews</a>
            </li>

            <!-- changes depending if the user is logged in our not -->

            <!-- new login system -->
            <li class="nav-link">

                <!-- if not logged in -->
                <security:authorize access="!isAuthenticated()">
                    <div class="dropdown" id="dropdown" style="display: block">
                            <a>My Account</a>
                        <div class="dropdown-content">
                            <a href="/login">Login</a>
                            <a href="/register">Register</a>
                        </div>
                </security:authorize>


                <!-- if logged in -->
                <security:authorize access="isAuthenticated()">
                    <div class="dropdown" id="dropdown1">
                        <a><security:authentication property="name"/></a>

                        <div class="dropdown-content">
                            <a href="/accountDetails">Account Details</a>
                            <a href="/manageBooking">Manage Booking</a>
                            <a href="/customerSupport">Customer Support</a>
                            <a href="/submitReview">Submit Review</a>
                            <a href="/logout">Logout</a>
                        </div>

                    </div>
                </security:authorize>
            </li>

            <%--
            <!-- shown only if user is logged in -->
            <li class="nav-link">
               <div class="dropdown" id="dropdown" style="display: none">
                   <p id="myAccount">My Account</p>

                    <div class="dropdown-content">
                            <a href="/accountDetails">Account Details</a>
                            <a href="/manageBooking">Manage Booking</a>
                            <a href="/customerSupport">Customer Support</a>
                            <a href="/submitReview">Submit Review</a>
                    </div>
               </div>
           </li> --%>



        </ul>
    </nav>
</header>
