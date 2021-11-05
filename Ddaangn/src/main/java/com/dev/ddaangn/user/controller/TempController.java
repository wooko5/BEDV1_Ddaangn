package com.dev.ddaangn.user.controller;

import com.dev.ddaangn.common.api.ApiResponse;
import com.dev.ddaangn.user.config.auth.LoginUser;
import com.dev.ddaangn.user.config.auth.dto.SessionUser;
import com.dev.ddaangn.user.dto.ListResponseDto;
import com.dev.ddaangn.user.dto.TempSaveResponseDto;
import com.dev.ddaangn.user.service.TempService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value ="/api/v1")
public class TempController {
    private final TempService tempService;

    // 자신의 온도를 수정할수 있어야 한다. (이름을 통해 기본값 36도 저장)
    @PutMapping("/temperature/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<String> insert(@PathVariable String name, @LoginUser SessionUser user) {
        if (user!=null) {


            return ApiResponse.ok(tempService.insert(name));
        }
        return ApiResponse.fail(tempService.insert(name));
    }

    // 사용자는 온도를 조회할 수 있어야 한다.
    @GetMapping("/temperature/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<TempSaveResponseDto> select(@PathVariable Long id, @LoginUser SessionUser user) {
        if (user != null) {
//            model.addAttribute("userName", user.getName());
            System.err.println("IDDD"+user.getId());
            return ApiResponse.ok(tempService.findById(id));
        }
        return ApiResponse.fail(tempService.findById(id));
    }


    @GetMapping("/temperature/list")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ListResponseDto>> findAll() {
        return ApiResponse.ok(tempService.findAll());
    }

    // 사용자는 평가를 토대로 온도가 수정되어야 한다.
//    @GetMapping("/temperature/evaluation")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ApiResponse<String> modify(EvaluationRequestDto evaluationRequestDto, @LoginUser SessionUser user) {
//        if (user!=null) {
//            return ApiResponse.ok(tempService.modify(evaluationRequestDto));
//        }
//        return ApiResponse.fail(tempService.modify(evaluationRequestDto));
//    }






}
