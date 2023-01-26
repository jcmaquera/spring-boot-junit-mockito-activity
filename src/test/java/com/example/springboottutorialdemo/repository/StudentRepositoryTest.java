package com.example.springboottutorialdemo.repository;

import com.example.springboottutorialdemo.entity.StudentEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Test that studentRepository will retrieve a student entity given a student id")
    public void findById() {
        //given: student_id
        int student_id = 1;
        StudentEntity expectedStudentEntity = new StudentEntity(1, "Test Name", 1, "Test Address");
        testEntityManager.persist(expectedStudentEntity);
        //when: studentRepository.findById is executed
        StudentEntity result = studentRepository.findById(student_id).get();
        //then: studentRepository.findById will return a studentEntity that is the same with expectedStudentEntity
        assertEquals(result, expectedStudentEntity);
    }

    @Test
    @DisplayName("Test to find by name in studentRepository")
    public void testFindByName() {

        String name = "Jc";
        StudentEntity expectedStudentEntity = new StudentEntity(1, "Jc", 1, "Muntinlupa");
        testEntityManager.persist(expectedStudentEntity);

        List<StudentEntity> studentList = studentRepository.findByName(name);

        for(StudentEntity student : studentList) {
            assertEquals(name, student.getName());
        }

    }
}