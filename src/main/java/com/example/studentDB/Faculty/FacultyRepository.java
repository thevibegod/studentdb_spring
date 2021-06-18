package com.example.studentDB.Faculty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    public Optional<Faculty> findByEmail(String email);

    public Optional<Faculty> findByEmployeeId(String employeeId);
}
