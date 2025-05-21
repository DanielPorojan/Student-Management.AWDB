package com.universitate.app.controller;

import com.universitate.app.model.Nota;
import com.universitate.app.service.NotaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping("/note")
    public String salveazaNota(@ModelAttribute Nota nota, HttpSession session, Model model) {
        if (nota.getValoare() > 10) {
            model.addAttribute("eroare", "Nota nu poate fi mai mare de 10.");
            return "studenti_profesor";
        }
        notaService.saveNota(nota);
        return "redirect:/studenti-profesor";
    }

    @GetMapping("/note/sterge/{id}")
    public String stergeNota(@PathVariable Long id) {
        notaService.deleteNota(id);
        return "redirect:/studenti-profesor";
    }

    @PostMapping("/note/update")
    public String updateNotaPentruStudent(@RequestParam Long studentId,
                                          @RequestParam Double nota,
                                          HttpSession session,
                                          Model model) {
        Long profesorId = (Long) session.getAttribute("profesorId");
        if (profesorId == null) {
            return "redirect:/";
        }

        if (nota > 10) {
            model.addAttribute("eroare", "Nota nu poate fi mai mare de 10.");
            return "studenti_profesor";
        }

        notaService.updateNotaPentruStudent(studentId, profesorId, nota);
        return "redirect:/studenti-profesor";
    }
}
