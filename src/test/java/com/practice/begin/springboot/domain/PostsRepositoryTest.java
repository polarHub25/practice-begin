package com.practice.begin.springboot.domain;

import com.practice.begin.springboot.domain.posts.Posts;
import com.practice.begin.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postRepository;

    @After //Junit 에서 단위 테스트 끝날때마다 수행되는 메소드 지정
    public void cleanup(){
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postRepository.save(Posts.builder() //insert , update 쿼리 실행 (id가 있다면 update, 없으면 insert)
                        .title(title)
                        .content(content)
                        .author("olivetree258@gmail.com")
                        .build());
        //when
        List<Posts> postList = postRepository.findAll(); //post 테이블에 모든 데이터 조회

        //then
        Posts posts = postList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BastTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2021,5,18,0,0,0);
        postRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postList = postRepository.findAll();

        //then
        Posts posts = postList.get(0);

        System.out.println(">>>>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate ="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
