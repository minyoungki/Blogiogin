package com.example.bloglogin.repository;

import com.example.bloglogin.entity.Blog;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findById(Long id);
    //Optional은 NPE(null 여부를 검사해야 하는데, null 검사를 해야하는 변수가 많은 경우 코드가 복잡)를 방지할 수 있도록 도와준다.
    // 즉 Blog형태의 데이터타입을 받는데 findById(Long id)의 Long id가 null값이 아니라면 id를 반환하고 null값이라면 빈 Optional객체를 반환
    List<Blog> findAllByOrderByModifiedAtDesc();
}
