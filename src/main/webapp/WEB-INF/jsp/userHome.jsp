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

    <script src="/js/dynamicLink.js"></script>
</head>

<body onload=userHome('/home','Logout','${username}');>
<!-- Site header -->
<jsp:include page="header.jsp"/>

    <main class="main-content">
        <div class="card-body">
            <h1>Welcome, ${username} </h1>

        </div>
    </main>

</body>
</html>
