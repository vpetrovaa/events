package com.solvd.laba.events.service.impl;

import com.solvd.laba.events.domain.Image;
import com.solvd.laba.events.service.MinioService;
import com.solvd.laba.events.service.property.MinioProperties;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioProperties minioProperties;
    private final MinioClient minioClient;

    @Override
    @SneakyThrows
    public void uploadImage(Long eventId, Image image) {
        InputStream image100 = generateThumbnail(image, 100);
        String newImage100Name = image.getFilename().replaceAll(image.getExtension(), "") + "100" + image.getExtension();
        createThumbnail(image100, newImage100Name);

        InputStream image400 = generateThumbnail(image, 400);
        String newImage400Name = image.getFilename().replaceAll(image.getExtension(), "") + "400" + image.getExtension();
        createThumbnail(image400, newImage400Name);

        createImage(image);
    }

    @Override
    @SneakyThrows
    public Image imageMapper(MultipartFile file) {
        Image image = new Image();
        image.setFilename(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setExtension(Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf(".")));
        image.setUrl(minioProperties.getUrl() + "/" + minioProperties.getBucketName() + "/" + file.getOriginalFilename());
        image.setFile(file);
        return image;
    }

    @SneakyThrows
    public void createImage(Image image){
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(image.getFilename())
                .stream(image.getFile().getInputStream(), image.getSize(), -1)
                .build());
    }

    @SneakyThrows
    public void createThumbnail(InputStream image, String filename){
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(filename)
                .stream(image, image.available(), -1)
                .build());
    }

    @SneakyThrows
    public InputStream generateThumbnail(Image image, int height) {
        BufferedImage currentImage = ImageIO.read(image.getFile().getInputStream());
        int currentHeight = currentImage.getHeight();
        float ratio = ((1F* height) / (1F * currentHeight));
        int newWidth = Math.round(currentImage.getWidth() * ratio);
        BufferedImage newImage = Scalr.resize(currentImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, newWidth, height);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(newImage, Objects.requireNonNull(image.getFile().getContentType()).split("/")[1], outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    @SneakyThrows
    public boolean isExistBucket() {
        return minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(minioProperties.getBucketName())
                .build());
    }

    @SneakyThrows
    public void createBucket() {
        minioClient.makeBucket(MakeBucketArgs.builder()
                .bucket(minioProperties.getBucketName())
                .build());
    }

}
