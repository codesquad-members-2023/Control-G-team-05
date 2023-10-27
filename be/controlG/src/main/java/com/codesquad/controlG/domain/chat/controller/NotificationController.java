package com.codesquad.controlG.domain.chat.controller;

import com.codesquad.controlG.domain.chat.service.NotificationService;
import com.codesquad.controlG.domain.chat.service.RedisChatQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final RedisChatQueueService redisChatQueueService;

    @GetMapping(value = "/connect/{memberId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@PathVariable Long memberId) {
        SseEmitter emitter = new SseEmitter(20 * 1000L);
        notificationService.add(emitter, memberId);
        return ResponseEntity.ok(emitter);
    }

    @GetMapping(value = "/connect/{groupId}/{memberId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connectQueue(@PathVariable Long groupId, @PathVariable Long memberId) {
        SseEmitter emitter = new SseEmitter(20 * 1000L);
        notificationService.addQueue(emitter, groupId, memberId);
        return ResponseEntity.ok(emitter);
    }
}
