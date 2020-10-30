<%-- 
    Document   : homepage
    Created on : Oct 27, 2020, 5:04:13 PM
    Author     : haseo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Booking</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/jspf/header.jsp" flush="true"></jsp:include>
            <form action="search" method="post">
                Area: 
                <select name="txtArea">
                    <option></option>
                    <c:forEach var="area" items="${requestScope.AREA_LIST}">
                        <option <c:if test="${area.areaName == param.txtArea}">selected="true"</c:if>>
                            ${area.areaName}
                        </option>
                    </c:forEach>
                </select><br/>
                Hotel: 
                <select name="txtHotelName">
                    <option></option>
                    <c:forEach var="hotel" items="${requestScope.HOTEL_LIST}">
                        <option <c:if test="${hotel.hotelName == param.txtHotelName}">selected="true"</c:if>>
                            ${hotel.hotelName}
                        </option>
                    </c:forEach>
                </select><br/>
                <label for="checkin">Check-in Date:</label>
                <input type="date" id="checkin" name="checkInDate" value="${param.checkInDate}" min="" /><br/>
            <label for="checkout">Check-out Date:</label>
            <input type="date" id="checkout" name="checkOutDate" value="${param.checkOutDate}" min="" /><br/>
            <input type="submit" name="btAction" value="Search"/>
        </form>
            <c:if test="${not empty requestScope.ROOM_LIST}">
                <c:forEach var="room" items="${requestScope.ROOM_LIST}">
                    ${room.hotelName}
                </c:forEach>
            </c:if>
    </body>
</html>
