package com.dev.ddaangn.user.repository;

import com.dev.ddaangn.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
