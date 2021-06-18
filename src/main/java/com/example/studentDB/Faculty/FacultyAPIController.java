package com.example.studentDB.Faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class FacultyAPIController {

    FacultyService facultyService;

    @Autowired
    public FacultyAPIController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @GetMapping("/api/faculty/all")
    public List<Faculty> getFaculties() {
        return facultyService.findFaculties();
    }

    @PostMapping("/api/faculty")
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.insertFaculty(faculty);
    }

    @GetMapping("/api/faculty")
    public Faculty getFaculty(@RequestParam(required = false) Long id, @RequestParam(required = false) String email, @RequestParam(required = false) String employeeId) {
        return facultyService.findFaculty(id, email, employeeId);
    }

    @PostMapping("/api/faculty/delete/{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }

    @PostMapping("/api/faculty/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Map<String, String> jsonData) {
        return facultyService.updateFaculty(id, jsonData);
    }
}
