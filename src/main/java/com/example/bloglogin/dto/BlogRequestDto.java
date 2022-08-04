package com.example.bloglogin.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;
}
