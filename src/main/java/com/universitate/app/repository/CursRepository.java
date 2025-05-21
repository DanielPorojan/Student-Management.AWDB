package com.universitate.app.repository;

import com.universitate.app.model.Curs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CursRepository extends JpaRepository<Curs, Long> {

    @Query("SELECT c FROM Curs c LEFT JOIN FETCH c.studenti WHERE c.profesor.id = :profesorId")
    Optional<Curs> findByProfesorId(@Param("profesorId") Long profesorId);
}
