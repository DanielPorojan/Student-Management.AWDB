package com.universitate.app.service;

import com.universitate.app.model.Profesor;
import com.universitate.app.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class ProfesorUserDetailsService implements UserDetailsService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Profesor profesor = profesorRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Profesorul nu a fost găsit"));

        // IMPORTANT: parola este deja criptată cu BCrypt în baza de date
        return User.builder()
                .username(profesor.getEmail())
                .password(profesor.getParola())
                .roles("PROFESOR")
                .build();
    }
}
