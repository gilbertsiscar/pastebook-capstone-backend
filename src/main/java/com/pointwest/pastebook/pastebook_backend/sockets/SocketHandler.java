package com.pointwest.pastebook.pastebook_backend.sockets;

//import com.google.gson.Gson;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.BinaryMessage;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//@Component
//public class SocketHandler extends TextWebSocketHandler {
//    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
//    int messagecount = 0;
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message)
//            throws InterruptedException, IOException {
//
//        // parse message
//        //Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);
//
//         //send message to all sessions
//         for (WebSocketSession webSocketSession : sessions) {
//             Map value = new Gson().fromJson(message.getPayload(), Map.class);
//             webSocketSession.sendMessage(new TextMessage(message.getPayload()));
//         }
//
////        if(value.keySet().contains("subscribe")) {
////            // start the service with the subscribe name
////        } else if(value.keySet().contains("unsubscribe")) {
////            // stop the service with the unsubscribe name or remove the session that unsubscribed
////            // be careful not to stop the service if there are still sessions available
////        } else {
////            // do something with the sent object
////
////            messagecount++;
////            // create object myMessageNumberObject = {type: 'messageNumber', messagecount: messagecount}
////            // session.sendMessage(new TextMessage(myMessageNumberObject))
////
////            // emit message with type='message'
////            System.out.println(message.getPayload());
////            //{"name":"test","message":"test messaage","type":"message"}
////            session.sendMessage(new TextMessage(message.getPayload()));
////        }
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        // the messages will be broadcasted to all users.
//        //System.out.println("Connection Establised");
//        //System.out.println(session);
//        //System.out.println(session.getId());
//        if(!sessions.contains(session)){
//            sessions.add(session);
//        }
//
//
//        System.out.println(sessions.size());
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
//        // do something on connection closed
//        sessions.remove(session);
//    }
//
//    @Override
//    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
//        // handle binary message
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) {
//        // hanedle transport error
//    }
//}