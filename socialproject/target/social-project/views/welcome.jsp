<%@ page import="com.bp.model.User" %><%--
  Created by IntelliJ IDEA.
  User: bp
  Date: 11/4/2017
  Time: 9:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ page session="true" %>
<%
    if (session.getAttribute("user") == null){
        response.sendRedirect("/social-project/login.htm");
    }
    User u = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>wl</title>
</head>
<body>
<h1><%=u.getName()%></h1>
<h1><%=u.getEmail()%></h1>
<h1><%=u.getUid()%></h1>
</body>
</html>
