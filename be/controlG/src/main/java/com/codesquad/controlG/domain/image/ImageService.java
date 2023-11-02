package com.codesquad.controlG.domain.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ImageService {

    private final S3Uploader s3Uploader;

    @Transactional
    public String uploadImage(MultipartFile image) {
        ImageFile imageFile = ImageFile.from(image);
        return s3Uploader.uploadImageFile(imageFile);
    }

    @Transactional
    public void deleteImage(String imageUrl) {
        s3Uploader.deleteImage(imageUrl);
    }
}
