package com.codesquad.controlG.domain.chat.service;

import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.ChatException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Service
public class NotificationService {

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, SseEmitter> chatQueueEmitters = new ConcurrentHashMap<>();

    public SseEmitter add(SseEmitter emitter, Long memberId) {
        this.emitters.put(memberId, emitter);

        //	더미 데이터를 보내는 이유는 Emitter 생성후 만료시간까지 아무런 데이터를 보내지 않으면 503 Error 발생가능성이 있다.
        try {
            emitter.send(SseEmitter.event()
                    .name("connect") //todo 이벤트 이름 지정
                    .data("connected!"));// 503 에러 방지를 위한 더미 데이터
        } catch (IOException e) {
            log.info("emitter send error");
        }
        setupEmitterCallbacks(emitter, memberId);
        return emitter;
    }

    private void setupEmitterCallbacks(SseEmitter emitter, Long memberId) {
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitter.complete();
        });

        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            emitters.remove(memberId);
        });
    }

    public void refreshChatRoomList(Long receiverId) {
        SseEmitter emitter = emitters.get(receiverId);

        if (emitter == null) {
            return;
        }

        try {
            emitter.send(SseEmitter.event()
                    .name("refreshChatRoomList")
                    .data("refresh"));
        } catch (IOException e) {
            log.info("emitter send error");
        }
    }

    public SseEmitter addQueue(SseEmitter emitter, Long groupId, Long memberId) {
        String key = groupId + "_" + memberId;
        this.chatQueueEmitters.put(key, emitter);
        //	더미 데이터를 보내는 이유는 Emitter 생성후 만료시간까지 아무런 데이터를 보내지 않으면 503 Error 발생가능성이 있다.
        try {
            emitter.send(SseEmitter.event()
                    .name("connectChatQueue") //todo 이벤트 이름 지정
                    .data("connected!")); // 503 에러 방지를 위한 더미 데이터
        } catch (IOException e) {
            log.info("emitter send error");
        }
        emitter.onTimeout(() -> {
            log.info("onTimeout callback - failed match partner");
            emitter.completeWithError(new CustomRuntimeException(ChatException.PARTNER_NOT_FOUND));
        });

        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            chatQueueEmitters.remove(key);
        });
        return emitter;
    }

    public void matchPartner(String key) {
        SseEmitter emitter = chatQueueEmitters.get(key);
        // redis에서 List<SseEmitter> 리스트 가져옴
        try {
            emitter.send(SseEmitter.event()
                    .name("성공")
                    .data("refresh"));
        } catch (IOException e) {
            log.info("emitter send error");
        }

    }
}
