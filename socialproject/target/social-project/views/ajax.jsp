<%--
  Created by IntelliJ IDEA.
  User: bp
  Date: 11/22/2017
  Time: 10:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <title>Ajax Test</title>
    <script>
        $(function () {
            $('#b1').click(function () {
                $.ajax({
                    type:"GET",
                    url: "${pageContext.request.contextPath}/ajax",
                    success: function(result){
                    $("#ajx").html(result);
                }});
            });
        });
    </script>
</head>
<body>
<button id="b1">Check!!!</button>
<div id="ajx"></div>
</body>
</html>
