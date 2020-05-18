<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 18/05/2020
  Time: 7:54 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home</title>
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
                    <a href="/login">Logout</a>
                </li>
            </ul>
        </nav>
    </header>

    <main class="main-content">
        <div class="card-body">
            <h1>Welcome, ${username} </h1>

        </div>
    </main>

</body>
</html>
