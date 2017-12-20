<%--
  Created by IntelliJ IDEA.
  User: bp
  Date: 11/3/2017
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>home</h1>
<a href="register.htm">register</a>
<a href="login.htm">register</a>
<br><br><br>
<form>

    <input id="message" type="text">

    <input onclick="wsSendMessage();" value="Echo" type="button">

    <input onclick="wsCloseConnection();" value="Disconnect" type="button">

</form>

<br>

<textarea id="echoText" rows="10" cols="30"></textarea>
<div style="hight:500px;width: 400px;background-color: lightgrey;" id="divID"></div>

<script type="text/javascript">

    var urli = "ws://" + document.location.host + document.location.pathname + "bpchat";
    var webSocket = new WebSocket(urli);

    var echoText = document.getElementById("echoText");

    echoText.value = "";
    var div = document.getElementById('divID');



    var message = document.getElementById("message");

    webSocket.onopen = function(message){ wsOpen(message);};

    webSocket.onmessage = function(message){ wsGetMessage(message);};

    webSocket.onclose = function(message){ wsClose(message);};

    webSocket.onerror = function(message){ wsError(message);};

    function wsOpen(message){

        var jsonData = JSON.parse(message.data);

        echoText.value += jsonData.message + "";
        div.innerHTML += '<p>'+jsonData.message+'</p>';

    }

    function wsSendMessage(){

        webSocket.send(message.value);

        // echoText.value += "Message sended to the server : " + message.value + "\n";

        message.value = "";


    }

    function wsCloseConnection(){

        webSocket.close();

    }

    function wsGetMessage(message){

        // echoText.value += "Message received from to the server : " + message.data + "\n";
        var jsonData = JSON.parse(message.data);
        if(jsonData.us == "Bhanuka"){
            div.innerHTML += '<p style="text-align: right;width: 100%;">'+jsonData.message+'</p>';
        }else{
            div.innerHTML += '<p>'+jsonData.message+'</p>';
        }

        //aditional
        echoText.value += jsonData.message + "";



    }

    function wsClose(message){

        echoText.value += "Disconnect ... ";

    }



    function wserror(message){

        echoText.value += "Error ... ";

    }

</script>
</body>
</html>
