package com.kyliethedev.MyProject.web;

import com.kyliethedev.MyProject.service.PostsService;
import com.kyliethedev.MyProject.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    @ResponseBody
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }
}
