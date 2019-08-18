package com.example.study5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Board {
    private int idx;
    private String title;
    private String content;
    private String url;
}
