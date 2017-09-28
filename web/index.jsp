<%-- 
    Document   : index
    Created on : 27.09.2017, 23:37:55
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
        <a href="/WebShop2/doBuy">Хочу купить</a> <a href="/WebShop2/logout"> Выход</a>
        <h1>Наши товары:</h1>
        Для покупки выберите продукт
        <ul>
            
            <c:forEach var="product" items="${products}">
                <li>
                    <c:if test="${user==true}">
                        <a href="/WebShop2/selectProduct?id=${product.id}">${product.name}, по ценe ${product.price/100} EUR - ${product.quantity} шт. </a>
                    </c:if>
                    <c:if test="${user==false}">
                        ${product.name}, по ценe ${product.price/100} EUR - ${product.quantity} шт.
                    </c:if>
                </li>
            </c:forEach>
             
        </ul>
        <a href="/WebShop2/proba">proba</a>
    </body>
</html>
