<%-- 
    Document   : login
    Created on : Oct 27, 2020, 4:39:33 PM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp" flush="true"></jsp:include>
            <form action="login" method="post">
                Username <input type="text" name="txtEmail" value="${param.txtEmail}" required/><br/>
            Password <input type="password" name="txtPassword"/><br/>
            <c:if test="${not empty requestScope.FAILED}">
                <font style="color: red">Invalid Login!</font><br/>
            </c:if>
            <input type="submit" name="btAction" value="Login" onclick="return checkCaptcha()"/>
        </form>
            <div class="g-recaptcha" data-sitekey="6LdxHN0ZAAAAALOMo76_M3go5J8Lw17eEjrV45IV"></div>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <script>
        function checkCaptcha(){
            return grecaptcha.getResponse().length !=0;
        }
    </script>
    </body>
</html>
