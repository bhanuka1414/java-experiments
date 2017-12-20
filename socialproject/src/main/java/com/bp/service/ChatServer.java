package com.bp.service;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
@ServerEndpoint("/bpchat")
public class ChatServer {
    static Set<Session> chatroomUsers = Collections.synchronizedSet(new HashSet<Session>());
    private String name = "Bhanuka";
    @OnOpen
    public void handleOpen(Session userSession) throws IOException  {
        chatroomUsers.add(userSession);
        userSession.getUserProperties().put("username", name);
        userSession.getBasicRemote().sendText(buildJsonData("NIBM GLOBLE CHAT" , "You are connected "+name+"!!!"));
    }
    @OnMessage
    public void handleMessage(String message , Session userSession)throws IOException {

        String username = (String) userSession.getUserProperties().get("username");
            Iterator <Session> iterator = chatroomUsers.iterator();
            while(iterator.hasNext()) iterator.next().getBasicRemote().sendText(buildJsonData(username , message));

    }

    @OnClose
    public void handleClose(Session userSession) {
        chatroomUsers.remove(userSession);
    }

    private String buildJsonData(String username , String message) {
        JsonObject jsonobject = Json.createObjectBuilder().add("message", username+" :  "+message).add("us" , username).build();

        StringWriter stringwriter = new StringWriter();
        try (JsonWriter jsonwriter = Json.createWriter(stringwriter)){jsonwriter.write(jsonobject);}
        return stringwriter.toString();
    }



}
