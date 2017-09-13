package com.jjsd.options.controller;

import com.alibaba.fastjson.JSON;
import com.jjsd.options.service.InvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by zhujing on 2017/9/13.
 */

@Controller
@ServerEndpoint("/websocket")
public class Websocket {

    @Autowired
    private InvestService investService;

    @OnMessage
    public void onMessage(String email, Session session) throws IOException, InterruptedException {

        // Send messages to the client every 30 seconds

        //返回数据格式未处理
        while (true) {
            Thread.sleep(30000);
            session.getBasicRemote().sendText(JSON.toJSONString(investService.getDecision(email)));
        }

    }

    @OnOpen
    public void onOpen() {
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose() {
        System.out.println("Connection closed");
    }


}
