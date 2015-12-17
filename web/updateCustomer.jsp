<%-- 
    Document   : updateCustomer
    Created on : Nov 21, 2015, 1:57:32 PM
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
        <h1>Update Information</h1>
        <c:if test="${param.enter == '0'}">
            <form action="UpdateCustomerServlet?action=info" method="post">
                <table border = 1>
                    <thead>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Action</th>
                    </thead>
                    <tbody>
                            <tr>
                                <td>
                                    <input type="text" readonly="readonly" name = "username" value="<c:out value = "${sessionScope.Customer.username}"/>"/>
                                </td>
                                <td>
                                    <input type="text" name = "fname" value="<c:out value = "${sessionScope.Customer.fName}" />"/>
                                </td>
                                <td>
                                    <input type="text" name = "lname" value="<c:out value = "${sessionScope.Customer.lName}" />"/>
                                </td>
                                <td>
                                    <input type="text" name = "address" value="<c:out value = "${sessionScope.Customer.address}" />"/>
                                </td>
                                <td>
                                    <input type="text" name = "phone" value="<c:out value = "${sessionScope.Customer.phone}" />"/>
                                </td>
                                <td>
                                    <input type="text" name = "email" value="<c:out value = "${sessionScope.Customer.email}" />"/>
                                </td>
                                <td>
                                    <input type="submit" value="Submit Changes" id="rsubmit"/>
                                    <input type ="submit" value="Back to Home" id="ksubmit" onclick="form.action='customerPortal.jsp'"/>
                                </td>
                            </tr>
                    </tbody>
                </table>
            </form>
        </c:if>
        <c:if test="${param.enter == '1'}">
            <form action="UpdateCustomerServlet?action=pwd" method="post">
                <div id = "Label">Username </div><input type = "text" name ="username" readonly="readonly" value="${sessionScope.Customer.username}" /> <br />
                <div id = "Label">Enter Old Password </div><input type = "password" name ="oldpwd" /> <br />
                <div id = "Label">Enter New Password</div><input type = "password" name ="pwd" /> <br />
                <input type ="submit" value ="Change" id="rsubmit" />
                <input type ="submit" value="Back to Home" id="ksubmit" onclick="form.action='customerPortal.jsp'"/>
            </form>
            <p>${param.message}</p>
        </c:if>
    </body>
</html>
