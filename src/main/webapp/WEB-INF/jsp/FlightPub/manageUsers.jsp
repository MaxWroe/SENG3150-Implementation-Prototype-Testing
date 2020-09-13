<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 28/08/2020
  Time: 4:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">

        <h1>User Management</h1>
        <h4>Lists of Users</h4>
        <div class="my-airlines">

            <div class="wrap-airlines">
        <c:forEach items="${users}" var ="users">

            <c:if test="${users.ROLEDID == 'CUSTOMER'}">

                <div class="manage-airlines">
                    <div class="register-group">
                        <div class="register-row1">
                            <div class="booking-info-left" style="font-weight: bold"><c:out value= "${users.firstName}"></c:out></div><br>
                            <div class="booking-input-left">First Name</div>
                        </div>
                        <div class="register-row1">
                            <div class="booking-info-right" style="font-weight: bold"> <c:out value= "${users.lastName}"></c:out></div><br>
                            <div class="booking-input-right">Last Name</div>

                        </div>
                    </div>

                    <br>
                    <div class="register-group">
                        <div class="register-row1">
                            <div class="booking-input-left">Email</div><br>
                            <div class="booking-info-left"> <c:out value= "${users.email}"></c:out></div>
                        </div>
                        <br>
                        <div class="register-row1">
                            <div class="booking-info-right" style="font-weight: bold"><c:out value="${users.ROLEDID}"></c:out></div><br>
                            <div class="booking-input-right">Type</div>


                        </div>
                    </div>

                    <br>
                    <div class="register-group">
                        <div class="register-row1">
                            <form method="post" action="${pageContext.request.contextPath}/manageUsers/update">
                                <input type ="hidden" name="update" value="<c:out value= "${users.userID}"></c:out>" />
                                <button type="submit">Update</button>
                            </form>
                        </div>
                        <div class="register-row1">
                            <form method="post" action="${pageContext.request.contextPath}/manageUsers/remove">
                                <input type ="hidden" name="remove" value="<c:out value= "${users.userID}"></c:out>" />
                                <button type="submit">Remove</button>
                            </form>
                        </div>
                    </div>
                </div>

            </c:if>
        </c:forEach>
            </div>

            <div class="wrap-airlines">
                <c:forEach items="${users}" var ="users">
                    <c:if test="${users.ROLEDID == 'AGENT'}">

                        <div class="manage-airlines">
                            <div class="register-group">
                                <div class="register-row1">
                                    <div class="booking-info-left" style="font-weight: bold"><c:out value= "${users.firstName}"></c:out></div><br>
                                    <div class="booking-input-left">First Name</div>
                                </div>
                                <div class="register-row1">
                                    <div class="booking-info-right" style="font-weight: bold"> <c:out value= "${users.lastName}"></c:out></div><br>
                                    <div class="booking-input-right">Last Name</div>

                                </div>
                            </div>

                            <br>
                            <div class="register-group">
                                <div class="register-row1">
                                    <div class="booking-input-left">Email</div><br>
                                    <div class="booking-info-left"> <c:out value= "${users.email}"></c:out></div>
                                </div>
                                <br>
                                <div class="register-row1">
                                    <div class="booking-info-right" style="font-weight: bold"><c:out value="${users.ROLEDID}"></c:out></div><br>
                                    <div class="booking-input-right">Type</div>


                                </div>
                            </div>

                            <br>
                            <div class="register-group">
                                <div class="register-row1">
                                    <form method="post" action="${pageContext.request.contextPath}/manageUsers/update">
                                        <input type ="hidden" name="update" value="<c:out value= "${users.userID}"></c:out>" />
                                        <button type="submit">Update</button>
                                    </form>
                                </div>
                                <div class="register-row1">
                                    <form method="post" action="${pageContext.request.contextPath}/manageUsers/remove">
                                        <input type ="hidden"  name="remove" value="<c:out value= "${users.userID}"></c:out>" />
                                        <button type="submit">Remove</button>
                                    </form>
                                </div>
                            </div>
                        </div>


                    </c:if>
                </c:forEach>
            </div>

            <div class="wrap-airlines">
                <c:forEach items="${users}" var ="users">
                    <c:if test="${users.ROLEDID == 'FLIGHTPUB'}">
                        <div class="manage-airlines">
                            <div class="register-group">
                                <div class="register-row1">
                                    <div class="booking-info-left" style="font-weight: bold"><c:out value= "${users.firstName}"></c:out></div><br>
                                    <div class="booking-input-left">First Name</div>
                                </div>
                                <div class="register-row1">
                                    <div class="booking-info-right" style="font-weight: bold"> <c:out value= "${users.lastName}"></c:out></div><br>
                                    <div class="booking-input-right">Last Name</div>

                                </div>
                            </div>

                            <br>
                            <div class="register-group">
                                <div class="register-row1">
                                    <div class="booking-input-left">Email</div><br>
                                    <div class="booking-info-left"> <c:out value= "${users.email}"></c:out></div>
                                </div>
                                <br>
                                <div class="register-row1">
                                    <div class="booking-info-right" style="font-weight: bold"><c:out value="${users.ROLEDID}"></c:out></div><br>
                                    <div class="booking-input-right">Type</div>


                                </div>
                            </div>

                            <br>
                            <div class="register-group">
                                <div class="register-row1">
                                    <form method="post" action="${pageContext.request.contextPath}/manageUsers/update">
                                        <input type ="hidden"  name="update" value="<c:out value= "${users.userID}"></c:out>" />
                                        <button type="submit">Update</button>
                                    </form>
                                </div>
                                <div class="register-row1">
                                    <form method="post" action="${pageContext.request.contextPath}/manageUsers/remove">
                                        <input type ="hidden" name="remove" value="<c:out value= "${users.userID}"></c:out>" />
                                        <button type="submit">Remove</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </c:if>
                </c:forEach>
            </div>

            <div class="wrap-airlines">
                <c:forEach items="${users}" var ="users">
                    <c:if test="${users.ROLEDID == 'ADMIN'}">

                            <div class="manage-airlines">
                                <div class="register-group">
                                    <div class="register-row1">
                                        <div class="booking-info-left" style="font-weight: bold"><c:out value= "${users.firstName}"></c:out></div><br>
                                        <div class="booking-input-left">First Name</div>
                                    </div>
                                    <div class="register-row1">
                                        <div class="booking-info-right" style="font-weight: bold"> <c:out value= "${users.lastName}"></c:out></div><br>
                                        <div class="booking-input-right">Last Name</div>

                                    </div>
                                </div>

                                <br>
                                <div class="register-group">
                                    <div class="register-row1">
                                        <div class="booking-input-left">Email</div><br>
                                        <div class="booking-info-left"> <c:out value= "${users.email}"></c:out></div>
                                    </div>
                                    <br>
                                    <div class="register-row1">
                                        <div class="booking-info-right" style="font-weight: bold"><c:out value="${users.ROLEDID}"></c:out></div><br>
                                        <div class="booking-input-right">Type</div>


                                    </div>
                                </div>

                                <br>
                                <div class="register-group">
                                    <div class="register-row1">
                                        <form method="post" action="${pageContext.request.contextPath}/manageUsers/update">
                                            <input type ="hidden" name="update" value="<c:out value= "${users.userID}"></c:out>" />
                                            <button type="submit">Update</button>
                                        </form>
                                    </div>
                                    <div class="register-row1">
                                        <form method="post" action="${pageContext.request.contextPath}/manageUsers/remove">
                                            <input type ="hidden" name="remove" value="<c:out value= "${users.userID}"></c:out>" />
                                            <button type="submit">Remove</button>
                                        </form>
                                    </div>
                                </div>
                            </div>

                    </c:if>
                </c:forEach>
            </div>



        </div>


</main>

</body>
</html>
