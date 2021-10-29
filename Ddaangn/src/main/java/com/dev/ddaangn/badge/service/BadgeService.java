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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final BadgeConverter badgeConverter;
    private final UserRepository userRepository;

    public BadgeService(BadgeRepository badgeRepository, BadgeConverter badgeConverter, UserRepository userRepository) {
        this.badgeRepository = badgeRepository;
        this.badgeConverter = badgeConverter;
        this.userRepository = userRepository;
    }

    @Transactional
    public BadgeResponse save(BadgeRequest request) {
        User user = getUser(request.getUserId());
        Badge badge = badgeConverter.converterBadge(request, user);
        badge.setUser(user);
        Badge insertedBadge = badgeRepository.save(badge);
        return new BadgeResponse(insertedBadge);
    }

    @Transactional
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_EXIST_MEMBER));
    }

    @Transactional
    public Page<BadgeResponse> findAll(Pageable pageable) {
        return badgeRepository.findAll(pageable)
                .map(BadgeResponse::new);
    }
}
