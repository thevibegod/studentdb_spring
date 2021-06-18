package com.example.studentDB.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class StudentAPIController {

    StudentService studentService;

    @Autowired
    public StudentAPIController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/api/students/all")
    public List<Student> getStudents() {
        return studentService.findStudents();
    }

    @PostMapping("/api/students")
    public Student createStudent(@RequestBody Map<String, String> jsonData) {
        return studentService.insertStudent(jsonData);
    }

    @GetMapping("/api/students")
    public Student getStudent(@RequestParam(required = false) Long id, @RequestParam(required = false) String email, @RequestParam(required = false) String rollno) {
        return studentService.findStudent(id, rollno, email);
    }

    @PostMapping("/api/students/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/api/students/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Map<String, String> jsonData) {
        return studentService.updateStudent(id, jsonData);
    }
}
