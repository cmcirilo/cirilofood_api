package com.cirilo.cirilofood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface PhotoStorageService {

    void upload(Photo photo);

    void remove(String fileName);

    default String generateFileName(String originFileName) {
        return UUID.randomUUID() + "_" + originFileName;
    }

    default void replace(String existingFile, Photo newPhoto){
        this.upload(newPhoto);

        if (existingFile != null){
            this.remove(existingFile);
        }
    };

    @Builder
    @Getter
    class Photo {

        private String fileName;

        private InputStream inputStream;

    }

}
