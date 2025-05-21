package com.universitate.app.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Curs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titlu;
    private String descriere;



    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

//    @ManyToMany(mappedBy = "cursuri")
//    private List<Student> studenti;

    @ManyToMany
    @JoinTable(
            name = "student_curs",
            joinColumns = @JoinColumn(name = "curs_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> studenti;

    @OneToMany(mappedBy = "curs", cascade = CascadeType.ALL)
    private List<Nota> note;

    // Getteri și setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitlu() { return titlu; }
    public void setTitlu(String titlu) { this.titlu = titlu; }

    public String getDescriere() { return descriere; }
    public void setDescriere(String descriere) { this.descriere = descriere; }

    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }

    public List<Student> getStudenti() { return studenti; }
    public void setStudenti(List<Student> studenti) { this.studenti = studenti; }

    public List<Nota> getNote() { return note; }
    public void setNote(List<Nota> note) { this.note = note; }
}
