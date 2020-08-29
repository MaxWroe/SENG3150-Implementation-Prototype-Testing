<%--
  JSP for unauthorised user attempting to access a page.
  User: Jacob Andronicos
  Date: 29/08/2020
  Time: 10:12 am
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Submit Review</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css">

    <%--<script src="/js/dynamicLink.js"></script>--%>
</head>
<body>
<!-- Site header -->
<jsp:include page="header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>Sorry, you do not have access to this page</h1>
        <h2>Check you are logged in to the correct account.</h2>
    </div>
</main>

</body>
</html>

