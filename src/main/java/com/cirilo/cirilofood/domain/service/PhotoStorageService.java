package com.cirilo.cirilofood.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {

    void upload(Photo photo);

    void remove(String fileName);

    RecoveredPhoto find(String fileName);

    default String generateFileName(String originFileName) {
        return UUID.randomUUID() + "_" + originFileName;
    }

    default void replace(String existingFile, Photo newPhoto) {
        this.upload(newPhoto);

        if (existingFile != null) {
            this.remove(existingFile);
        }
    }

    @Builder
    @Getter
    class Photo {

        private String fileName;

        private String contentType;

        private Long contentLength;

        private InputStream inputStream;

    }

    @Builder
    @Getter
    class RecoveredPhoto {

        private InputStream inputStream;

        private String url;

        public boolean hasUrl() {
            return url != null;
        }

        public boolean hasInputStream() {
            return inputStream != null;
        }

    }

}
