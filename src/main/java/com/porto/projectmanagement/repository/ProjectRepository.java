package com.porto.projectmanagement.repository;

import com.porto.projectmanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Project findByProjectName(String projectName);
}

