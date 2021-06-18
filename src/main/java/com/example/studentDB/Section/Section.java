package com.example.studentDB.Section;


import com.example.studentDB.Department.Department;
import com.example.studentDB.Faculty.Faculty;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "section")
public class Section {

    @SequenceGenerator(name = "section_id_seq", allocationSize = 1, sequenceName = "section_id_seq")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "section_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "year")
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    private Department department;

    @OneToOne
    @JoinColumn(name = "class_advisor_id", referencedColumnName = "id")
    private Faculty classAdvisor;

    public Section() {
    }

    public Section(String name, Integer year, Department department, Faculty classAdvisor) {
        this.name = name;
        this.year = year;
        this.department = department;
        this.classAdvisor = classAdvisor;
    }

    public Section(Long id, String name, Integer year, Department department, Faculty classAdvisor) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.department = department;
        this.classAdvisor = classAdvisor;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Faculty getClassAdvisor() {
        return classAdvisor;
    }

    public void setClassAdvisor(Faculty classAdvisor) {
        this.classAdvisor = classAdvisor;
    }

    public Map<String, String> toJson() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("name", name);
        data.put("year", String.format("%d", year));
        data.put("dept_id", String.format("%d", department.getId()));
        data.put("class_advisor_id", String.format("%d", classAdvisor.getId()));
        return data;
    }
}
