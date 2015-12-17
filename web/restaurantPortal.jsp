<%-- 
    Document   : restaurantPortal
    Created on : Nov 23, 2015, 1:04:23 PM
    Author     : Aziz
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dinner's Served</title>
    </head>
    <body>
            <table border = 1 align="right">
            <tbody>
                <tr>
                    <td><a href="UpdateRestaurantServlet?method=get&action=info">Update Information</a></td>
                    <td><a href="UpdateRestaurantServlet?method=get&action=pwd&">Change Password</a></td>
                    <td><a href="UpdateRestaurantServlet?method=get&action=logout">Sign Out</a></td>
                    <td><a href="UpdateRestaurantServlet?method=get&action=delete">Delete Account</a></td>
                </tr>
            </tbody>
        </table>
        <br/>
        <h1 align="center">Welcome to Dinner's Served Restaurant Portal</h1>
        <h2 align = "center"> What would you like to do?</h2>
            <table border =1 align = "center">
                <tbody>
                    <tr><td><a href="createMenu.jsp">Create your Menu</a></td></tr>
                    <tr><td><a href="ListMenuServlet?method=get">View/Edit Menus</a></td></tr>
                    <tr><td><a href="PromotionServlet?method=get">Add Promotions</a></td></tr>
                    <tr><td><a href="OrderServlet?method=get">View Pending Orders</a></td></tr>
                </tbody>
            </table>
            <c:if test="${param.enter == '0'}">
                <script>alert ("Information Updated Successfully")</script>
            </c:if>
            <c:if test="${param.enter == '1'}">
                <script>alert ("Password Changed Successfully")</script>
            </c:if>
    </body>
</html>
