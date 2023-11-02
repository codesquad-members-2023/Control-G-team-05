package com.codesquad.controlG.domain.chat.service;

import com.codesquad.controlG.domain.chat.entity.ChatMember;
import com.codesquad.controlG.domain.chat.entity.ChatRoom;
import com.codesquad.controlG.domain.chat.repository.ChatMemberRepository;
import com.codesquad.controlG.domain.chat.repository.ChatMessageRepository;
import com.codesquad.controlG.domain.chat.repository.ChatRoomRepository;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.ChatException;
import com.codesquad.controlG.exception.errorcode.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatMemberService {

    private final ChatMemberRepository chatMemberRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public void saveChatMember(Long memberId, Long partnerId, Long chatRoomId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MemberException.MEMBER_NOT_FOUND));
        Member partner = memberRepository.findById(partnerId)
                .orElseThrow(() -> new CustomRuntimeException(MemberException.MEMBER_NOT_FOUND));
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new CustomRuntimeException(
                ChatException.INVALID_CHAT_ROOM_ID));

        ChatMember chatMember = ChatMember.of(chatRoom, member);
        ChatMember chatMemberPartner = ChatMember.of(chatRoom, partner);
        chatMemberRepository.save(chatMember);
        chatMemberRepository.save(chatMemberPartner);
    }

    @Transactional
    public void deleteChatRoom(Long chatRoomId, Long memberId) {
        ChatMember chatMember = chatMemberRepository.findByChatRoomIdAndMemberId(chatRoomId, memberId)
                .orElseThrow(() -> new CustomRuntimeException(ChatException.INVALID_CHAT_MEMBER));
        Long lastMessageIndex = chatMessageRepository.findMaxIdByChatRoomId(chatRoomId);
        chatMember.exit(lastMessageIndex);

        if (chatMemberRepository.countByChatRoomIdAndIsExit(chatRoomId, true) == 2) {
            chatMemberRepository.deleteByChatRoomId(chatRoomId);
            chatMessageRepository.deleteByChatRoomId(chatRoomId);
            chatRoomRepository.deleteById(chatRoomId);
        }
    }
}
