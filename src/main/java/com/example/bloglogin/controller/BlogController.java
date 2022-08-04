package com.example.bloglogin.controller;

import com.example.bloglogin.dto.BlogRequestDto;
import com.example.bloglogin.dto.ResponseDto;
import com.example.bloglogin.service.BlogService;
import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/api/blog")
    public ResponseDto<?> createBlog(@RequestBody BlogRequestDto requestDto) {
        return blogService.createBlog(requestDto);
    }

    @GetMapping("/api/blog/{id}")
    public ResponseDto<?> getBlog(@PathVariable Long id) {
        return blogService.getBlog(id);
    }

    @GetMapping("/api/blog")
    public ResponseDto<?> getAllBlogs() {return blogService.getAllBlog();
    }

    @PutMapping("/api/blog/{id}")
    public ResponseDto<?> updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto blogRequestDto) {
        return blogService.updateBlog(id, blogRequestDto);
    }

    @DeleteMapping("/api/blog/{id}")
    public ResponseDto<?> deleteBlog(@PathVariable Long id) {
        return blogService.deleteBlog(id);
    }

    @PostMapping("/api/blog/{id}")
    public ResponseDto<?> validateAuthorByPassword(@PathVariable Long id, @RequestBody String password) {
        return blogService.validateAuthorByPassword(id, password);
    }
}
