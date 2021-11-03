package com.dev.ddaangn.post.controller;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.post.dto.request.PostStatusUpdateRequest;
import com.dev.ddaangn.post.dto.request.PostUpdateRequest;
import com.dev.ddaangn.post.dto.response.PostDetailResponse;
import com.dev.ddaangn.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ApiResponse<PostDetailResponse> insert(@RequestBody PostInsertRequest request) {
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

    @PutMapping("/{id}")
    public ApiResponse<PostDetailResponse> update(@PathVariable("id") Long postId, @RequestBody PostUpdateRequest request) {
        return ApiResponse.ok(postService.update(postId, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") Long postId) {
        postService.delete(postId);
        return ApiResponse.ok(null);
    }

    @PutMapping("/{id}/status")
    public ApiResponse<PostDetailResponse> updateStatus(@PathVariable("id") Long postId, @Valid @RequestBody PostStatusUpdateRequest request) {
        return ApiResponse.ok(postService.updateStatus(postId, request));
    }

}
