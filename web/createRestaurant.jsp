<%-- 
    Document   : createRestaurant
    Created on : Nov 20, 2015, 12:43:44 PM
    Author     : Aziz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dinner's Served</title>
    </head>
    <body>
        <h1>Please Enter Restaurant Info</h1>
        <form action="CreateRestaurantServlet" method ="post">
            <div id = "Label">Restaurant Account Number</div><input type = "text" readonly = "readonly" name ="acct" value = "${param.rid}"/> <br />
            <div id = "Label">Restaurant Name</div><input type = "text" name ="name" /> <br />
            <div id = "Label">Create Password</div><input type = "password" name ="password" /> <br />
            <div id = "Label">Enter Address</div><input type = "text" name ="address" /> <br />
            <div id = "Label">Enter Phone</div><input type = "text" name ="phone" /> <br />
            <div id = "Label">Set Delivery Minimum</div><input type = "text" name ="dmin" /> <br />
            <div id = "Label">Set Cuisine Style</div><input type = "text" name ="cstyle" /> <br />
            <input type ="submit" value ="Submit" id="rsubmit" />
        </form>
            <p>${param.message}</p>
    </body>
</html>
