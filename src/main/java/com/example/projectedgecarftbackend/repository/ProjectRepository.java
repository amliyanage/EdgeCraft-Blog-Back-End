package com.example.projectedgecarftbackend.repository;

import com.example.projectedgecarftbackend.dto.UserDTO;
import com.example.projectedgecarftbackend.entity.Project;
import com.example.projectedgecarftbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository <Project , String> {
    boolean existsByProjectId(String projectId);
    Project findByProjectId(String projectId);

    @Query("SELECT p FROM Project p WHERE p.user = :user")
    List<Project> getAllProjectByUser(@Param("user") User user);


}
