package com.codesquad.controlG.domain.image;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.codesquad.controlG.annotation.ServiceTest;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@DisplayName("단위 테스트 - 이미지")
@ServiceTest
class ImageServiceTest {

    @Mock
    private S3Uploader s3Uploader;

    @InjectMocks
    private ImageService imageService;

    @DisplayName("이미지 파일이 주어지면 이미지 업로드에 성공한다.")
    @Test
    void givenMultipartFile_thenSuccess() {
        // given
        var mockMultipartFile = createMockMultipartFile("test.png", MediaType.IMAGE_PNG_VALUE);

        given(s3Uploader.uploadImageFile(any(ImageFile.class))).willReturn("url");

        // when & then
        assertThatCode(() -> imageService.uploadImage(mockMultipartFile)).doesNotThrowAnyException();
    }

    private MockMultipartFile createMockMultipartFile(String fileName, String extension) {
        return new MockMultipartFile(
                "test-image",
                fileName,
                extension,
                "imageBytes".getBytes(StandardCharsets.UTF_8)
        );
    }
}
