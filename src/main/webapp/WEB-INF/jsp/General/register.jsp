<%--
  JSP for user register
  SENG3150 Group 3
  Date: 19/05/2020
  Time: 5:52 am
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="/js/formValidations.js"></script>
</head>
<body>
<!-- session handler -->
<%--<jsp:include page="sessionHandlerRegister.jsp"/>--%>

<!-- Site header -->
<jsp:include page="../header.jsp"/>

    <main class="main-content">

            <h1>Register</h1>
            <h2>${message}</h2>
            <h4>Fill in your information</h4>

            <div class="wrap-register">
                <div class="register-form">

                    <!-- registration form-->
                    <form id="registerForm" method="post" action="/register" onsubmit="return validateRegister()">

                        <jsp:include page="../form.jsp"/>

                        <div class="register-group">
                            <div class="register-row1">
                                <input class="input-submit" type="submit" value="Register"/>
                            </div>
                            <div class="register-row1">
                                <input class="input-submit" type="reset" value="Reset"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

    </main>

</body>
</html>
