package com.codesquad.controlG.domain.chat.service;

import com.codesquad.controlG.domain.chat.entity.ChatRoom;
import com.codesquad.controlG.domain.chat.repository.ChatRoomRepository;
import com.codesquad.controlG.domain.group.entity.Group;
import com.codesquad.controlG.domain.group.repository.GroupRepository;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.exception.CustomRuntimeException;
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

    public Long createChatRoom(Long groupId, Long memberId, Long partnerId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomRuntimeException(GroupException.NOT_FOUND));
        Member member1 = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MemberException.MEMBER_NOT_FOUND));
        Member member2 = memberRepository.findById(partnerId)
                .orElseThrow(() -> new CustomRuntimeException(MemberException.MEMBER_NOT_FOUND));

        ChatRoom chatRoom = ChatRoom.of(group, member1, member2);
        return chatRoomRepository.save(chatRoom).getId();
    }

    public List<Long> findMemberChatRoom(Long groupId, Long memberId) {
        return chatRoomRepository.findMemberChatroom(groupId, memberId);
    }
}
