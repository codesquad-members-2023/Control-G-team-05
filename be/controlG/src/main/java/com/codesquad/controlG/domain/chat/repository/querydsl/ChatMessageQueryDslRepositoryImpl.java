package com.codesquad.controlG.domain.chat.repository.querydsl;

import static com.codesquad.controlG.domain.chat.entity.QChatMessage.chatMessage;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatMessageQueryDslRepositoryImpl implements ChatMessageQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public void updateIsRead(Long chatRoomId) {
        queryFactory.update(chatMessage)
                .set(chatMessage.isRead, true)
                .where(isUnread()
                        .and(equalChatRoomId(chatRoomId)))
                .execute();
    }

    private BooleanExpression equalChatRoomId(Long chatRoomId) {
        return chatMessage.chatRoom.id.eq(chatRoomId);
    }

    private BooleanExpression isUnread() {
        return chatMessage.isRead.eq(false);
    }
}
