package com.codesquad.controlG.domain.member.controller;

import com.codesquad.controlG.domain.image.ImageService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class test {
    private final ImageService imageService;

    @PostMapping("/images")
    public ResponseEntity<Map<String, String>> uploadMemberImage(
            @RequestPart(value = "file") MultipartFile multipartFile) {
        String imageUrl = imageService.uploadImage(multipartFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("imgUrl", imageUrl));
    }
}
