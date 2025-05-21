package com.universitate.app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valoare;

    private LocalDate dataNotare;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "curs_id")
    private Curs curs;

    // Getteri și setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getValoare() { return valoare; }
    public void setValoare(Double valoare) { this.valoare = valoare; }

    public LocalDate getDataNotare() { return dataNotare; }
    public void setDataNotare(LocalDate dataNotare) { this.dataNotare = dataNotare; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Curs getCurs() { return curs; }
    public void setCurs(Curs curs) { this.curs = curs; }
}
