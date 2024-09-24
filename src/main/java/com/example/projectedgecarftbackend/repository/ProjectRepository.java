package com.example.projectedgecarftbackend.repository;

import com.example.projectedgecarftbackend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository <Project , String> {
    boolean existsByProjectId(String projectId);
}
