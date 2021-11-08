package com.cirilo.cirilofood.infrastructure.service.storage;

import com.cirilo.cirilofood.domain.service.PhotoStorageService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3PhotoStorageService implements PhotoStorageService {

    @Override public void upload(Photo photo) {

    }

    @Override public void remove(String fileName) {

    }

    @Override public InputStream find(String fileName) {
        return null;
    }
}
