package com.codesquad.controlG.domain.chat.controller;

import com.codesquad.controlG.domain.chat.dto.MessageRequest;
import com.codesquad.controlG.domain.chat.dto.response.ChatSendMessageResponse;
import com.codesquad.controlG.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ChatService chatService;

    @MessageMapping("/message")
    public void sendMessage(MessageRequest messageRequest) {
        ChatSendMessageResponse chatSendMessageResponse = chatService.sendMessage(messageRequest);
        // 채널아이디 만들기
        simpMessageSendingOperations.convertAndSend("/sub/room/" + messageRequest.getChatRoomId(),
                chatSendMessageResponse);
    }
}
