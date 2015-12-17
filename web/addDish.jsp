<%-- 
    Document   : addDish
    Created on : Nov 26, 2015, 10:25:16 PM
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
        <c:if test="${not empty Dishes}">
        <table border = 1 align="center">
            <h1 align = "center">Current Dishes</h1>
            <thead>
            <th>Dish Name</th>
            <th>Price</th>
            <th>Description</th>
            <th colspan="2">Action</th>
            </thead>
            <tbody>
                <c:forEach items = "${Dishes}" var="d">
                    <tr>
                        <form action ="AddDishServlet" method="post">
                        <td><input type="text" name="dname" value="<c:out value = "${d.dname}" />"/></td>
                        <td><input type="text" name="price" value="<c:out value = "${d.price}" />"/></td>
                        <td><textarea type="text" name="desc" rows="4" cols="25"><c:out value = "${d.desc}" /></textarea></td>
                        <td>
                            <input type="hidden" name="did" value="${d.did}"/>
                            <input type="hidden" name="action" value="update"/>
                            <input type="submit" id="rsubmit" value="Update"/>
                        </td>
                        </form>
                        <td>
                            <form action ="AddDishServlet" method="post">
                                <input type="hidden" name="did" value="${d.did}"/>
                                <input type="hidden" name="action" value="delete"/>
                                <input type="submit" id="rsubmit" value="Delete"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:if>
        <form action="restaurantPortal.jsp" align="center">
            <input type="submit" id="rsubmit" value="Back To Portal"/>
        </form>
        <form action ="AddDishServlet" id="frm1" method="post">
            <h1>Please enter dish info</h1>
            <div id = "Label">Dish ID</div><input readonly="readonly" type = "text" name ="did" value="${param.did}" /> <br />
            <div id = "Label">Dish Name</div><input type = "text" name ="dname" /> <br />
            <div id = "Label">Price</div><input type = "text" name ="price" /> <br />
            <div id = "Label">Description</div><textarea form="frm1" name ="desc" placeholder="Enter Text Here" rows = 4 cols = 25></textarea> <br />
            <input type ="hidden" value="${param.smcategory}" name="smcat"/>
            <input type="hidden" value="${param.mtype}" name="mtype"/>
            <input type ="submit" value ="Submit" id="rsubmit" />
        </form>
    </body>
</html>
