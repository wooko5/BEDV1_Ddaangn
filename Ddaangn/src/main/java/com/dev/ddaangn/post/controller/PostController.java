package com.dev.ddaangn.post.controller;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.post.dto.request.PostInsertRequest;
import com.dev.ddaangn.post.dto.response.PostInsertResponse;
import com.dev.ddaangn.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1")
public class PostController {
    public final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<ErrorMessage> notFoundHandler(NotFoundException exception) {
        return ApiResponse.fail(ErrorMessage.of(exception.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<String> internalServerHandler(NotFoundException exception) {
        return ApiResponse.fail(exception.getMessage());
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<PostInsertResponse> insert(@RequestBody PostInsertRequest request) {
        return ApiResponse.ok(postService.insert(request));
    }
}
