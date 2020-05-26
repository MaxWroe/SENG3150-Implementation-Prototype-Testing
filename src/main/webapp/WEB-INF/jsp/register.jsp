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
</head>
<body>
<!-- Site header -->
<jsp:include page="header.jsp"/>

    <main class="main-content">
        <div class="card-body">
            <h1>Register</h1>
            <h4>Fill in your information</h4>

                <!-- registration form-->
                <form id="registerForm" method="post" action="/register">

                    <!-- First Name -->
                    <label for="firstName">First Name: </label>
                    <input id="firstName" name ="firstName" required/> <br>

                    <!-- Last Name -->
                    <label for="lastName">Last Name: </label>
                    <input id="lastName" name ="lastName" required/> <br>

                    <!-- password -->
                    <label for="password">Password: </label>
                    <input type ="password" id="password" name ="password" required/> <br>

                    <!-- confirm password -->
                    <label for="confirmPassword">Confirm Password: </label>
                    <input type ="password" id="confirmPassword" name ="confirmPassword" required/> <br>

                    <!-- email -->
                    <label for="email">Email: </label>
                    <input type ="email" id="email" name ="email" required/> <br>

                    <!-- phone no -->
                    <label for="phoneNo">Phone No:</label>
                    <input type ="tel" id="phoneNo" name ="phoneNo" required/> <br>

                    <!-- date of birth -->
                    <label for="dob">Date of birth: </label>
                    <input type="date" id="dob" name ="dob" required/> <br>

                    <!-- user types -->
                    <label>User type: </label>

                    <input type="radio" id="personal" name="userType" value="Personal" checked/>
                    <label for="personal">Personal</label>

                    <input type="radio" id="business" name="userType" value="Business"/>
                    <label for="business">Business</label>

                    <input type="radio" id="family" name="userType" value="Family"/>
                    <label for="family">Family</label> <br>



                    <input type="submit" value="Register"/><input type="reset" value="Reset"/>

                </form>
        </div>
    </main>

</body>
</html>
