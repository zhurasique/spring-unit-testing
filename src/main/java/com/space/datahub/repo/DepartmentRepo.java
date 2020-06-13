package com.space.datahub.repo;

import com.space.datahub.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Department findByName(String name);
}