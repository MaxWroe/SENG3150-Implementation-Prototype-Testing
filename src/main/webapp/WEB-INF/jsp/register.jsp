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
    <%--><script src="/js/formValidations.js"></script> --%>
</head>
<body>
<!-- session handler -->
<%--<jsp:include page="sessionHandlerRegister.jsp"/>--%>

<!-- Site header -->
<jsp:include page="header.jsp"/>

    <main class="main-content">
        <div class="card-body">
            <h1>Register</h1>
            <h2>${message}</h2>
            <h4>Fill in your information</h4>

                <!-- registration form-->
                <form id="registerForm" method="post" action="/register" onsubmit="return validateRegister()">

                    <jsp:include page="form.jsp"/>


                    <input type="submit" value="Register"/><input type="reset" value="Reset"/>

                </form>
        </div>
    </main>

</body>
</html>
