package com.kyliethedev.MyProject.service;

import com.kyliethedev.MyProject.domain.Posts.Posts;
import com.kyliethedev.MyProject.domain.Posts.PostsRepository;
import com.kyliethedev.MyProject.web.dto.PostsListResponseDto;
import com.kyliethedev.MyProject.web.dto.PostsResponseDto;
import com.kyliethedev.MyProject.web.dto.PostsSaveRequestDto;
import com.kyliethedev.MyProject.web.dto.PostsUpdateRequestDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Post. id=" + id));

        return new PostsResponseDto(posts);
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Post.id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Post. id=" + id));

        postsRepository.delete(posts);
    }
}
