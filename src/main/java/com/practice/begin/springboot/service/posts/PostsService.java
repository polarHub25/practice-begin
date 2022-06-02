package com.practice.begin.springboot.service.posts;

import com.practice.begin.springboot.domain.posts.Posts;
import com.practice.begin.springboot.domain.posts.PostsRepository;
import com.practice.begin.springboot.web.dto.PostsListResponseDto;
import com.practice.begin.springboot.web.dto.PostsResponseDto;
import com.practice.begin.springboot.web.dto.PostsSaveRequestDto;
import com.practice.begin.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id , PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id ){
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id"+id));

        return new PostsResponseDto(entity);
    }
    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되 조회 기능만 두기 : 등록,삭제.수정기능이 전혀 없는 서비스에서 사용하는것을 추천
    public List<PostsListResponseDto> finaAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);
    }
}
