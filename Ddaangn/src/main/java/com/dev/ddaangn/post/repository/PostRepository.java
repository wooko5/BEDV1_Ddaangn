package com.dev.ddaangn.post.repository;

import com.dev.ddaangn.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
