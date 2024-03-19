package com.mazbah.fly.repository;

import com.mazbah.fly.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, Long> {

    String findByTokenHash(String userId);
}
