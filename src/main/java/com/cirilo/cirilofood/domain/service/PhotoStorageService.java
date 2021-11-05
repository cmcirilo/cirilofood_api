package com.cirilo.cirilofood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface PhotoStorageService {

    void upload(Photo photo);

    default String generateFileName(String originFileName) {
        return UUID.randomUUID() + "_" + originFileName;
    }

    @Builder
    @Getter
    class Photo {

        private String fileName;

        private InputStream inputStream;

    }

}
