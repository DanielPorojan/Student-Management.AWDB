package com.universitate.app.repository;

import com.universitate.app.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByStudentId(Long studentId);
    List<Nota> findByCursId(Long cursId);

    Optional<Nota> findByStudentIdAndCursId(Long studentId, Long cursId);
}
