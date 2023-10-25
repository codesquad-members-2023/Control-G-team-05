package com.codesquad.controlG.interceptor;

import static com.codesquad.controlG.filter.util.RequestParser.extractAccessTokenFromAccessor;

import com.codesquad.controlG.domain.auth.jwt.JwtProvider;
import com.codesquad.controlG.domain.chat.service.RedisChatMemberService;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StompInterceptor implements ChannelInterceptor {

    private final JwtProvider jwtProvider;
    private final RedisChatMemberService redisChatMemberService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        log.info("StompAccessor = {}", accessor);
        handleMessage(accessor.getCommand(), accessor);
        return message;
    }

    private void handleMessage(StompCommand stompCommand, StompHeaderAccessor accessor) {
        switch (stompCommand) {
            case CONNECT:
                log.info("CONNECT !!");
                Long memberId = validateAccessToken(accessor);
                String sessionId = accessor.getSessionId();
                log.info("세션아이디 = {}", sessionId);
                connectToChatRoom(accessor, memberId, sessionId);
                break;
            case SUBSCRIBE:
                log.info("SUBSCRIBE !!");
                break;
            case SEND:
                break;
            case DISCONNECT:
                log.info("DISCONNECT !!");
                log.info("디스커넥트세션아이디 = {}", accessor.getSessionId());
                redisChatMemberService.deleteChatMember(accessor.getSessionId());
                break;
        }
    }

    private void connectToChatRoom(StompHeaderAccessor accessor, Long memberId, String sessionId) {
        // 채팅방 번호를 가져온다.
        Long chatRoomId = getChatRoomId(accessor);

        //채팅방 입장 처리 -> Redis에 입장 내역 저장
        redisChatMemberService.connectChatRoom(chatRoomId, memberId, sessionId);
    }

    private Long getChatRoomId(StompHeaderAccessor accessor) {
        return Long.valueOf(accessor.getFirstNativeHeader("chatRoomId"));
    }

    private Long validateAccessToken(StompHeaderAccessor accessor) {
        try {
            String token = extractAccessTokenFromAccessor(accessor);
            log.info("token : {}", token);
            return jwtProvider.getClaims(token).get("memberId", Long.class);
        } catch (RuntimeException e) {
            throw new CustomRuntimeException(JwtException.from(e));
        }
    }

}
