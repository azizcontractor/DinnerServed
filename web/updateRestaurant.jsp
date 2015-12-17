<%-- 
    Document   : updateRestaurant
    Created on : Nov 23, 2015, 1:07:53 PM
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
        <h1>Update Info</h1>
        <c:if test="${param.enter == '0'}">
            <form action="UpdateRestaurantServlet?action=info" method="post">
                <table border = 1>
                    <thead>
                        <th>Restaurant ID</th>
                        <th>Restaurant Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Delivery Minimum</th>
                        <th>Action</th>
                        <tbody>
                                <tr>
                                <td>
                                    <input type="text" readonly="readonly" name = "rid" value="<c:out value = "${sessionScope.Restaurant.rID}"/>"/>
                                </td>
                                <td>
                                    <input type="text" name = "rname" value="<c:out value = "${sessionScope.Restaurant.rName}" />"/>
                                </td>
                                <td>
                                    <input type="text" name = "address" value="<c:out value = "${sessionScope.Restaurant.address}" />"/>
                                </td>
                                <td>
                                    <input type="text" name = "phone" value="<c:out value = "${sessionScope.Restaurant.phone}" />"/>
                                </td>
                                <td>
                                    <input type="text" name = "dmin" value="<c:out value = "${sessionScope.Restaurant.dMin}" />"/>
                                </td>
                                <td>
                                    <input type="submit" value="Submit Changes" id="rsubmit"/>
                                    <input type ="submit" value="Back to Home" id="ksubmit" onclick="form.action='restaurantPortal.jsp'"/>
                                </td>
                            </tr>
                        </tbody>
                    </thead>
                </table>
            </form>
        </c:if>
        <c:if test="${param.enter == '1'}">
            <form action="UpdateRestaurantServlet?action=pwd" method="post">
                <div id = "Label">Restaurant Account Number </div><input type = "text" name ="rid" readonly="readonly" value="${sessionScope.Restaurant.rID}" /> <br />
                <div id = "Label">Enter Old Password </div><input type = "password" name ="oldpwd" /> <br />
                <div id = "Label">Enter New Password</div><input type = "password" name ="pwd" /> <br />
                <input type ="submit" value ="Change" id="rsubmit" />
                <input type ="submit" value="Back to Home" id="ksubmit" onclick="form.action='restaurantPortal.jsp'"/>
            </form>
                <p>${param.message}</p>
        </c:if>
    </body>
</html>
