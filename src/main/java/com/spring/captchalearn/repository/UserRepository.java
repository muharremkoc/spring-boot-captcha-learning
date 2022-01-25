package com.spring.captchalearn.repository;

import com.spring.captchalearn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
