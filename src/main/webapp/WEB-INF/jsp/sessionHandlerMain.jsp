<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 29/05/2020
  Time: 8:30 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- session handler -->
<%
    String userID = (String)session.getAttribute("userId");
    String email = request.getParameter("email");

    //checks if session is empty and not logged in
    if(userID == null && email == null){
        %><body><%

    //checks if logged in
    }else if(email != null && userID == null){

        session.setAttribute("userId", email);
        %> <body onload=userPage('/logout','Logout');> <%

    }else{

        %> <body onload=userPage('/logout','Logout');> <%

    }
%>