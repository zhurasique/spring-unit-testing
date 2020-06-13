package com.space.datahub.service;

import com.space.datahub.domain.Department;
import com.space.datahub.repo.DepartmentRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepo departmentRepository;

    public DepartmentService(DepartmentRepo departmentRepository) {
        this.departmentRepository =  departmentRepository;
    }


    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Department findByName(String name){
        return departmentRepository.findByName(name);
    }

    public Department save(@RequestBody Department department){
        return departmentRepository.save(department);
    }
}
