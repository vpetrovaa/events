package com.solvd.laba.events.service;

import com.solvd.laba.events.domain.Image;
import org.springframework.web.multipart.MultipartFile;

public interface MinioService {

    void uploadImage(Long eventId, Image image);

    Image imageMapper(MultipartFile file);

}
