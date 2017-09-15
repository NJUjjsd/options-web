package com.jjsd.options.controller;

import com.alibaba.fastjson.JSON;
import com.jjsd.options.entity.vo.UserInvestVOService;
import com.jjsd.options.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by zhujing on 2017/9/14.
 */
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {

    @Autowired
    private InvestService investService;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;


    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {


    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws InterruptedException {
        System.out.println("客户端发来消息");
        System.out.println(message);
        while (true) {
            Thread.sleep(3000);
            try {
                session.getBasicRemote().sendText(JSON.toJSONString(UserInvestVOService.generateInformation()));
//                session.getBasicRemote().sendText(JSON.toJSONString(investService.getDecision(message)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     @OnError
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误");
     error.printStackTrace();
     }


     public void sendMessage(String message) throws IOException {
     this.session.getBasicRemote().sendText(message);
     //this.session.getAsyncRemote().sendText(message);
     }

     */

}
