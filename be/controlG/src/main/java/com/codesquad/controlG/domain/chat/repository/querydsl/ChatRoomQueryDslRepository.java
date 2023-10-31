package com.codesquad.controlG.domain.chat.repository.querydsl;

import com.codesquad.controlG.domain.chat.dto.response.ChatListResponse;
import java.util.List;
import java.util.Map;

public interface ChatRoomQueryDslRepository {

    List<Long> findMemberChatroom(Long groupId, Long memberId);

    List<ChatListResponse> getChatList(Long groupId, Long memberId);

    Map<Long, Long> countNewMessage(Long groupId, Long memberId);
}
