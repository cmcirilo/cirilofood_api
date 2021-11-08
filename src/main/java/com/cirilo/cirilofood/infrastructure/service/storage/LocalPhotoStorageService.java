package com.cirilo.cirilofood.infrastructure.service.storage;

import com.cirilo.cirilofood.core.storage.StorageProperties;
import com.cirilo.cirilofood.domain.service.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

public class LocalPhotoStorageService implements PhotoStorageService {

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void upload(Photo photo) {
        try {
            Path pathFile = getPathFile(photo.getFileName());

            FileCopyUtils.copy(photo.getInputStream(),
                    Files.newOutputStream(pathFile));
        } catch (Exception e) {
            throw new StorageException("Its not possible upload file", e);
        }
    }

    @Override
    public void remove(String fileName) {
        try {
            Path pathFile = getPathFile(fileName);

            Files.deleteIfExists(pathFile);
        } catch (Exception e) {
            throw new StorageException("Its not possible remove file", e);
        }
    }

    @Override
    public RecoveredPhoto find(String fileName) {
        try {
            Path pathFile = getPathFile(fileName);

            RecoveredPhoto recoveredPhoto = RecoveredPhoto.builder()
                    .inputStream(Files.newInputStream(pathFile))
                    .build();

            return recoveredPhoto;
        } catch (Exception e) {
            throw new StorageException("Its not possible find file", e);
        }
    }

    private Path getPathFile(String fileName) {
        return storageProperties.getLocal().getPhotosFolder().resolve(Path.of(fileName));
    }
}
