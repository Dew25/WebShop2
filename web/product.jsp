<%-- 
    Document   : product
    Created on : 28.09.2017, 20:23:24
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
        <h1>Выбранный продукт</h1>
        <div>${info}</div>
        ${product.name}, по ценe ${product.price/100} EUR - ${product.quantity} шт.
        
        <form action="buyProduct" method="POST" name="buy_product">
            <select name="quantityProduct">
                <c:forEach begin="1" end="${product.quantity}" varStatus="status">
                    <option value="${status.count}">${status.count}</option>
                </c:forEach>
            </select>
            <input type="hidden" value="${product.id}" name="product_id">
            <input type="submit" value="Купить">
        </form>
        
    </body>
</html>
