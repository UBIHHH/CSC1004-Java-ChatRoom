package com.lcj.chat.Controller;

import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

@RestController
@ServerEndpoint(value = "/ChatRoom/{username}")
//这个注解的作用是声明这是一个websocket的服务端，value的值是访问的地址
public class WebSocketController {
    static List<Session> sessionList = new ArrayList<Session>(); //用来存储每个客户端对应的WebSocketController对象
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "username") String username){
        sessionList.add(session); // 将当前的session加入到list中
        sendMsg(username+" joined the Chatroom！");

    }
    @OnMessage
    public void onMessage(String msg, @PathParam(value = "username") String username){
        sendMsg(username + " : " + msg);
    }
    @OnClose
    public void onClose(Session session , @PathParam(value = "username") String username){
        sessionList.remove(session);
        sendMsg(username + " left the Chatroom！");
    }
    @OnError
    public void onError(Throwable error){
        error.printStackTrace();
    }
    public void sendMsg(String msg){
        for(Session session : sessionList){
            session.getAsyncRemote().sendText(msg);
        }
    }
}
