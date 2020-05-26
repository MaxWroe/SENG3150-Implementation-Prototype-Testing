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
    <script src="/js/formValidations.js"></script>
</head>
<body>
<!-- Site header -->
<jsp:include page="header.jsp"/>

    <main class="main-content">
        <div class="card-body">
            <h1>Register</h1>
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
