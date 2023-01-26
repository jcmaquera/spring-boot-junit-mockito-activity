package com.example.springboottutorialdemo.controller;

import com.example.springboottutorialdemo.entity.StudentEntity;
import com.example.springboottutorialdemo.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test is addStudent request will return an Ok status")
    public void testAddStudent() throws Exception {
        //given: student Entity
        StudentEntity expectedStudentEntity = new StudentEntity(1, "Test Name", 1, "Test Address");
        given(studentService.addStudent(expectedStudentEntity)).willReturn(expectedStudentEntity);
        //When: addStudent post request is called
        mockMvc.perform(MockMvcRequestBuilders.post("/student/").contentType(MediaType.APPLICATION_JSON).content("{\r\n" +
                "  \"id\": 1,\r\n" +
                "  \"name\": \"Test Name\",\r\n" +
                "  \"rollNo\": 1,\r\n" +
                "  \"address\": \"Test Address" +"\"\r\n" +
                //Then: The request result should return an OK status
                "}")).andExpect(status().isOk());

    }

    @Test
    @DisplayName("Test is getStudentById request will return an Ok status")
    public void testGetStudentById() throws Exception {

        Integer existingId = 3;
        StudentEntity student = new StudentEntity(existingId, "Jc", 2, "Muntinlupa");
        given(studentService.getStudentById(existingId)).willReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.get("/student/").contentType(MediaType.APPLICATION_JSON).content("{\r\n" +
                "  \"id\": 1,\r\n" +
                "  \"name\": \"Jc\",\r\n" +
                "  \"rollNo\": 1,\r\n" +
                "  \"address\": \"Muntinlupa" +"\"\r\n" +

                "}")).andExpect(MockMvcResultMatchers.status().isOk());



    }
}