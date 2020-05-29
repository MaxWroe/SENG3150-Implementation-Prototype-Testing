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
                <a href="/search">Flight Search</a>
            </li>
            <li class="nav-link">
                <a href="/recommendations">Travel Recommendations</a>
            </li>

            <!-- changes depending if the user is logged in our not -->
            <li class="nav-link">
                <a id="dynamicLink" href="/login" >Login</a>
            </li>

            <!-- shown only if user is logged in -->
            <li class="nav-link">
               <div class="dropdown" id="dropdown" style="display: none">
                   <p id="myAccount">My Account
                       <i class="fa fa-caret-down"></i>
                   </p>

                    <div class="dropdown-content">
                        <form id="" method="post">
                            <a href="/accountDetails?userID=">Account Details</a>
                            <a href="/manageBooking?userID=">Manage Booking</a>
                            <a href="/customerSupport?userID=">Customer Support</a>
                        </form>
                    </div>
               </div>
           </li>


        </ul>
    </nav>
</header>
