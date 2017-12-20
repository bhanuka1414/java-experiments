<%--
  Created by IntelliJ IDEA.
  User: bp
  Date: 11/3/2017
  Time: 6:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Register</title>
</head>
<body>
<form action="reg" method="get">
<table>
    <tr>
        <td>Name : </td>
        <td><input type="text" name="name"></td>
    </tr>
    <tr>
        <td>E-mail</td>
        <td><input type="email" name="email"></td>
    </tr>
    <tr>
        <td>Password</td>
        <td><input type="password" name="password"></td>
    </tr>
</table>
    <button type="submit">Register</button>
</form>
</body>
</html>
