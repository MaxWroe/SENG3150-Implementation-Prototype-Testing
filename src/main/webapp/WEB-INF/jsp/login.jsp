
<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 18/05/2020
  Time: 7:03 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

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
        </ul>
    </nav>
</header>

<main class="main-content">
    <div class="card-body">
        <h1>Login</h1>
        <h4>Enter your credentials</h4>

        <!-- login form -->
        <form id="loginForm" method="post" action="">

            <!-- username -->
            <label for="username">Username</label>
            <input id="username" name ="username" required/> <br>

            <!-- password -->
            <label for="password">Password</label>
            <input type ="password" id="password" name ="password" required/> <br>

            <input type="submit" value="Submit"/>

        </form>

    </div>
</main>

</body>


</html>
