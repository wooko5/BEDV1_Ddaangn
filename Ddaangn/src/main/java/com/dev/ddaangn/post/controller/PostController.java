package com.dev.ddaangn.post.controller;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.post.dto.response.PostDetailResponse;
import com.dev.ddaangn.post.dto.response.PostInsertResponse;
import com.dev.ddaangn.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/posts")
public class PostController {
    public final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PostInsertResponse> insert(@RequestBody PostInsertRequest request) {
        return ApiResponse.ok(postService.insert(request));
    }

    @GetMapping
    public ApiResponse<Page<PostDetailResponse>> getAll(Pageable pageable) {
        return ApiResponse.ok(postService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<PostDetailResponse> getOne(@PathVariable("id") Long id) {
        return ApiResponse.ok(postService.findById(id));
    }

}
