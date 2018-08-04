package com.romantupikov.gbspring.security.repository;

import com.romantupikov.gbspring.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
