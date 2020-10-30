<%-- 
    Document   : createaccount
    Created on : Oct 27, 2020, 4:56:58 PM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp" flush="true"></jsp:include>
        <form action="create" method="post">
            Email <input type="text" name="txtEmail" id="email" value="${param.txtEmail}" required maxlength="150"/><br/>
        <div id="emailErr" style="color: red"><c:if test="${not empty requestScope.EXISTED}">Email Existed!</c:if></div>
        Password <input type="password" name="txtPassword" id="password" value="" required maxlength="64"/><br/>
        <div id="passwordErr" style="color: red"></div>
        Confirm <input type="password" name="txtConfirm" id="confirm" value="" required maxlength="64"/><br/>
        <div id="confirmErr" style="color: red"></div>
        Name <input type="text" name="txtName" id="name" value="${param.txtName}" required maxlength="150"/><br/>
        <div id="nameErr" style="color: red"></div>
        Address <input type="text" name="txtAddress" id="address" value="${param.txtAddress}" required maxlength="250"/><br/>
        <div id="addressErr" style="color: red"></div>
        Phone <input type="tel" name="txtPhone" id="phone" value="${param.txtPhone}" required maxlength="12"/><br/>
        <div id="phoneErr" style="color: red"></div>
        <input type="submit" name="btAction" value="Create" onclick="return validate()"/><br/>
        </form>
        <script>
            function validate(){
                document.getElementById("emailErr").innerHTML="";
                document.getElementById("passwordErr").innerHTML="";
                document.getElementById("confirmErr").innerHTML="";
                document.getElementById("nameErr").innerHTML="";
                document.getElementById("addressErr").innerHTML="";
                document.getElementById("phoneErr").innerHTML="";
                let valid = true;
                let email = document.getElementById("email").value;
                let password = document.getElementById("password").value;
                let confirm = document.getElementById("confirm").value;
                let name = document.getElementById("name").value;
                let address = document.getElementById("address").value;
                let phone = document.getElementById("phone").value;
                if(!email.trim().match("[A-Za-z0-9]+([\\.\\-\\_][A-Za-z0-9])*@{1}([A-Za-z0-9]+[\\.\\-\\_])+[A-Za-z0-9]+")){
                    valid=false;
                    document.getElementById("emailErr").innerHTML="Invalid email!";
                }
                if(password.trim().length<6||password.trim().length>64){
                    valid=false;
                    document.getElementById("passwordErr").innerHTML="Invalid Password";
                }else if(password.trim() !== confirm.trim()){
                    valid=false;
                    document.getElementById("confirmErr").innerHTML="Confirm Not Match";
                }
                if(name.trim().length<2||name.trim().length>150){
                    valid=false;
                    document.getElementById("nameErr").innerHTML="Name Too Short";
                }
                if(address.trim().length<6||address.trim().length>250){
                    valid=false;
                    document.getElementById("addressErr").innerHTML="Address Too Short";
                }
                if(phone.trim().length<6||phone.trim().length>64||!phone.match("\\d+")){
                    valid=false;
                    document.getElementById("phoneErr").innerHTML="Invalid Phone";
                }
                return valid;
            }
        </script>
    </body>
</html>
