package com.cirilo.cirilofood.infrastructure.service.storage;

import com.cirilo.cirilofood.core.storage.StorageProperties;
import com.cirilo.cirilofood.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalPhotoStorageService implements PhotoStorageService {

    @Autowired
    private StorageProperties storageProperties;

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
        try {
            Path filePath = getFilePath(fileName);

            return Files.newInputStream(filePath);
        } catch (Exception e) {
            throw new StorageException("Its not possible find file", e);
        }
    }

    private Path getFilePath(String fileName) {
        return storageProperties.getLocal().getPhotosFolder().resolve(Path.of(fileName));
    }
}
