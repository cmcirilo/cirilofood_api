package com.cirilo.cirilofood.infrastructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cirilo.cirilofood.core.storage.StorageProperties;
import com.cirilo.cirilofood.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3PhotoStorageService implements PhotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override public void upload(Photo photo) {
        try {
            String filePath = getPathFile(photo.getFileName());

            var objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(photo.getContentType());
            objectMetaData.setContentLength(photo.getContentLength());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    filePath,
                    photo.getInputStream(),
                    objectMetaData)
                    .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);

        } catch (Exception e) {
            throw new StorageException("Its not possible send file to S3 Amazon", e);
        }
    }

    @Override public void remove(String fileName) {

    }

    @Override public InputStream find(String fileName) {
        return null;
    }

    private String getPathFile(String fileName) {
        return String.format("%s/%s", storageProperties.getS3().getPhotosFolder(), fileName);
    }
}
