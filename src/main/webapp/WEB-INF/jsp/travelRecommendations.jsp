<%--
  Created by IntelliJ IDEA.
  User: jfpr2
  Date: 22/05/2020
  Time: 4:35 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Travel Recommendations</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <script src="/js/dynamicLink.js"></script>
</head>

<!-- session checker -->
<%
    String userID = (String)session.getAttribute("userId");

    //checks if user is logged in
    if(userID == null) {
        %> <body> <%
    }else{
        %><body onload=userPage('/logout','Logout');> <%
    }%>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->

<!-- stuff like deals, popular destinations, quiet (high seat capacity) flights? -->

</body>
</html>
