package com.codesquad.controlG.domain.image;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class S3Uploader {

    private static final String PUBLIC_IMAGE_DIR = "public/";
    private static final int FILE_NAME_INDEX = 5;   // s3에 저장된 fileName 가져오기 위한 인덱스

    private final AmazonS3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImageFile(ImageFile imageFile) {
        final String fileName = PUBLIC_IMAGE_DIR + imageFile.getSavedFileName();
        putS3(imageFile, fileName);
        return getObjectUrl(fileName);
    }

    private String getObjectUrl(final String fileName) {
        return URLDecoder.decode(s3Client.getUrl(bucket, fileName).toString(), StandardCharsets.UTF_8);
    }

    private void putS3(ImageFile imageFile, final String fileName) {
        s3Client.putObject(createPutObjRequest(imageFile, fileName));
    }

    private PutObjectRequest createPutObjRequest(ImageFile imageFile, String fileName) {
        return new PutObjectRequest(
                bucket,
                fileName,
                imageFile.getInputStream(),
                createObjectMetaData(imageFile)
        ).withCannedAcl(CannedAccessControlList.PublicRead);
    }

    private ObjectMetadata createObjectMetaData(ImageFile imageFile) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(imageFile.getContentType());
        metadata.setContentLength(imageFile.getFileSize());
        return metadata;
    }

    public void deleteImage(String imageUrl) {
        String fileName = PUBLIC_IMAGE_DIR + imageUrl.split("/")[FILE_NAME_INDEX];
        s3Client.deleteObject(bucket, fileName);
    }
}