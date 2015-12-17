<%-- 
    Document   : listMenu
    Created on : Nov 26, 2015, 9:50:39 PM
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
        <h1 align="center">List of All Current Menus</h1>
        <table border = 1 align="center">
            <thead>
            <th>Menu Type</th>
            <th>Submenu Categories</th>
            </thead>
            <tbody>
                <c:forEach items = "${Menu}" var="m">
                    <tr align = "center">
                        <td><c:out value = "${m.mtype}"/></td>
                        <td>
                            <table border = 0>
                                <tbody>
                                    <c:forEach items = "${m.smenus}" var = "s">
                                        <tr>
                                            <form action = "AddDishServlet?method=get">
                                            <td>
                                                <input type = "text" outline:0 readonly="readonly" name="smcategory"value = "<c:out value = "${s.smcategory}" />"/>
                                                <input type = "hidden" name = "mtype" value="<c:out value = "${m.mtype}"/>"/>
                                            </td>
                                            <td>
                                                <input type="submit" value="Add/Edit Dishes" id ="rsubmit"/>
                                            </td>
                                            </form>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form action="restaurantPortal.jsp" align="center">
            <input type="submit" id="rsubmit" value="Back To Home"/>
        </form>
    </body>
</html>
