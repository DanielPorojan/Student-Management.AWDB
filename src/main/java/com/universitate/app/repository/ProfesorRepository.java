package com.universitate.app.repository;

import com.universitate.app.model.Profesor;
import com.universitate.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    // Folosit în autentificare din ProfesorService
    Optional<Profesor> findByEmail(String email);

    // Opțional: Poate fi păstrat pentru alte cazuri, dar în LoginController nu mai e folosit
    Optional<Profesor> findByEmailAndParola(String email, String parola);

    // Obține lista de studenți asociați unui profesor
    @Query("SELECT p.studenti FROM Profesor p WHERE p.id = :id")
    List<Student> findStudentiByProfesorId(@Param("id") Long id);
}
