package com.dev.ddaangn.user.service;

import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.dto.request.UserInsertRequestDto;
import com.dev.ddaangn.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public String insert(UserInsertRequestDto requestDto) {

        User user = requestDto.insertRequestDtoToEntity(requestDto);
        userRepository.save(user);
        return requestDto.getName();
    }


    @Transactional
    public User getUser(String userName) {
        return userRepository.findByName(userName)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER));
    }


}
