<%-- 
    Document   : createCustomer
    Created on : Nov 18, 2015, 9:36:22 PM
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
        <form action="CreateCustomerServlet" method="post">
        <h1>Please enter your info</h1>
        <div id = "Label">Create Username</div><input type = "text" name ="username" /> <br />
        <div id = "Label">Create Password</div><input type = "password" name ="password" /> <br />
        <div id = "Label">Confirm Password</div><input type = "password" name ="cpassword" /> <br />
        <div id = "Label">First Name</div><input type = "text" name ="fName" /> <br />
        <div id = "Label">Last Name</div><input type = "text" name ="lName" /> <br />
        <div id = "Label">Address</div><input type = "text" name ="address" /> <br />
        <div id = "Label">Phone</div><input type = "text" name ="phone" /> <br />
        <div id = "Label">Email</div><input type = "text" name ="email" /> <br />
        <input type ="submit" value ="Submit" id="rsubmit" />
        </form>
        <p>${param.message}</p>
    </body>
</html>
