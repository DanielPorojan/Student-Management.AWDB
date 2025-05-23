package com.universitate.app.model;

import com.universitate.app.model.Curs;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nume;
    private String email;
    private String grupa;

    @ManyToMany
    @JoinTable(
            name = "student_curs",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "curs_id")
    )
    private List<Curs> cursuri;


    public Student() {}
    public Student(Long id) {
        this.id = id;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGrupa() { return grupa; }
    public void setGrupa(String grupa) { this.grupa = grupa; }

    public List<Curs> getCursuri() { return cursuri; }
    public void setCursuri(List<Curs> cursuri) { this.cursuri = cursuri; }
}
