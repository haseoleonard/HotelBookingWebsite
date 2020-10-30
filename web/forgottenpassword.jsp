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
            <h3></h3>
            <form action="checkcode" method="post">
                Your Verification Code<input type="text" name="txtCode" value="${param.txtCode}"/>
                <c:if test="${not empty requestScope.INVALID_CODE}">${requestScope.INVALID_CODE}</c:if>
                    New Password<input type="password" id="password" id="confirm" name="txtPassword" value="" required maxlength="64"/>
                    <div id="passwordErr" style="color: red"></div>
                    Confirm<input type="password" name="txtConfirm" value="" required/>
                    <div id="confirmErr" style="color: red"></div>
                    <button type="submit">Submit</button>
                </form>
        </c:if>
        <script>
            function checkPassword() {
                let valid = true;
                document.getElementById("passwordErr").innerHTML = "";
                document.getElementById("confirmErr").innerHTML = "";
                let password = document.getElementById("password").value;
                let confirm = document.getElementById("confirm").value;
                if(password.trim().length<6||password.trim().length>64){
                    valid=false;
                    document.getElementById("passwordErr").innerHTML="Invalid Password";
                }else if(password.trim() !== confirm.trim()){
                    valid=false;
                    document.getElementById("confirmErr").innerHTML="Confirm Not Match";
                }
                return valid;
            }
        </script>
    </body>
</html>
