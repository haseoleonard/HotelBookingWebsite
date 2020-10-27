<%-- 
    Document   : Header
    Created on : Oct 27, 2020, 4:39:51 PM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
                  crossorigin="anonymous">
    </head>
    <nav class="navbar navbar-expand-lg navbar-light">
        <a class="navbar-brand" href="homepage.jsp">Hotel Booking</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item <c:if test="${requestScope.NEXT_PAGE=='homepage.jsp'}">active</c:if>">
                    <a class="nav-link" href="homepage.jsp">
                        Home
                        <c:if test="${requestScope.NEXT_PAGE=='homepage.jsp'}">
                            <span class="sr-only">(current)</span>
                        </c:if>
                    </a>
                </li>
                <li class="nav-item <c:if test="${requestScope.NEXT_PAGE=='viewcart.jsp'}">active</c:if>">
                    <a class="nav-link" href="viewcart.jsp">
                        Your Cart<c:if test="${not empty sessionScope.CART.items}">(${sessionScope.CART.items.size()})</c:if>
                        <c:if test="${requestScope.NEXT_PAGE=='viewcart.jsp'}">
                            <span class="sr-only">(current)</span>
                        </c:if>
                    </a>
                </li>
                <c:if test="${sessionScope.LOGIN_USER.role eq 0}">
                    <li class="nav-item <c:if test="${requestScope.NEXT_PAGE=='traceorder.jsp'}">active</c:if>">
                        <a class="nav-link" href="traceorder.jsp">
                            Check Your Order
                            <c:if test="${requestScope.NEXT_PAGE=='productlist.jsp'}"><span class="sr-only">(current)</span></c:if>
                            </a>
                        </li>
                </c:if>
                <c:if test="${sessionScope.LOGIN_USER.role eq 1}">
                    <li class="nav-item <c:if test="${requestScope.NEXT_PAGE=='adminpage.jsp'}">active</c:if>">
                        <a class="nav-link" href="adminpage.jsp">
                            Admin Interface
                            <c:if test="${requestScope.NEXT_PAGE=='adminpage.jsp'}">
                                <span class="sr-only">(current)</span>
                            </c:if>
                        </a>
                    </li>
                </c:if>
            </ul>
            <span class="navbar-text">
                <ul class="navbar-nav mr-auto">
                    <c:if test="${empty sessionScope.LOGIN_USER}">
                        <li class="nav-item <c:if test="${requestScope.NEXT_PAGE=='createaccount.jsp'}">active</c:if>">
                            <a class="nav-link" href="createaccount.jsp">
                                Sign Up
                                <c:if test="${requestScope.NEXT_PAGE=='createaccount.jsp'}">
                                    <span class="sr-only">(current)</span>
                                </c:if>
                            </a>
                        </li>
                        <li class="nav-item <c:if test="${requestScope.NEXT_PAGE=='login.jsp'}">active</c:if>">
                            <a class="nav-link" href="login.jsp">
                                Sign In
                                <c:if test="${requestScope.NEXT_PAGE=='login.jsp'}">
                                    <span class="sr-only">(current)</span>
                                </c:if>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${not empty sessionScope.LOGIN_USER}">
                        <span class="navbar-text">
                            Welcome,<font style="color: black">${sessionScope.LOGIN_USER.name}</font>
                        </span>           
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Sign Out</a>
                        </li>
                    </c:if>
                </ul>
            </span>
        </div>
    </nav>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" crossorigin="anonymous"></script>
</html>
