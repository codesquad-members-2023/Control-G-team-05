package com.codesquad.controlG.domain.chat.service;

import com.codesquad.controlG.domain.chat.dto.MessageRequest;
import com.codesquad.controlG.domain.chat.dto.response.ChatInfoMessages;
import com.codesquad.controlG.domain.chat.dto.response.ChatInfoPartner;
import com.codesquad.controlG.domain.chat.dto.response.ChatInfoResponse;
import com.codesquad.controlG.domain.chat.dto.response.ChatListResponse;
import com.codesquad.controlG.domain.chat.dto.response.ChatSendMessageResponse;
import com.codesquad.controlG.domain.chat.entity.ChatMessage;
import com.codesquad.controlG.domain.chat.entity.ChatRoom;
import com.codesquad.controlG.domain.chat.repository.ChatMessageRepository;
import com.codesquad.controlG.domain.chat.repository.ChatRoomRepository;
import com.codesquad.controlG.domain.like.repository.LikeRepository;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.member.service.MemberService;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.ChatException;
import java.util.List;
import java.util.Map;
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

    private final NotificationService notificationService;

    private final LikeRepository likeRepository;


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
        // 3. SSE 재요청 알림 보내기
        Long receiverId = chatRoom.partnerId(sender.getId());

        notificationService.refreshChatRoomList(receiverId);

        return ChatSendMessageResponse.of(chatMessage, chatRoom);
    }

    public List<ChatListResponse> getChatList(Long groupId, Long memberId) {
        List<ChatListResponse> chatList = chatRoomRepository.getChatList(groupId, memberId);
        Map<Long, Long> countNewMessage = chatRoomRepository.countNewMessage(groupId, memberId);

        List<Long> likerIds = likeRepository.findLikerIds(memberId);
        List<Member> likedMembers = likeRepository.findLikedMembers(memberId);
        List<Long> matchedMembers = getMatched(likedMembers, likerIds);

        chatList.forEach(chatRoom -> {
            Long count = countNewMessage.getOrDefault(chatRoom.getChatRoomId(), 0L);
            chatRoom.assignNewMessageCount(count);
            if (!matchedMembers.contains(chatRoom.getPartner().getId())) {
                chatRoom.getPartner().hideInfo();
            }
        });
        return chatList;
    }

    @Transactional
    public ChatInfoResponse getChatInfo(Long chatRoomId, Long memberId) {
        chatMessageRepository.updateIsRead(chatRoomId);
        ChatInfoPartner chatInfoPartner = chatMessageRepository.getChatInfoPartner(chatRoomId, memberId);
        List<ChatInfoMessages> chatMessages = chatMessageRepository.getChatMessages(chatRoomId, memberId);

        boolean isLiked = likeRepository.existsLike(memberId, chatInfoPartner.getId());
        chatInfoPartner.setIsLiked(isLiked);

        validateMatch(memberId, chatInfoPartner);
        return ChatInfoResponse.of(chatInfoPartner, chatMessages);
    }

    private void validateMatch(Long memberId, ChatInfoPartner chatInfoPartner) {
        if (!isMatched(memberId, chatInfoPartner.getId())) {
            chatInfoPartner.hideName();
        }
    }

    public boolean isMatched(Long memberId, Long partnerId) {
        return likeRepository.existsLike(memberId, partnerId) && likeRepository.existsLike(partnerId, memberId);
    }

    private List<Long> getMatched(List<Member> memberList, List<Long> likerIds) {
        return memberList.stream()
                .map(Member::getId)
                .filter(likerIds::contains)
                .toList();
    }
}
