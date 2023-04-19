package com.kh.learnovation.domain.user.repository;

import com.kh.learnovation.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
