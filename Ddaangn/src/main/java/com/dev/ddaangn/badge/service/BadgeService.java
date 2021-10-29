package com.dev.ddaangn.badge.service;

import com.dev.ddaangn.badge.converter.BadgeConverter;
import com.dev.ddaangn.badge.dto.BadgeResponse;
import com.dev.ddaangn.badge.repository.BadgeRepository;
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
    public Page<BadgeResponse> findAll(Pageable pageable) {
        return badgeRepository.findAll(pageable)
                .map(BadgeResponse::new);
    }
}
