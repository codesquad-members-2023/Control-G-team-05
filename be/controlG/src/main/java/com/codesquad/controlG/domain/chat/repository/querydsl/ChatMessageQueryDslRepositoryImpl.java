package com.codesquad.controlG.domain.chat.repository.querydsl;

import static com.codesquad.controlG.domain.chat.entity.QChatMember.chatMember;
import static com.codesquad.controlG.domain.chat.entity.QChatMessage.chatMessage;
import static com.codesquad.controlG.domain.chat.entity.QChatRoom.chatRoom;
import static com.codesquad.controlG.domain.member.entity.QMember.member;

import com.codesquad.controlG.domain.chat.dto.response.ChatInfoMessages;
import com.codesquad.controlG.domain.chat.dto.response.ChatInfoPartner;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatMessageQueryDslRepositoryImpl implements ChatMessageQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public void updateIsRead(Long chatRoomId, Long partnerId) {
        queryFactory.update(chatMessage)
                .set(chatMessage.isRead, true)
                .where(isUnread(),
                        equalChatRoomId(chatRoomId),
                        chatMessage.id.gt(0),
                        chatMessage.sender.id.eq(partnerId))
                .execute();
    }

    @Override
    public List<ChatInfoMessages> getChatMessages(Long chatRoomId, Long memberId) {
        return queryFactory.select(Projections.fields(ChatInfoMessages.class,
                        chatMessage.id,
                        chatMessage.sender.id.as("senderId"),
                        chatMessage.sentAt,
                        chatMessage.message,
                        chatMessage.isRead))
                .from(chatMessage)
                .join(chatMember)
                .on(chatMember.chatRoom.eq(chatMessage.chatRoom))
                .where(
                        chatMessage.chatRoom.id.eq(chatRoomId),
                        chatMessage.id.gt(chatMember.lastMessageId),
                        chatMember.member.id.eq(memberId)
                )
                .fetch();
    }

    @Override
    public ChatInfoPartner getChatInfoPartner(Long chatRoomId, Long memberId) {
        return queryFactory.select(Projections.fields(ChatInfoPartner.class,
                        chatRoom.group.name.as("groupName"),
                        member.id,
                        member.name,
                        member.nickname,
                        member.gender))
                .from(member)
                .join(chatRoom)
                .on(chatRoom.id.eq(chatRoomId))
                .where(
                        member.id.eq(getPartner(memberId))
                )
                .fetchOne();
    }

    private NumberExpression<Long> getPartner(Long memberId) {
        return new CaseBuilder()
                .when(chatRoom.member1.id.eq(memberId))
                .then(chatRoom.member2.id)
                .otherwise(chatRoom.member1.id);
    }

    private BooleanExpression equalChatRoomId(Long chatRoomId) {
        return chatMessage.chatRoom.id.eq(chatRoomId);
    }

    private BooleanExpression isUnread() {
        return chatMessage.isRead.eq(false);
    }
}
