package com.cirilo.cirilofood.domain.service;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

public interface PhotoStorageService {

    void upload(Photo photo);

    @Builder
    @Getter
    class Photo {

        private String fileName;

        private InputStream inputStream;

    }

}
