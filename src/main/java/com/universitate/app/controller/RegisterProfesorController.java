package com.universitate.app.controller;

import com.universitate.app.model.Curs;
import com.universitate.app.model.Profesor;
import com.universitate.app.repository.CursRepository;
import com.universitate.app.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private CursRepository cursRepository;

    @GetMapping("/register-profesor")
    public String showRegistrationForm(Model model) {
        model.addAttribute("profesor", new Profesor());
        return "register-profesor";
    }

    @PostMapping("/register-profesor")
    public String registerProfesor(@ModelAttribute Profesor profesor,
                                   @RequestParam("numeCurs") String numeCurs,
                                   Model model) {
        if (numeCurs == null || numeCurs.trim().isEmpty()) {
            model.addAttribute("profesor", profesor);
            model.addAttribute("cursError", "Numele cursului nu poate fi gol.");
            return "register-profesor";
        }

        // Criptare parolă
        profesor.setParola(new BCryptPasswordEncoder().encode(profesor.getParola()));

        // Salvăm profesorul
        Profesor saved = profesorRepository.save(profesor);

        // Salvăm cursul asociat
        Curs curs = new Curs();
        curs.setTitlu(numeCurs.trim());
        curs.setProfesor(saved);
        cursRepository.save(curs);

        return "redirect:/login";
    }
}
