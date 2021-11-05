package com.cirilo.cirilofood.infrastructure.service.storage;

import java.io.InputStream;
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

    @Override
    public void remove(String fileName) {
        try {
            Path filePath = getFilePath(fileName);

            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException("Its not possible remove file", e);
        }
    }

    @Override
    public InputStream find(String fileName) {
        try{
            Path filePath = getFilePath(fileName);

            return Files.newInputStream(filePath);
        }catch (Exception e) {
            throw new StorageException("Its not possible find file", e);
        }
    }

    private Path getFilePath(String fileName) {
        return photosFolder.resolve(Path.of(fileName));
    }
}
