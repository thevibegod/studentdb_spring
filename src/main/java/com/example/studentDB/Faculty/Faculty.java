package com.example.studentDB.Faculty;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@Entity
@Table(name = "faculty")
public class Faculty {

    public enum FacultyChoice {
        HOD, PROF, ASST_PROF;
    }

    @SequenceGenerator(name = "faculty_id_seq", allocationSize = 1, sequenceName = "faculty_id_seq")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "faculty_id_seq")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "employeeId", nullable = false, unique = true)
    private String employeeId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "designation", nullable = false)
    private FacultyChoice designation;

    public Faculty() {
    }

    public Faculty(String name, String email, String employeeId, FacultyChoice designation) {
        this.name = name;
        this.email = email;
        this.employeeId = employeeId;
        this.designation = designation;
    }

    public Faculty(Long id, String name, String email, String employeeId, FacultyChoice designation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.employeeId = employeeId;
        this.designation = designation;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public FacultyChoice getDesignation() {
        return designation;
    }

    public void setDesignation(FacultyChoice designation) {
        this.designation = designation;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }


    public Map<String, String> getJson() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("name", getName());
        data.put("designation", String.format("%d", getDesignation().ordinal()));
        data.put("email", getEmail());
        data.put("employeeId", getEmployeeId());
        return data;
    }
}
