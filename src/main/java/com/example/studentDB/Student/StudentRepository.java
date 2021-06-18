package com.example.studentDB.Student;

import com.example.studentDB.Section.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findByRollno(String rollno);

    List<Student> findStudentsBySection(Section section);
}