package com.example.studentDB.Section;


import com.example.studentDB.Department.Department;
import com.example.studentDB.Department.DepartmentRepository;
import com.example.studentDB.Exceptions.InsufficientParameters;
import com.example.studentDB.Exceptions.ObjectAlreadyExists;
import com.example.studentDB.Exceptions.ObjectNotFound;
import com.example.studentDB.Exceptions.ServiceLevelException;
import com.example.studentDB.Faculty.Faculty;
import com.example.studentDB.Faculty.FacultyRepository;
import com.example.studentDB.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SectionService {

    SectionRepository sectionRepository;
    FacultyRepository facultyRepository;
    DepartmentRepository departmentRepository;
    StudentRepository studentRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository, FacultyRepository facultyRepository, DepartmentRepository departmentRepository, StudentRepository studentRepository) {
        this.sectionRepository = sectionRepository;
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    public Section insertSection(Map<String, String> jsonData) {
        try {
            String name = jsonData.get("name");
            String yearString = jsonData.get("year");
            String deptIdString = jsonData.get("dept_id");
            String classAdvisorString = jsonData.get("class_advisor_id");
            if (name == null || yearString == null || deptIdString == null || classAdvisorString == null) {
                throw new InsufficientParameters("Required Parameters : name,year,dept_id and class_advisor_id");
            }
            Long deptId = Long.parseLong(deptIdString);
            Long classAdvisorId = Long.parseLong(classAdvisorString);
            Integer year = Integer.parseInt(yearString);
            Department department = departmentRepository.findById(deptId).orElseThrow(() -> {
                throw new ObjectNotFound("Department with id:" + deptId + " not found");
            });
            Faculty classAdvisor = facultyRepository.findById(classAdvisorId).orElseThrow(() -> {
                throw new ObjectNotFound("Faculty with id:" + classAdvisorId + " not found");
            });
            Optional<Section> optionalSection = sectionRepository.findByClassAdvisor(classAdvisor);
            if (optionalSection.isPresent()) {
                throw new Exception("Class Advisor with faculty id:" + classAdvisorId + " is already associated with another section.");
            }
            Section section = new Section(name, year, department, classAdvisor);
            sectionRepository.save(section);
            return sectionRepository.findByClassAdvisor(section.getClassAdvisor()).orElseThrow(() -> {
                throw new ServiceLevelException("Cannot create object.");
            });
        } catch (Exception e) {
            throw new ServiceLevelException(e.getMessage());
        }
    }

    public Section findSection(Long id, Long classAdvisorId) {
        if (id == null) {
            if (classAdvisorId == null) {
                throw new InsufficientParameters("Expecting atleast one of the following params: id,class_advisor_id");
            }
            Faculty faculty = facultyRepository.findById(classAdvisorId).orElseThrow(() -> {
                throw new ObjectNotFound("Faculty with id:" + classAdvisorId + " not found");
            });
            return sectionRepository.findByClassAdvisor(faculty).orElseThrow(() -> {
                throw new ObjectNotFound("Section with Faculty id:" + classAdvisorId + " not found.");
            });
        }
        return sectionRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Section with id:" + id + " not found.");
        });
    }

    public List<Section> findSections() {
        return sectionRepository.findAll();
    }

    @Transactional
    public Section updateSection(Long id, Map<String, String> jsonData) {

        String deptIdString = jsonData.get("dept_id");
        String classAdvisorIDString = jsonData.get("class_advisor_id");
        String yearString = jsonData.get("year");
        String name = jsonData.get("name");

        Long deptID = deptIdString != null ? Long.parseLong(deptIdString) : null;
        Integer year = yearString != null ? Integer.parseInt(yearString) : null;
        Long classAdvisorID = classAdvisorIDString != null ? Long.parseLong(classAdvisorIDString) : null;

        Section section = sectionRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Section with id: " + id + " does not exist.");
        });

        try {
            if (name != null) {
                section.setName(name);
            }
            if (year != null) {
                section.setYear(year);
            }
            if (deptID != null && !section.getDepartment().getId().equals(deptID)) {
                Department department = departmentRepository.findById(deptID).orElseThrow(() -> {
                    throw new ObjectNotFound("Department with id: " + deptID + " does not exist.");
                });
                section.setDepartment(department);
            }
            if (classAdvisorID != null && !section.getClassAdvisor().getId().equals(classAdvisorID)) {
                Faculty classAdvisor = facultyRepository.findById(classAdvisorID).orElseThrow(() -> {
                    throw new ObjectNotFound("Faculty with id: " + classAdvisorID + " does not exist.");
                });
                Optional<Section> alreadyExists = sectionRepository.findByClassAdvisor(classAdvisor);
                if (alreadyExists.isPresent()) {
                    throw new ObjectAlreadyExists("Class Advisor already assigned to different section.");
                }
                section.setClassAdvisor(classAdvisor);
            }
            return section;
        } catch (Exception e) {
            throw new ServiceLevelException(e.getMessage());
        }

    }


    public void deleteSection(Long id) throws ObjectNotFound {
        Section section = sectionRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Section with id: " + id + " does not exist.");
        });
        studentRepository.deleteAll(studentRepository.findStudentsBySection(section));
        sectionRepository.delete(section);
    }

}