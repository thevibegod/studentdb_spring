package com.example.studentDB.Student;


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


@Service
public class StudentService {

    SectionRepository sectionRepository;
    StudentRepository studentRepository;

    @Autowired
    public StudentService(SectionRepository sectionRepository, StudentRepository studentRepository) {
        this.sectionRepository = sectionRepository;
        this.studentRepository = studentRepository;
    }

    public Student insertStudent(Map<String, String> jsonData) {
        try {
            String name = jsonData.get("name");
            String email = jsonData.get("email");
            String rollno = jsonData.get("rollno");
            String sectionIDString = jsonData.get("section_id");
            if (name == null || email == null || rollno == null || sectionIDString == null) {
                throw new InsufficientParameters("Required Parameters : name,email,rollno and section_id");
            }
            Long sectionId = Long.parseLong(sectionIDString);

            Section section = sectionRepository.findById(sectionId).orElseThrow(() -> {
                throw new ObjectNotFound("Section with id:" + sectionId + " not found.");
            });

            Student student = new Student(name, email, rollno, section);
            studentRepository.save(student);
            return studentRepository.findByEmail(student.getEmail()).orElseThrow(() -> {
                throw new ServiceLevelException("Cannot create object.");
            });
        } catch (Exception e) {
            throw new ServiceLevelException(e.getMessage());
        }
    }

    public Student findStudent(Long id, String rollno, String email) {
        if (id == null) {
            if (rollno == null) {
                if (email == null)
                    throw new InsufficientParameters("Expecting atleast one of the following params: id,rollno,email");
                return studentRepository.findByEmail(email).orElseThrow(() -> {
                    throw new ObjectNotFound("Student with email:" + email + " not found");
                });
            }
            return studentRepository.findByRollno(rollno).orElseThrow(() -> {
                throw new ObjectNotFound("Student with rollno:" + rollno + " not found");
            });
        }
        return studentRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Section with id:" + id + " not found.");
        });
    }

    public List<Student> findStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public Student updateStudent(Long id, Map<String, String> jsonData) {

        Student student = studentRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Student with id:" + id + " does not exist.");
        });

        String name = jsonData.get("name");
        String email = jsonData.get("email");
        String rollno = jsonData.get("rollno");
        String sectionIDString = jsonData.get("section_id");

        Long sectionId = sectionIDString != null ? Long.parseLong(sectionIDString) : null;


        try {
            if (name != null) {
                student.setName(name);
            }
            if (sectionId != null) {
                Section section = sectionRepository.findById(sectionId).orElseThrow(() -> {
                    throw new ObjectNotFound("Section with id: " + sectionId + " does not exist.");
                });
                student.setSection(section);
            }
            if (rollno != null) {
                student.setRollno(rollno);
            }
            if (email != null) {
                student.setEmail(email);
            }
            return student;
        } catch (Exception e) {
            throw new ServiceLevelException(e.getMessage());
        }

    }


    public void deleteStudent(Long id) throws ObjectNotFound {
        Student student = studentRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Student with id: " + id + " does not exist.");
        });
        studentRepository.delete(student);
    }

}