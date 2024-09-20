package com.example.projectedgecarftbackend.repository;

import com.example.projectedgecarftbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUserId(String userId);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    @Query("SELECT u.password FROM User u WHERE u.email = :email")
    String getPassword(@Param("email") String email);

}
