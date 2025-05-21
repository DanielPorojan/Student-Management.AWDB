package com.universitate.app.controller;

import com.universitate.app.model.Curs;
import com.universitate.app.model.Nota;
import com.universitate.app.model.Profesor;
import com.universitate.app.model.Student;
import com.universitate.app.repository.CursRepository;
import com.universitate.app.repository.NotaRepository;
import com.universitate.app.service.ProfesorService;
import com.universitate.app.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private CursRepository cursRepository;

    @GetMapping("/studenti-profesor")
    public String studentiProfesor(HttpSession session, Model model) {
        Long profesorId = (Long) session.getAttribute("profesorId");
        if (profesorId == null) return "redirect:/";

        Profesor profesor = profesorService.getById(profesorId);
        List<Student> studentiProfesorului = profesor.getStudenti();
        List<Student> totiStudentii = studentService.getAll();

        model.addAttribute("studentiProfesorului", studentiProfesorului);
        model.addAttribute("totiStudentii", totiStudentii);

        Optional<Curs> cursOptional = cursRepository.findByProfesorId(profesorId);
        Map<Long, Nota> noteStudenti = new HashMap<>();

        if (cursOptional.isPresent()) {
            Curs curs = cursOptional.get();
            for (Student student : studentiProfesorului) {
                notaRepository.findByStudentIdAndCursId(student.getId(), curs.getId())
                        .ifPresent(nota -> noteStudenti.put(student.getId(), nota));
            }
        }

        model.addAttribute("noteStudenti", noteStudenti);
        return "studenti_profesor";
    }

    @PostMapping("/studenti-profesor/adauga")
    public String adaugaStudentProfesor(
            @RequestParam String nume,
            @RequestParam String email,
            @RequestParam String grupa,
            @RequestParam(required = false) Boolean confirmaAdaugare,
            HttpSession session,
            Model model) {

        Long profesorId = (Long) session.getAttribute("profesorId");
        if (profesorId == null) return "redirect:/";

        Profesor profesor = profesorService.getById(profesorId);
        Optional<Student> optionalStudent = studentService.findByEmail(email);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            boolean dejaAsociat = profesor.getStudenti().stream()
                    .anyMatch(s -> s.getEmail().equalsIgnoreCase(email));

            if (dejaAsociat) {
                model.addAttribute("eroare", "Studentul cu acest email este deja în lista ta.");
                return studentiProfesor(session, model);
            }

            if (confirmaAdaugare == null || !confirmaAdaugare) {
                model.addAttribute("confirmareNecesara", true);
                model.addAttribute("studentEmail", email);
                model.addAttribute("studentNume", student.getNume());
                model.addAttribute("studentGrupa", student.getGrupa());
                model.addAttribute("nume", nume);
                model.addAttribute("grupa", grupa);
                return studentiProfesor(session, model);
            }

            profesorService.adaugaStudentLaProfesor(profesorId, student.getId());
            return "redirect:/studenti-profesor";
        }

        Student newStudent = new Student();
        newStudent.setNume(nume);
        newStudent.setEmail(email);
        newStudent.setGrupa(grupa);
        studentService.save(newStudent);
        profesorService.adaugaStudentLaProfesor(profesorId, newStudent.getId());

        return "redirect:/studenti-profesor";
    }

    @PostMapping("/studenti/adauga-la-profesor")
    public String adaugaStudentExistentLaProfesor(@RequestParam Long studentId, HttpSession session, Model model) {
        Long profesorId = (Long) session.getAttribute("profesorId");
        if (profesorId == null) return "redirect:/";

        Profesor profesor = profesorService.getById(profesorId);

        boolean dejaAsociat = profesor.getStudenti().stream()
                .anyMatch(s -> s.getId().equals(studentId));

        if (!dejaAsociat) {
            profesorService.adaugaStudentLaProfesor(profesorId, studentId);
        }

        return "redirect:/studenti";
    }

    @GetMapping("/studenti")
    public String totiStudentii(Model model) {
        model.addAttribute("studenti", studentService.getAll());
        return "studenti_admin";
    }

    @GetMapping("/studenti-profesor/sterge/{id}")
    public String stergeStudentDeLaProfesor(@PathVariable Long id, HttpSession session) {
        Long profesorId = (Long) session.getAttribute("profesorId");
        if (profesorId == null) return "redirect:/";
        profesorService.stergeStudentDeLaProfesor(profesorId, id);
        return "redirect:/studenti-profesor";
    }

    @PostMapping("/studenti")
    public String adaugaStudent(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/studenti";
    }

    @GetMapping("/studenti/sterge/{id}")
    public String stergeStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return "redirect:/studenti";
    }
}
