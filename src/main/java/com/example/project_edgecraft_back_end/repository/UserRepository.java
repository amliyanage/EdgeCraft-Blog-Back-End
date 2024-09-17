package com.example.project_edgecraft_back_end.repository;

import com.example.project_edgecraft_back_end.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    @Query("SELECT u.password FROM User u WHERE u.email = :email")
    String findPasswordByEmail(@Param("email") String email);

    User findByEmail(String email);

}
