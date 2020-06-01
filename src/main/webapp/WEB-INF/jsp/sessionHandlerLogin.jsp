<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 29/05/2020
  Time: 8:33 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- session handler -->
<%
    String userID = (String)session.getAttribute("userId");

    //checks if user is logged in
    if(userID != null) {
        %> <jsp:forward page="errorPage.jsp"></jsp:forward> <%
    }else{
        %><body onload=dynamicLink('/register','Register');> <%
   }%>
