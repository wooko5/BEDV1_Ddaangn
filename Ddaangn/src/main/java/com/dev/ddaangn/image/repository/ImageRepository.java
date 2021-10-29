package com.dev.ddaangn.image.repository;

import com.dev.ddaangn.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
