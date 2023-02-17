package com.solvd.laba.events.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Data
public class Image {

    private String url;
    private String filename;
    private String extension;
    private Long size;
    private MultipartFile file;

}
