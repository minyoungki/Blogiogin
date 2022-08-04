package com.example.bloglogin.service;

import com.example.bloglogin.dto.BlogRequestDto;
import com.example.bloglogin.dto.ResponseDto;
import com.example.bloglogin.entity.Blog;
import com.example.bloglogin.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor  //lombok에 final 생성자를 대신 만들어 준다.
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Transactional
    public ResponseDto<?> createBlog(BlogRequestDto requestDto) {
        // 데이터 타입 ResponseDto<?>으로 BlogRequestDto requestDto를 받는다.

        Blog blog = new Blog(requestDto);
        //새로운 requestDto이란 매개변수를 받는 인스턴스 Blog를 생성

        blogRepository.save(blog);
        //BlogRepository 에 Blog를 저장

        return ResponseDto.success(blog);
        //ResponseDto에 success에 Blog를 반환
    }

    @Transactional  //readOnly는 의도지 않게 데이터를 변경하는 것을 막주고, 속도를 향상시켜준다.
    public ResponseDto<?> getBlog(Long id) {
        // 데이터 타입 ResponseDto<?>으로 Long id를 받는다.

        Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isEmpty()) {
            return ResponseDto.fail("NULL_Blog_ID", "Blog id isn't exist");
        }
        // 만약 optionalBlog가 isEmpty() = 비어 있다면 ResponseDto에 fail에 값을 전달.
        return ResponseDto.success(optionalBlog.get());
        // 아니라면 ResponseDto에 success에 optionalBlog.get()을 전달
    }

    @Transactional
    public ResponseDto<?> getAllBlog() {
        return ResponseDto.success(blogRepository.findAllByOrderByModifiedAtDesc());
    }
    // 데이터 타입 ResponseDto<?>으로  getAllBlog()클래스 생성.
    // 호출되면 ResponseDto.success에 BlogRepository.findAllByOrderByModifiedAtDesc()를 보낸다.

    @Transactional
    public ResponseDto<Blog> updateBlog(Long id, BlogRequestDto requestDto) {
        // 데이터 타입 ResponseDto<?>으로 매개변수 Long id, BlogRequestDto requestDto를 받는다.

        Optional<Blog> optionalBlog = blogRepository.findById(id);
        //데이터타입 Optional<Blog> 인 optionalBlog에 BlogRepository에서 id를 찾아 너어 준다.

        if (optionalBlog.isEmpty()) {
            return ResponseDto.fail("NULL_Blog_ID", "Blog id isn't exist");
        }
        // 만약 optionalBlog가 isEmpty() = 비어 있다면 ResponseDto에 fail에 값을 전달.

        Blog blog = optionalBlog.get();  //아니라면 optionalBlog를 Blog에 넣음
        blog.update(requestDto);        //update 실행

        return ResponseDto.success(blog);
        // 아니라면 ResponseDto에 success에 Blog을 전달
    }

    @Transactional
    public ResponseDto<?> deleteBlog(Long id) {  //위랑 거의 동일
        Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isEmpty()) {
            return ResponseDto.fail("NOT_FOUND", "Blog id is not exist");
        }

        Blog blog = optionalBlog.get();

        blogRepository.delete(blog);

        return ResponseDto.success(true);
    }

    @Transactional
    public ResponseDto<?> validateAuthorByPassword(Long id, String password) {
        Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isEmpty()) {
            return ResponseDto.fail("NOT_FOUND", "Blog id is not exist");
        }

        Blog blog = optionalBlog.get();
        //Long id, String password를 받아온다.

        if (blog.getPassword().equals(password)) {  //getPassword()를 부르지도 않았는데 왜 사용가능한가?
            return ResponseDto.fail("PASSWORD_NOT_CORRECT", "password is not correct");
        }

        return ResponseDto.success(true);
    }

}
