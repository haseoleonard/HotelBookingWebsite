<%-- 
    Document   : forgottenpassword
    Created on : Oct 27, 2020, 4:58:07 PM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Your Password</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp" flush="true"></jsp:include>
        <c:if test="${empty sessionScope.RESET_EMAIL}">
            <form action="sendemail" method="post">
                <input type="text" name="txtEmail" value="${param.txtEmail}"/>
                <c:if test="${not empty requestScope.NOT_EXISTED}">
                    <font style="color: red">Email Not Existed</font>
                </c:if>
                <button type="submit">Reset My Password</button>
            </form>
        </c:if>
        <c:if test="${not empty sessionScope.RESET_EMAIL}">
            <form action="checkcode" method="post">
                <input type="text" name="txtCode" value="${param.txtCode}"/>
                <input type="password" name="txtPassword" value=""/>
                <button type="submit">Submit</button>
            </form>
        </c:if>
    </body>
</html>
