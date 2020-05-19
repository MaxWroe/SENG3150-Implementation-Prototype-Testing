<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 19/05/2020
  Time: 5:52 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>

    <!-- Navigation bar -->
    <header class="navbar">
        <div class="nav-logo">
            <a href="/home">FlightPub</a>
        </div>
        <nav class="primary-nav">
            <ul>
                <li>
                    <a class ="active" href="/home">Flight Search</a>
                </li>
                <li>
                    <a href="/login">Login</a>
                </li>
            </ul>
        </nav>
    </header>

    <main class="main-content">
        <div class="card-body">
            <h1>Register</h1>
            <h4>Fill in your information</h4>

                <!-- registration form-->
                <form id="registerForm" method="post" action="/register">

                    <!-- username -->
                    <label for="username">Username</label>
                    <input id="username" name ="username" required/> <br>

                    <!-- password -->
                    <label for="password">Password</label>
                    <input type ="password" id="password" name ="password" required/> <br>

                    <!-- email -->
                    <label for="email">Email</label>
                    <input type ="email" id="email" name ="email" required/> <br>

                    <!-- date of birth -->
                    <label for="dob">Date of birth</label>
                    <input type="date" id="dob" name ="dob" required/> <br>

                    <!-- user type -->
                    <label>User type: </label>

                    <input type="radio" id="personal" name="userType" value="Personal" checked/>
                    <label for="personal">Personal</label>

                    <input type="radio" id="business" name="userType" value="Business"/>
                    <label for="business">Business</label> <br>



                    <input type="submit" value="Submit"/><input type="reset" value="Reset"/>

                </form>
        </div>
    </main>

</body>
</html>
