package com.cirilo.cirilofood.core.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("cirilofood.storage")
public class StorageProperties {

    private Local local = new Local();

    @Getter
    @Setter
    public class Local {

        private Path photosFolder;

    }

    @Getter
    @Setter
    public class S3 {

        private String accessKeyId;

        private String accessKeySecret;

        private String bucket;

        private String region;

        private String photosFolder;

    }
}
