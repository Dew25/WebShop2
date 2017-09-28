<%-- 
    Document   : proba
    Created on : 28.09.2017, 22:23:11
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <select name="name">
            <c:forEach begin="1" end="${end}" varStatus="status">
                <option value="${status.count}">${status.count}</option>
            </c:forEach>
        </select>
    </body>
</html>
