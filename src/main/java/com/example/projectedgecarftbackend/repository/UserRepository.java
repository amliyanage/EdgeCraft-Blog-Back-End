package com.example.projectedgecarftbackend.repository;

import com.example.projectedgecarftbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByUserId(String userId);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    @Query("SELECT u.password FROM User u WHERE u.email = :email")
    String getPassword(@Param("email") String email);

    User getUserByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.email = :email, u.password = :password WHERE u.userName = :username")
    int updateUser(@Param("email") String email, @Param("password") String password, @Param("username") String username);

    Optional<User> findByEmail(String email);
}
