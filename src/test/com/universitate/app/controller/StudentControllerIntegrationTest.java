package com.universitate.app.controller;

import com.universitate.app.model.Student;
import com.universitate.app.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testGetAllStudentsReturnsOk() throws Exception {
        Student student = new Student();
        student.setNume("Test");
        student.setEmail("test@student.com");
        student.setGrupa("999");

        studentRepository.save(student);

        mockMvc.perform(get("/studenti")
                        .with(user("testuser").roles("USER"))  // utilizator fictiv
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }
}
