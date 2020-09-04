<%--
  JSP for redirecting when user logs out
  SENG3150 Group 3
  Date: 29/05/2020
  Time: 6:30 pm
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>

<body>
<!-- session checker -->
<%
    String userID = (String)session.getAttribute("userId");

    //checks if user is logged in
    if(userID == null) {
        %> <jsp:forward page="../errorPage.jsp"></jsp:forward> <%
    }else{
        session.invalidate();
        %> <jsp:forward page="../home.jsp"></jsp:forward> <%
   }%>


</body>
</html>
