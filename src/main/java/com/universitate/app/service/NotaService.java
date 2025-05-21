package com.universitate.app.service;

import com.universitate.app.model.Curs;
import com.universitate.app.model.Nota;
import com.universitate.app.model.Student;
import com.universitate.app.repository.CursRepository;
import com.universitate.app.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private CursRepository cursRepository;

    public void saveNota(Nota nota) {
        notaRepository.save(nota);
    }

    public void deleteNota(Long id) {
        notaRepository.deleteById(id);
    }

    public void updateNotaPentruStudent(Long studentId, Long profesorId, Double valoareNota) {
        Optional<Curs> cursOptional = cursRepository.findByProfesorId(profesorId);
        if (cursOptional.isPresent()) {
            Curs curs = cursOptional.get();
            Optional<Nota> optionalNota = notaRepository.findByStudentIdAndCursId(studentId, curs.getId());

            Nota nota;
            if (optionalNota.isPresent()) {
                nota = optionalNota.get();
                nota.setValoare(valoareNota);
                nota.setDataNotare(LocalDate.now());
            } else {
                nota = new Nota();
                nota.setValoare(valoareNota);
                nota.setDataNotare(LocalDate.now());
                nota.setStudent(new Student(studentId));
                nota.setCurs(curs);
            }

            notaRepository.save(nota);
        }
    }
}
