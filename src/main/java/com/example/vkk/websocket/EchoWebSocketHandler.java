package com.example.vkk.websocket;

import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoWebSocketHandler extends TextWebSocketHandler {
//    private final EchoService echoService;
//
//    public EchoWebSocketHandler(EchoService echoService) {
//        this.echoService = echoService;
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) {
//        logger.debug("Opened new session in instance " + this);
//    }
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String echoMessage = this.echoService.getMessage(message.getPayload());
//        logger.debug(echoMessage);
//        session.sendMessage(new TextMessage(echoMessage));
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        session.close(CloseStatus.SERVER_ERROR);
//    }

}
