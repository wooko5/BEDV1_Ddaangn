package com.dev.ddaangn.post.controller;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.like.dto.response.LikeDetailResponse;
import com.dev.ddaangn.like.service.LikeService;
import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.post.dto.request.PostStatusUpdateRequest;
import com.dev.ddaangn.post.dto.request.PostUpdateRequest;
import com.dev.ddaangn.post.dto.response.PostDetailResponse;
import com.dev.ddaangn.post.service.PostService;
import com.dev.ddaangn.user.config.auth.LoginUser;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
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
    public final LikeService likeService;

    public PostController(PostService postService, LikeService likeService) {
        this.postService = postService;
        this.likeService = likeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PostDetailResponse> insert(@RequestBody PostInsertRequest request, @LoginUser SessionUser user) {
        return ApiResponse.ok(postService.insert(request, user));
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
    public ApiResponse<PostDetailResponse> update(@PathVariable("id") Long postId, @RequestBody PostUpdateRequest request, @LoginUser SessionUser user) {
        return ApiResponse.ok(postService.update(postId, request, user));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") Long postId, @LoginUser SessionUser user) {
        postService.delete(postId, user);
        return ApiResponse.ok(null);
    }

    @PutMapping("/{id}/status")
    public ApiResponse<PostDetailResponse> updateStatus(@PathVariable("id") Long postId, @Valid @RequestBody PostStatusUpdateRequest request, @LoginUser SessionUser sessionUser) {
        return ApiResponse.ok(postService.updateStatus(postId, request, sessionUser));
    }

    @PutMapping("/{id}/toggle-hidden")
    public ApiResponse<PostDetailResponse> toggleHidden(@PathVariable("id") Long postId, @LoginUser SessionUser sessionUser) {
        return ApiResponse.ok(postService.toggleHidden(postId, sessionUser));
    }

    @PostMapping("/{id}/likes")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<LikeDetailResponse> createLike(@PathVariable("id") Long postId, @LoginUser SessionUser sessionUser) {
        return ApiResponse.ok(likeService.create(postId, sessionUser));
    }

    @DeleteMapping("/{id}/likes")
    public ApiResponse<LikeDetailResponse> deleteLike(@PathVariable("id") Long postId, @LoginUser SessionUser sessionUser) {
        likeService.delete(postId, sessionUser);
        return ApiResponse.ok(null);
    }

}
