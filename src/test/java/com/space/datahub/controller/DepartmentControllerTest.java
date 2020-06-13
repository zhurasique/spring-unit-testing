package com.space.datahub.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.space.datahub.domain.Department;
import com.space.datahub.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
class DepartmentControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper;
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        departmentService = mock(DepartmentService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new DepartmentController(departmentService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void list(){
        List<Department> list = departmentService.findAll();
        when(departmentService.findAll()).thenReturn(list);

        try {
            mockMvc.perform( MockMvcRequestBuilders
                    .get("/api/department")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void create(){
        Department department = new Department();
        department.setName("Motoryzacja");
        department.setId(123);

        when(departmentService.save(department)).thenReturn(department);
        try {
            mockMvc.perform( MockMvcRequestBuilders
                    .post("/api/department")
                    .content(asJsonString(department))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void byName(){
        String name = "Motoryzacja";
        Department department = departmentService.findByName(name);
        when(departmentService.findByName(name)).thenReturn(department);

        try {
            mockMvc.perform( MockMvcRequestBuilders
                    .get("/api/department/name?name=" + name)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}