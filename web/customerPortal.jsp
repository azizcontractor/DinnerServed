<%-- 
    Document   : customerPortal
    Created on : Nov 21, 2015, 12:59:25 PM
    Author     : Aziz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dinner's Served Customer</title>
    </head>
    <body>
        <table border = 1 align="right">
            <tbody>
                <tr>
                    <td><a href="UpdateCustomerServlet?method=get&action=info">Update Information</a></td>
                    <td><a href="UpdateCustomerServlet?method=get&action=pwd&">Change Password</a></td>
                    <td><a href="UpdateCustomerServlet?method=get&action=logout">Sign Out</a></td>
                    <td><a href="UpdateCustomerServlet?method=get&action=delete">Delete Account</a></td>
                    <td><a href="OrderHistoryServlet?method=get&action=vieword">Order History</a></td>
                </tr>
            </tbody>
        </table>
        <br/>
        <h1 align="center">Welcome to Dinner's Served Customer Portal</h1>
        <br/>
        <h2 align = "center">What are you in the mood for?</h2>
        <form align = "center"action="UpdateCustomerServlet" method="get">
            <input type="text" name ="search" size = 30/>
            <input type="submit" id="rsubmit" value = "Search">
            <input type="hidden" name ="action" value="search">
        </form>
        <c:if test = "${not empty Restaurants}">
            <table border = 1 align = "center">
            <thead>
            <th>Name</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Style</th>
            <th>Delivery Min</th>
            <th>Select</th>
            </thead>
            <tbody>
                <br/>
                <br/>
                <c:forEach items = "${Restaurants}" var="res">
                    <tr align = "center">
                        <td><c:out value = "${res.rName}" /></td>
                        <td><c:out value = "${res.address}" /></td>
                        <td><c:out value = "${res.phone}" /></td>
                        <td><c:out value = "${res.styleName}" /></td>
                        <td><c:out value = "${res.dMin}" /></td>
                        <td>
                            <form action="UpdateCustomerServlet" method="get">
                                <input type="hidden" name ="rid" value="${res.rID}"/>
                                <input type="hidden" name ="action" value="order"/>
                                <input type="hidden" name ="keyword" value="${param.keyword}"/>
                                <input type ="submit" id="rsubmit" value="Order"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:if>
        <c:if test="${not empty Menus}">
            <table border = 1 align = "center">
            <thead>
            <th>Menu Type</th>
            <th>Select</th>
            </thead>
            <tbody>
                <br/>
                <br/>
                <c:forEach items = "${Menus}" var="m">
                    <tr align = "center">
                        <td><c:out value = "${m.mtype}" /></td>
                        <td>
                            <form action="ListDishServlet" method="get">
                                <input type="hidden" name ="rid" value="${m.rid}"/>
                                <input type="hidden" name ="mtype" value="${m.mtype}"/>
                                <input type ="submit" id="rsubmit" value="Select"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
            <form action="UpdateCustomerServlet" method="get" align="center">
                <input type="submit" id="rsubmit" value="Back To Restaurants"/>
                <input type="hidden" name="action" value="search"/>
                <input type="hidden" name="search" value="${sessionScope.keyword}"/>
            </form>
        </c:if>
        <c:if test="${not empty Submenu}">
            <table align = "center" border = 1>
            <thead>
            <th>Category</th>
            <th>Dishes</th>
            </thead>
            <tbody>
                <br/>
                <br/>
                <c:set var= "i" value="${0}"></c:set>
                <c:forEach items = "${Submenu}" var="s" varStatus="status">
                    <tr align = "center">
                        <td><c:out value = "${s.smcategory}" /></td>
                        <td>
                        <table align ="center" BORDER=1 CELLPADDING=3 CELLSPACING=1 RULES=ROWS FRAME=BOX width = 500>
                            <colgroup>
                                <col span="1" style="width: 15%;">
                                <col span="1" style="width: 70%;">
                                <col span="1" style="width: 15%;">
                                <col span="1" style="width: 15%;">
                             </colgroup>
                                    <tbody border = 1>
                                        <c:forEach items = "${Dishes[i]}" var = "d">
                                        <tr>
                                            <td><c:out value = "${d.dname}"/></td>
                                            <td><c:out value = "${d.desc}"/></td>
                                            <td><c:out value = "${d.price}"/></td>
                                            <td>
                                                <form action ="CartServlet" method="get">
                                                    <input type="hidden" value="${d.did}" name="did"/>
                                                    <input type="hidden" value="${s.mtype}" name="mtype"/>
                                                    <input type="hidden" value="${s.rid}" name="rid"/>
                                                    <input type="submit" id="rsubmit" value="Add to Cart">
                                                </form>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                        
                                    </tbody>
                        </table>
                        </td>
                    </tr>
                    <c:set var = "i" value="${i+1}"></c:set>
                    </c:forEach>
            </tbody>
        </table>
                <form action ="UpdateCustomerServlet" method="get" align = center name="backfor">
                <input type="hidden" value="${Submenu[1].rid}" name="rid"/>
                <input type="submit" value="Select another Menu" id="rsubmit"/>
                <input type="hidden" value="order" name="action"/>
                <input type="hidden" value="${sessionScope.keyword}" name="search"/>
                <input type="submit" value="Back To Restaurants" id="rsubmit" onclick="document.forms['backfor'].elements['action'].value='search'"/>
            </form>
        </c:if>
        <c:if test="${not empty sessionScope.Cart}">
            <br/><br/>
            <h2 align ="center">Current Order</h2>
                <table align="center" border = 1>
                    <thead>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.Cart.dishes}" var = "d">
                        <tr align = "center">
                            <td><c:out value="${d.dname}"/></td>
                            <td><c:out value="${d.qty}"/></td>
                            <td><c:out value="${d.price}"/></td>
                            <td><form action = "CartServlet" method="post">
                                    <input type="submit" id="rsubmit" value="Remove"/>
                                    <input type="hidden" name="did" value="${d.did}"/>
                                    <input type="hidden" name="remove" value ="remove"/>
                                </form></td>
                        </tr>
                        </c:forEach>
                        <tr>
                            <td></td>
                            <td>Total</td>
                            <td>${sessionScope.Cart.total}</td>
                        </tr>
                    </tbody>
                </table>
            <form  align = "center" action="CartServlet" method="post">
                <input type="submit" id="rsubmit" value="Check Out" />
                <input type="hidden" name="remove" value="checkout"/>
            </form>
        </c:if>
        <c:if test="${param.enter == '0'}">
            <script>alert ("Information Updated Successfully")</script>
        </c:if>
        <c:if test="${param.enter == '1'}">
            <script>alert ("Password Changed Successfully")</script>
        </c:if>
        <c:if test="${param.enter == '3'}">
            <script>alert ("Your order has been submitted!!")</script>
        </c:if>
    </body>
</html>
