package com.cirilo.cirilofood.infrastructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.cirilo.cirilofood.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3PhotoStorageService implements PhotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Override public void upload(Photo photo) {

    }

    @Override public void remove(String fileName) {

    }

    @Override public InputStream find(String fileName) {
        return null;
    }
}
