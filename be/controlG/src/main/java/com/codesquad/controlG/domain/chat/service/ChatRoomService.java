package com.codesquad.controlG.domain.chat.service;

import com.codesquad.controlG.domain.chat.entity.ChatRoom;
import com.codesquad.controlG.domain.chat.repository.ChatRoomRepository;
import com.codesquad.controlG.domain.group.entity.Group;
import com.codesquad.controlG.domain.group.repository.GroupRepository;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.ChatException;
import com.codesquad.controlG.exception.errorcode.GroupException;
import com.codesquad.controlG.exception.errorcode.MemberException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;
    private final ChatMemberService chatMemberService;
    private final ChatService chatService;

    public Long createChatRoom(Long groupId, Long memberId, Long partnerId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomRuntimeException(GroupException.NOT_FOUND));
        Member member1 = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MemberException.MEMBER_NOT_FOUND));
        Member member2 = memberRepository.findById(partnerId)
                .orElseThrow(() -> new CustomRuntimeException(MemberException.MEMBER_NOT_FOUND));

        ChatRoom chatRoom = ChatRoom.of(group, member1, member2);
        Long chatRoomId = chatRoomRepository.save(chatRoom).getId();
        chatMemberService.saveChatMember(memberId, partnerId, chatRoomId);
        return chatRoomId;
    }

    public List<Long> findMemberChatRoom(Long groupId, Long memberId) {
        return chatRoomRepository.findMemberChatroom(groupId, memberId);
    }

    public Long createMatchingChatRoom(Long groupId, Long memberId, Long partnerId) {
        validateMatching(memberId, partnerId);
        return createChatRoom(groupId, memberId, partnerId);
    }

    private void validateMatching(Long memberId, Long partnerId) {
        if (!chatService.isMatched(memberId, partnerId)) {
            throw new CustomRuntimeException(ChatException.MATCHING_NOT_FOUND);
        }
    }

}
