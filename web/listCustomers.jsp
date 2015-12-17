<%-- 
    Document   : listCustomers
    Created on : Nov 18, 2015, 10:39:57 PM
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
        <h1>Current Customers</h1>
        <table border = 1>
            <thead>
            <th>Username</th>
            <th>Password</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Email</th>
            </thead>
            <tbody>
                <c:forEach items = "${Customer}" var="cus">
                    <tr>
                        <td><c:out value = "${cus.username}" /></td>
                        <td><c:out value = "${cus.password}" /></td>
                        <td><c:out value = "${cus.fName}" /></td>
                        <td><c:out value = "${cus.lName}" /></td>
                        <td><c:out value = "${cus.address}" /></td>
                        <td><c:out value = "${cus.phone}" /></td>
                        <td><c:out value = "${cus.email}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
