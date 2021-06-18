package com.example.studentDB.Student;


import com.example.studentDB.Section.Section;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @SequenceGenerator(name = "student_id_seq", allocationSize = 1, sequenceName = "student_id_seq")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "student_id_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "rollno", unique = true, nullable = false)
    private String rollno;


    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;


    public Student() {

    }

    public Student(String name, String email, String rollno, Section section) {
        this.name = name;
        this.email = email;
        this.rollno = rollno;
        this.section = section;
    }

    public Student(Long id, String name, String email, String rollno, Section section) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rollno = rollno;
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
