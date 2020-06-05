<%--
  JSP for handling session in the login
  SENG3150 Group 3
  Date: 29/05/2020
  Time: 8:33 pm
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
