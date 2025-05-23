package com.universitate.app.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nume;
    private String email;
    private String parola;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL) //asigura stergerea datelor stil cascade (ex: save delete sudenti)
    private List<Curs> cursuri;

    @ManyToMany
    @JoinTable(
            name = "profesor_student",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> studenti = new ArrayList<>();

    // Getteri și setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getParola() { return parola; }
    public void setParola(String parola) { this.parola = parola; }

    public List<Curs> getCursuri() { return cursuri; }
    public void setCursuri(List<Curs> cursuri) { this.cursuri = cursuri; }

    public List<Student> getStudenti() { return studenti; }
    public void setStudenti(List<Student> studenti) { this.studenti = studenti; }
}
