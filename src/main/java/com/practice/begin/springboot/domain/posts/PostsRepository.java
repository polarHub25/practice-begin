package com.practice.begin.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {// Entity 클래스는 기본 Repository 없이는 제대로 역할 못함


}
