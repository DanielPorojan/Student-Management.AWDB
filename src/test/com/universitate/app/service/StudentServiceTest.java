package com.universitate.app.service;

import com.universitate.app.model.Student;
import com.universitate.app.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    private StudentService studentService;
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() throws Exception {
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentService();

        // injectăm studentRepository în câmpul privat cu reflecție
        Field field = StudentService.class.getDeclaredField("studentRepository");
        field.setAccessible(true);
        field.set(studentService, studentRepository);
    }

    @Test
    void testSaveStudent() {
        Student student = new Student();
        student.setNume("Ion");
        student.setEmail("ion@example.com");
        student.setGrupa("105");

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student result = studentService.save(student);

        assertEquals("Ion", result.getNume());
        verify(studentRepository, times(1)).save(student);
    }
}
