<%--
  JSP for user register for admin
  SENG3150 Group 3
  Date: 19/05/2020
  Time: 5:52 am
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>

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

    <h1>Add a new user</h1>
    <h2>${message}</h2>
    <h4>Fill in correct information</h4>

    <div class="wrap-register">
        <div class="register-form">

            <!-- registration form-->
            <form id="registerForm" method="post" action="addUsers/add" onsubmit="return validateRegister()">

                <jsp:include page="../form.jsp"/>

                <br>
                <div class="register-group">
                    <div class="register-row1">
                        <div class="register-gender">
                            <input type="radio" id="agent" name="userRole" value="AGENT" checked/>
                            <label for="agent">Agent</label>
                        </div>
                    </div>

                    <div class="register-row1">
                        <div class="register-gender">
                            <input type="radio" id="flightpub" name="userRole" value="FLIGHTPUB"/>
                            <label for="flightpub">Flightpub</label>
                        </div>
                    </div>

                    <div class="register-row1">
                        <div class="register-gender">
                            <input type="radio" id="admin" name="userRole" value="ADMIN"/>
                            <label for="admin">Admin</label> <br>
                        </div>
                    </div>

                </div>

                <div class="register-group">
                    <div class="register-row1">
                        <input class="input-submit" type="submit" value="Add"/>
                    </div>
                    <div class="register-row1">
                        <input class="input-submit" type="reset" value="Clear"/>
                    </div>
                </div>
            </form>
        </div>
    </div>

</main>

</body>
</html>
