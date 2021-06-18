package com.example.studentDB.Section;

import com.example.studentDB.Department.Department;
import com.example.studentDB.Faculty.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {
    public Optional<Section> findByClassAdvisor(Faculty faculty);

    public List<Section> findSectionsByDepartment(Department department);
}
