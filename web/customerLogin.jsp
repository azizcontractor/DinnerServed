<%-- 
    Document   : userLogin
    Created on : Nov 20, 2015, 11:44:29 AM
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
        <h1>Login to access your account</h1>
        <form action="CustomerLoginServlet" method="post">
            <div id = "Label">Enter Username</div><input type = "text" name ="username" /> <br />
            <div id = "Label">Enter Password</div><input type = "password" name ="password" /> <br />
            <input type ="submit" value ="Enter" id="rsubmit" />
        </form>
        <p>${param.message}</p>
    </body>
</html>
