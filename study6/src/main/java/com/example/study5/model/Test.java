package com.example.study5.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Test {
    private MultipartFile profile;
    private String url;
    private String title;
}
