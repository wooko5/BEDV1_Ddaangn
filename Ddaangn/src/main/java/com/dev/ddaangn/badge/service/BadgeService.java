package com.dev.ddaangn.badge.service;

import com.dev.ddaangn.badge.Badge;
import com.dev.ddaangn.badge.converter.BadgeConverter;
import com.dev.ddaangn.badge.dto.BadgeRequest;
import com.dev.ddaangn.badge.dto.BadgeResponse;
import com.dev.ddaangn.badge.repository.BadgeRepository;
import com.dev.ddaangn.common.error.ErrorMessage;
import com.dev.ddaangn.common.error.exception.NotFoundException;
import com.dev.ddaangn.user.User;
import com.dev.ddaangn.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final BadgeConverter badgeConverter;
    private final UserRepository userRepository;

    //    @Transactional
//    public BadgeResponse save(BadgeRequest request) {
//        User user = getUser(request.getUserId());
//        Badge badge = badgeConverter.converterBadge(request, user);
//        badge.setUser(user);
//        Badge insertedBadge = badgeRepository.save(badge);
//        return new BadgeResponse(insertedBadge);
//    }
//

    @Transactional
    public Long save(BadgeRequest request) {
        User user = getUser(request.getUserId());
        Badge badge = badgeConverter.converterBadge(request, user);
        Badge savedBadge = badgeRepository.save(badge);
        return savedBadge.getId();
    }

    @Transactional
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER));
    }

    @Transactional
    public List<BadgeResponse> findAll() {
        return badgeRepository.findAll().stream()
                .map(BadgeResponse::new)
                .collect(Collectors.toList());
    }
}
