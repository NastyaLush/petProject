package com.example.vkk.websocket;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
@Log4j2
public class ClientWebSocketHandler extends TextWebSocketHandler {
    private final static String ECHO_SERVER_URI = "wss://echo.websocket.org/";
    private final Map<WebSocketSession, WebSocketSession> sessions = new HashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws ExecutionException, InterruptedException {
        log.info("new connection established " + session);
        WebSocketClient client = new StandardWebSocketClient();
        sessions.put(session, client.execute(new WebsocketEchoHandler(session), ECHO_SERVER_URI).get());

    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        WebSocketSession current = sessions.get(session);
        if (current != null && current.isOpen()) {
            current.sendMessage(new TextMessage(message.getPayload()));
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("error occurred " + session);
        WebSocketSession webSocketSession = sessions.get(session);
        if (webSocketSession != null) {
            webSocketSession.close();
        }
        sessions.remove(session);
        session.close();
    }

}
