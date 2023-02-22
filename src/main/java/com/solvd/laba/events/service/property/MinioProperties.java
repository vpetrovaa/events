package com.solvd.laba.events.service.property;

import io.minio.MinioClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "minio.properties")
public class MinioProperties {

    private final String url;
    private final String accessKey;
    private final String secretKey;
    private final String bucketName;
    private final Integer extension100 = 100;
    private final Integer extension400 = 400;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(getUrl())
                .credentials(getAccessKey(), getSecretKey())
                .build();
    }

}
