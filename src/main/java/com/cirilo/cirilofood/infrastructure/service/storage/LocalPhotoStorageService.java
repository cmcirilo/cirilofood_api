package com.cirilo.cirilofood.infrastructure.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.cirilo.cirilofood.domain.service.PhotoStorageService;

@Service
public class LocalPhotoStorageService implements PhotoStorageService {

    @Value("${cirilofood.storage.local.photos-folder}")
    private Path photosFolder;

    @Override
    public void upload(Photo photo) {
        try {
            Path filePath = getFilePath(photo.getFileName());

            FileCopyUtils.copy(photo.getInputStream(),
                    Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("Its not possible upload file", e);
        }
    }

    private Path getFilePath(String fileName) {
        return photosFolder.resolve(Path.of(fileName));
    }
}
