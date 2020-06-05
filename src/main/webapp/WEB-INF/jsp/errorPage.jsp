<%--
  JSP for redirecting unauthorized access to certain pages
  SENG3150 Group 3
  Date: 29/05/2020
  Time: 5:06 pm
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404 not found</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css">

</head>

<body>
<!-- Site header -->
<jsp:include page="header.jsp"/>
    <main class="main-content">
        <div class="card-body">
              <h1>Sorry the page you are after is not available.</h1>
        </div>
    </main>
</body>
</html>
