package com.universitate.app.service;

import com.universitate.app.model.Curs;
import com.universitate.app.model.Student;
import com.universitate.app.repository.CursRepository;
import com.universitate.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursService {

    @Autowired
    private CursRepository cursRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Optional<Curs> getCursuriByProfesorId(Long profesorId) {
        return cursRepository.findByProfesorId(profesorId);
    }

    public void adaugaStudentLaCurs(Long cursId, Long studentId) {
        Curs curs = cursRepository.findById(cursId).orElseThrow();
        Student student = studentRepository.findById(studentId).orElseThrow();
        curs.getStudenti().add(student);
        cursRepository.save(curs);
    }

    public void stergeStudentDinCurs(Long cursId, Long studentId) {
        Curs curs = cursRepository.findById(cursId).orElseThrow();
        curs.getStudenti().removeIf(s -> s.getId().equals(studentId));
        cursRepository.save(curs);
    }

    public List<Curs> getAll() {
        return cursRepository.findAll();
    }

    public Curs getById(Long id) {
        return cursRepository.findById(id).orElse(null);
    }
}