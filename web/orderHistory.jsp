<%-- 
    Document   : orderHistory
    Created on : Nov 30, 2015, 1:50:49 PM
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
        <c:if test="${not empty Orders}">
            <h1 align="center">Order History</h1>
            <table border ="1" align="center">
                <thead>
                <th>Order Date & Time</th>
                <th>Order Number</th>
                <th>Order Status</th>
                <th>Total</th>
                <th>Action</th>
                </thead>
                <tbody>
                <c:forEach items="${Orders}" var="o">
                        <tr>
                            <td><c:out value="${o.date}"/></td>
                            <td><c:out value="${o.ordid}"/></td>
                            <td><c:out value="${o.status}"/></td>
                            <td><c:out value="${o.total}"/></td>
                            <td>
                                <form action="OrderHistoryServlet" method="get">
                                    <input type ="hidden" value="${o.ordid}" name="oid"/>
                                    <input type="hidden" value="viewdet" name="action"/>
                                    <input type="submit" id="rsubmit" value="View Details"/>
                                </form>
                            </td>
                        </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
            <c:if test="${not empty Dishes}">
                <h1 align="center">Order Details</h1>
                <table align="center" border="1">
                    <thead>
                    <th>Dish Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Ordered From</th>
                    <th>Status</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${Dishes}" var="d">
                            <tr>
                                <td><c:out value="${d.dname}"/></td>
                                <td><c:out value="${d.qty}"/></td>
                                <td><c:out value="${d.price}"/></td>
                                <td><c:out value="${d.rname}"/></td>
                                <td><c:out value="${d.status}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
                <form action="customerPortal.jsp" align="center">
                    <input type ="submit" value="Back to Home" id="rsubmit"/>
                </form>
    </body>
</html>
