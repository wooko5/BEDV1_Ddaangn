package com.dev.ddaangn.user.service;

import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.post.domain.Post;
import com.dev.ddaangn.post.dto.request.PostUpdateRequest;
import com.dev.ddaangn.post.dto.response.PostDetailResponse;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.dto.request.UserInsertRequestDto;
import com.dev.ddaangn.user.dto.request.UserUpdateRequestDto;
import com.dev.ddaangn.user.dto.response.UserDetailResponse;
import com.dev.ddaangn.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public UserDetailResponse insert(UserInsertRequestDto requestDto) {
        User user = requestDto.insertRequestDtoToEntity(requestDto);
        userRepository.save(user);
        return new UserDetailResponse(user);
    }

    @Transactional
    public UserDetailResponse findById(Long userId) {
        return userRepository.findById(userId)
                .map(UserDetailResponse::new)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_POST));
    }




    @Transactional
    public Page<UserDetailResponse> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserDetailResponse::new);
    }


    @Transactional
    public UserDetailResponse update(Long userId, UserUpdateRequestDto request) {
        User user = getUser(userId);
        user.updatedRequestDto(request);
        return new UserDetailResponse(user);
    }

    @Transactional
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER));
    }

    @Transactional
    public void delete(Long userId) {
        if (!userRepository.existsById(userId)) throw new NotFoundException(ErrorMessage.NOT_EXIST_POST);

        userRepository.deleteById(userId);
    }


}
