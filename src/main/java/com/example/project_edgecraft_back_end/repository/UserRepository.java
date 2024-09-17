package com.example.project_edgecraft_back_end.repository;

import com.example.project_edgecraft_back_end.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}
