package com.practice.begin.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복의 어노테이션
@NoArgsConstructor // 롬복의 어노테이션 : 기본 생성자 자동 추가 public Posts(){}
@Entity // JPA 어노테이션 : 테이블과 링크될 클래스임을 나타냄
public class Posts { //Entity 클래스

    @Id // 해당 테이블의 pk , pk는 long 타입의 auto_increment 추천
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk의 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성
    public Posts(String title , String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title , String content){
        this.title = title;
        this.content = content;
    }
}
