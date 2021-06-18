package com.example.studentDB.Faculty;

import com.example.studentDB.Exceptions.InsufficientParameters;
import com.example.studentDB.Exceptions.ObjectNotFound;
import com.example.studentDB.Exceptions.ServiceLevelException;
import com.example.studentDB.Section.Section;
import com.example.studentDB.Section.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FacultyService {

    FacultyRepository facultyRepository;
    SectionRepository sectionRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository, SectionRepository sectionRepository) {
        this.facultyRepository = facultyRepository;
        this.sectionRepository = sectionRepository;
    }

    public Faculty insertFaculty(Faculty faculty) {

        facultyRepository.save(faculty);
        return facultyRepository.findByEmail(faculty.getEmail()).orElseThrow(
                () -> {
                    throw new ServiceLevelException("Cannot create object.");
                }
        );


    }

    public Faculty findFaculty(Long id, String email, String employeeId) {
        if (id == null) {
            if (email == null) {
                if (employeeId == null)
                    throw new InsufficientParameters("Expecting atleast one of the following params: id,email,employeeId");
                return facultyRepository.findByEmployeeId(employeeId).orElseThrow(() -> {
                    throw new ObjectNotFound("Faculty with employeeId:" + employeeId + " not found.");
                });
            }
            return facultyRepository.findByEmail(email).orElseThrow(() -> {
                throw new ObjectNotFound("Faculty with email:" + email + " not found.");
            });
        }
        return facultyRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Faculty with id:" + id + " not found.");
        });
    }

    public List<Faculty> findFaculties() {
        return facultyRepository.findAll();
    }

    @Transactional
    public Faculty updateFaculty(Long id, Map<String, String> jsonData) {
        String designationString = jsonData.get("designation");
        Integer designation = designationString != null ? Integer.parseInt(designationString) : null;
        String email = jsonData.get("email");
        String employeeId = jsonData.get("employeeId");
        String name = jsonData.get("name");

        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Faculty with id: " + id + " does not exist.");
        });
        try {
            if (designation != null) {
                Faculty.FacultyChoice choice = designation == 0 ? Faculty.FacultyChoice.HOD : designation == 1 ? Faculty.FacultyChoice.PROF : Faculty.FacultyChoice.ASST_PROF;
                faculty.setDesignation(choice);
            }
            if (email != null) {
                faculty.setEmail(email);
            }
            if (employeeId != null) {
                faculty.setEmployeeId(employeeId);
            }
            if (name != null) {
                faculty.setName(name);
            }
            return faculty;
        } catch (Exception e) {
            throw new ServiceLevelException(e.getMessage());
        }

    }


    public void deleteFaculty(Long id) throws ObjectNotFound {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Department with id: " + id + " does not exist.");
        });

        Optional<Section> section = sectionRepository.findByClassAdvisor(faculty);
        if (section.isPresent()) {
            throw new ServiceLevelException("Cannot delete faculty with id:" + id + " as the faculty is associated with section " + section.get().getId());
        }
        facultyRepository.delete(faculty);
    }
}


