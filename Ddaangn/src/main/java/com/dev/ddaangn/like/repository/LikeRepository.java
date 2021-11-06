package com.dev.ddaangn.like.repository;

import com.dev.ddaangn.like.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
