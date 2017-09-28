<%-- 
    Document   : loginForm
    Created on : 28.09.2017, 14:03:53
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Войти</h1>
        <form action="/WebShop2/checkLogin" method="POST" name="lForm">
            Логин:
            <input type="text" name="login">
            Пароль:
            <input type="text" name="password">
            <input type="submit" value="Войти">
        </form>
    </body>
</html>
