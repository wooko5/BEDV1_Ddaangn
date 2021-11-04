package com.dev.ddaangn.user.service;

import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.dto.ListResponseDto;
import com.dev.ddaangn.user.dto.TempSaveResponseDto;
import com.dev.ddaangn.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TempService {

    private final UserRepository userRepository;


    @Transactional
    public String insert(String name) {
        //name으로 DB 조회
        User user=getUser(name);
        user.setTemperature(36.0);
        return name;

    }

    @Transactional
    public User getUser(String userName) {
        return userRepository.findByName(userName)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER));
    }

    @Transactional
    public TempSaveResponseDto findById(Long id) {
        User entity=userRepository.findById(id).orElseThrow(()->new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER));
        return new TempSaveResponseDto(entity);
    }


    // 유저 전체 조회
    @Transactional
    public List<ListResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(ListResponseDto::new)
                .collect(Collectors.toList());
    }



}
