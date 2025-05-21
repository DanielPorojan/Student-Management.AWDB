package com.universitate.app.service;

import com.universitate.app.model.Profesor;
import com.universitate.app.model.Student;
import com.universitate.app.repository.ProfesorRepository;
import com.universitate.app.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Optional<Profesor> autentificare(String email, String parola) {
        return profesorRepository.findByEmail(email)
                .filter(p -> p.getParola().equals(parola));
    }

    @PostConstruct
    public void test() {
        System.out.println("Test query profesor:");
        profesorRepository.findAll().forEach(p -> System.out.println(p.getEmail()));
    }

    public Profesor getById(Long id) {
        return profesorRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Profesorul nu a fost găsit"));
    }

    public void adaugaStudentLaProfesor(Long profesorId, Long studentId) {
        Profesor profesor = getById(profesorId);
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalArgumentException("Studentul nu a fost găsit"));

        if (!profesor.getStudenti().contains(student)) {
            profesor.getStudenti().add(student);
            profesorRepository.save(profesor);
        }
    }

    public void stergeStudentDeLaProfesor(Long profesorId, Long studentId) {
        Profesor profesor = getById(profesorId);
        profesor.getStudenti().removeIf(s -> s.getId().equals(studentId));
        profesorRepository.save(profesor);
    }

    public Optional<Profesor> findById(Long id) {
        return profesorRepository.findById(id);
    }
}
