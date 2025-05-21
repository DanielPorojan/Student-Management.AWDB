package com.universitate.app.controller;

import com.universitate.app.model.Profesor;
import com.universitate.app.service.ProfesorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String parola,
                               HttpSession session,
                               Model model) {
        Optional<Profesor> optionalProfesor = profesorService.autentificare(email, parola);
        if (optionalProfesor.isPresent()) {
            session.setAttribute("profesor", optionalProfesor.get());
            return "redirect:/studenti-profesor";
        } else {
            model.addAttribute("error", "Email sau parolă incorectă");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
