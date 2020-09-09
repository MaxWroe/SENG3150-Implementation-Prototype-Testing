<%--
  JSP for user log-ins
  SENG3150 Group 3
  Date: 18/05/2020
  Time: 7:03 pm
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <%--<script src="/js/dynamicLink.js"></script>--%>
</head>
<body>
<!-- session handler -->
<%--<jsp:include page="sessionHandlerLogin.jsp"/>--%>

<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">

        <h1>Login</h1>
        <h2>${message}</h2>
        <h4>Enter your credentials</h4>


       <div class="wrap-login">
                <div class="login-form">
                    <div class="login-pic">
                        <img src="/img/login-splash.png" alt="LOGIN">
                    </div>
                     <!-- login form -->
                <form id="loginForm" method="post" action="appLogin">

                    <!-- email address -->
                    <div class="login-input">
                        <!-- <label for="username">Email address</label> -->
                        <input class="input" id="username" type="text" name ="username" placeholder="Email" required/> <br>
                        <span class="symbol-input">
							<i class="fa fa-envelope" aria-hidden="true"> </i>
						</span>
                    </div>

                    <!-- password -->
                    <div class="login-input">
                       <!-- <label for="password">Password</label> -->
                        <input class="input" type ="password" id="password" name ="password" placeholder="Password" required/> <br>
                        <span class="symbol-input">
							<i class="fa fa-lock" aria-hidden="true"> </i>
						</span>
                    </div>


                    <input class="input-submit" type="submit" value="Login"/>


                </form>
                </div>

       </div>



</main>

</body>


</html>
