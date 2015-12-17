<%-- 
    Document   : listRestaurants
    Created on : Nov 19, 2015, 9:35:48 AM
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
        <h1>Restaurant List</h1>
        <table border = 1>
            <thead>
            <th>Restaurant ID</th>
            <th>Password</th>
            <th>Name</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Style</th>
            <th>Delivery Min</th>
            </thead>
            <tbody>
                <c:forEach items = "${Restaurant}" var="res">
                    <tr>
                        <td><c:out value = "${res.rID}" /></td>
                        <td><c:out value = "${res.password}" /></td>
                        <td><c:out value = "${res.rName}" /></td>
                        <td><c:out value = "${res.address}" /></td>
                        <td><c:out value = "${res.phone}" /></td>
                        <td><c:out value = "${res.styleName}" /></td>
                        <td><c:out value = "${res.dMin}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
