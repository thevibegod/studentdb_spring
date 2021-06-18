package com.example.studentDB.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class DepartmentAPIController {

    DepartmentService departmentService;

    @Autowired
    public DepartmentAPIController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/api/depts/all")
    public List<Department> getDepts() {
        return departmentService.findDepartments();
    }

    @PostMapping("/api/depts")
    public Department createDept(@RequestBody Department department) {
        return departmentService.insertDepartment(department);
    }

    @GetMapping("/api/depts")
    public Department getDept(@RequestParam(required = false) Long id, @RequestParam(required = false) String shortCode) {
        return departmentService.findDepartment(id, shortCode);
    }

    @PostMapping("/api/depts/delete/{id}")
    public void deleteDept(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @PostMapping("/api/depts/{id}")
    public Department updateDept(@PathVariable Long id, @RequestBody Map<String, String> jsonData) {
        return departmentService.updateDepartment(id, jsonData.get("name"), jsonData.get("shortCode"));
    }
}
