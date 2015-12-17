<%-- 
    Document   : restaurantLogin
    Created on : Nov 21, 2015, 12:17:02 PM
    Author     : Aziz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dinner's Served</title>
    </head>
    <body>
        <h1>Login to Access Your Account</h1>
        <form action="RestaurantLoginServlet" method="post">
            <div id = "Label">Enter Account Number</div><input type = "text" name ="rid" /> <br />
            <div id = "Label">Enter Password</div><input type = "password" name ="password" /> <br />
            <input type ="submit" value ="Enter" id="rsubmit" />
        </form>
        <p>${param.message}</p>
    </body>
</html>
