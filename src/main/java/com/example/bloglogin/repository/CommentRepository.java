package com.example.bloglogin.repository;

import com.example.bloglogin.entity.Blog;
import com.example.bloglogin.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
