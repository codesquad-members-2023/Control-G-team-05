package com.codesquad.controlG.domain.chat.controller;

import com.codesquad.controlG.domain.chat.service.NotificationService;
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

    @GetMapping(value = "/connect/{memberId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@PathVariable Long memberId) {
        SseEmitter emitter = new SseEmitter(60 * 1000L);
        notificationService.add(emitter, memberId);
        return ResponseEntity.ok(emitter);
    }
}
