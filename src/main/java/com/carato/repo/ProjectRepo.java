package com.carato.repo;

import com.carato.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo  extends JpaRepository<ProjectEntity, Long> {

}
