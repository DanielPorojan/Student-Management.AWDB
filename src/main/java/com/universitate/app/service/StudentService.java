package com.universitate.app.service;

import com.universitate.app.model.Student;
import com.universitate.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}
