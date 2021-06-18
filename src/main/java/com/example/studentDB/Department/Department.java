package com.example.studentDB.Department;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @SequenceGenerator(name = "dept_id_seq", allocationSize = 1, sequenceName = "dept_id_seq")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dept_id_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "short_code", nullable = false, unique = true)
    private String shortCode;

    @Column(name = "name", nullable = false)
    private String name;

    public Department() {
    }

    public Department(String shortCode, String name) {
        this.shortCode = shortCode;
        this.name = name;
    }

    public Department(Long id, String shortCode, String name) {
        this.id = id;
        this.shortCode = shortCode;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
