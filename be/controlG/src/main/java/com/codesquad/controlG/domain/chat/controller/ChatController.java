package com.codesquad.controlG.domain.chat.controller;

import com.codesquad.controlG.domain.auth.Auth;
import com.codesquad.controlG.domain.chat.dto.MessageRequest;
import com.codesquad.controlG.domain.chat.dto.response.ChatInfoResponse;
import com.codesquad.controlG.domain.chat.dto.response.ChatListResponse;
import com.codesquad.controlG.domain.chat.dto.response.ChatRandomResult;
import com.codesquad.controlG.domain.chat.dto.response.ChatSendMessageResponse;
import com.codesquad.controlG.domain.chat.service.ChatMemberService;
import com.codesquad.controlG.domain.chat.service.ChatRoomService;
import com.codesquad.controlG.domain.chat.service.ChatService;
import com.codesquad.controlG.domain.chat.service.RedisChatQueueService;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final Map<String, DeferredResult<ResponseEntity<Map<String, Object>>>> que = new ConcurrentHashMap<>();

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ChatMemberService chatMemberService;
    private final ChatService chatService;
    private final ChatRoomService chatRoomService;
    private final RedisChatQueueService redisChatQueueService;

    @MessageMapping("/message")
    public void sendMessage(MessageRequest messageRequest) {
        ChatSendMessageResponse chatSendMessageResponse = chatService.sendMessage(messageRequest);
        // 채널아이디 만들기
        simpMessageSendingOperations.convertAndSend("/sub/room/" + messageRequest.getChatRoomId(),
                chatSendMessageResponse);
    }

    @PostMapping("/api/chats/random/{groupId}")
    public DeferredResult<ResponseEntity<Map<String, Object>>> startMatching(@PathVariable Long groupId,
                                                                             @Auth Long memberId) {
        // 매칭 상대 찾지 못하고 타임아웃되면, 이 값이 반환됨
        DeferredResult<ResponseEntity<Map<String, Object>>> deferredResult =
                new DeferredResult<>(10 * 1000L, ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body(Map.of("message", "상대를 찾을 수 없습니다.")));

        // redis 대기열에 저장 & Map 에 저장
        String key = redisChatQueueService.saveQueue(groupId, memberId);
        que.put(key, deferredResult);

        // service 에서 매칭 상대 찾기
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ChatRandomResult randomResult = redisChatQueueService.matching(groupId, memberId);

        // 본인 deferredResult.setResult
        if (!randomResult.getChatRoomId().equals(0L)) {
            deferredResult.setResult(
                    ResponseEntity.status(HttpStatus.CREATED).body(Map.of("chatRoomId", randomResult.getChatRoomId())));

            // 매칭 상대방 deferredResult.setResult
            String partnerKey = groupId + "_" + randomResult.getPartnerId();
            DeferredResult<ResponseEntity<Map<String, Object>>> partnerDeferredResult = que.get(partnerKey);
            partnerDeferredResult.setResult(
                    ResponseEntity.status(HttpStatus.CREATED).body(Map.of("chatRoomId", randomResult.getChatRoomId())));
        }
        // redis 대기열 삭제 & Map 에서 삭제
        String partnerKey = groupId + "_" + randomResult.getPartnerId();
        deferredResult.onCompletion(() -> {
            redisChatQueueService.removeQueue(key);
            redisChatQueueService.removeQueue(partnerKey);
            que.remove(key);
            que.remove(partnerKey);
        });

        return deferredResult;
    }

    @GetMapping("/api/chats")
    public ResponseEntity<List<ChatListResponse>> getChatList(@RequestParam(required = false) Long groupId,
                                                              @Auth Long memberId) {
        List<ChatListResponse> chatListResponses = chatService.getChatList(groupId, memberId);
        return ResponseEntity.ok().body(chatListResponses);
    }

    @GetMapping("/api/chats/{chatRoomId}")
    public ResponseEntity<ChatInfoResponse> getChatInfo(@PathVariable Long chatRoomId,
                                                        @Auth Long memberId) {
        ChatInfoResponse chatInfoResponses = chatService.getChatInfo(chatRoomId, memberId);
        return ResponseEntity.ok().body(chatInfoResponses);
    }

    @DeleteMapping("/api/chats/{chatRoomId}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable Long chatRoomId,
                                               @Auth Long memberId) {
        chatMemberService.deleteChatRoom(chatRoomId, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/chats/{groupId}/{partnerId}")
    public ResponseEntity<Map<String, Long>> createChatRoom(@PathVariable Long groupId,
                                                            @PathVariable Long partnerId,
                                                            @Auth Long memberId) {
        Long chatRoomId = chatRoomService.createMatchingChatRoom(groupId, memberId, partnerId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("chatRoomId", chatRoomId));
    }
}
