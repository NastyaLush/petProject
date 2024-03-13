package com.example.vkk.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebsocketEchoHandler extends TextWebSocketHandler {
    private final WebSocketSession clientSession;

    public WebsocketEchoHandler(WebSocketSession clientSession) {
        this.clientSession = clientSession;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if (clientSession != null && clientSession.isOpen()) {
            clientSession.sendMessage(new TextMessage(message.getPayload()));
        }
    }
}
