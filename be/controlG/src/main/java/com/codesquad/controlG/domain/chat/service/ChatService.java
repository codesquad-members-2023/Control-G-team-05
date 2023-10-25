package com.codesquad.controlG.domain.chat.service;

import com.codesquad.controlG.domain.chat.dto.MessageRequest;
import com.codesquad.controlG.domain.chat.dto.response.ChatSendMessageResponse;
import com.codesquad.controlG.domain.chat.entity.ChatMessage;
import com.codesquad.controlG.domain.chat.entity.ChatRoom;
import com.codesquad.controlG.domain.chat.repository.ChatMessageRepository;
import com.codesquad.controlG.domain.chat.repository.ChatRoomRepository;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.member.service.MemberService;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.ChatException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    private final ChatMessageRepository chatMessageRepository;

    private final RedisChatMemberService redisChatMemberService;

    private final MemberService memberService;

    @Transactional
    public ChatSendMessageResponse sendMessage(MessageRequest messageRequest) {
        // 1. chatRoomId 가 존재하는지 검증
        ChatRoom chatRoom = chatRoomRepository.findById(messageRequest.getChatRoomId())
                .orElseThrow(() -> new CustomRuntimeException(ChatException.INVALID_CHAT_ROOM_ID));
        /**
         *  2. size 는 현재 채팅방 접속자 수
         *     채팅 저장 -> 저장 할 때 ChatMessage 엔티티 위에서 가져온 채팅방 접속자 수를 통해 읽음 여부 확인 후 저장
         *     접속자 수가 2명이면 상대 메시지 읽음 처리(isRead = true), 1명이면 상대방 메지에 읽음처리 하지 않음(isRead = false)
         */
        Member sender = memberService.findMember(messageRequest.getSenderId());
        int size = redisChatMemberService.getRedisMemberSize(messageRequest.getChatRoomId());
        ChatMessage message = ChatMessage.of(messageRequest.getMessage(), chatRoom, sender, size);
        ChatMessage chatMessage = chatMessageRepository.save(message);
        if (chatMessage.isRead()) {
            chatMessageRepository.updateIsRead(chatRoom.getId());
        }

        return ChatSendMessageResponse.of(chatMessage, chatRoom);
    }
}
