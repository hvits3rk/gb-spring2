package com.romantupikov.gbspring.security.repository;

import com.romantupikov.gbspring.security.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {
}
