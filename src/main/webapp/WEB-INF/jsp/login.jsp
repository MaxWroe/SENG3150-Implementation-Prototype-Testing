
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

    <script src="/js/dynamicLink.js"></script>
</head>

<!-- session checker -->
<%
    String userID = (String)session.getAttribute("userId");

    //checks if user is logged in
    if(userID != null) {
        %> <jsp:forward page="errorPage.jsp"></jsp:forward> <%
    }else{
        %><body onload=dynamicLink('/register','Register');> <%
   }%>


<!-- Site header -->
<jsp:include page="header.jsp"/>

<main class="main-content">
   <div class="card-body">
        <h1>Login</h1>
        <h2>${message}</h2>
        <h4>Enter your credentials</h4>


                 <!-- login form -->
            <form id="loginForm" method="post" action="/login">

                <!-- email address -->
                <label for="email">Email address</label>
                <input id="email" type="email" name ="email" required/> <br>

                <!-- password -->
                <label for="password">Password</label>
                <input type ="password" id="password" name ="password" required/> <br>


                <input type="submit" value="Login"/>


            </form>

        </div>


</main>

</body>


</html>
