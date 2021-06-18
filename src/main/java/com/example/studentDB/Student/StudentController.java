package com.example.studentDB.Student;

import com.example.studentDB.Department.Department;
import com.example.studentDB.Department.DepartmentService;
import com.example.studentDB.Faculty.Faculty;
import com.example.studentDB.Faculty.FacultyService;
import com.example.studentDB.Section.Section;
import com.example.studentDB.Section.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class StudentController {

    StudentService studentService;
    SectionService sectionService;

    @Autowired
    public StudentController(StudentService studentService, SectionService sectionService) {
        this.sectionService = sectionService;
        this.studentService = studentService;
    }


    @GetMapping("/students/view")
    public ModelAndView getStudents() {
        ModelAndView mv = new ModelAndView("/students/view.jsp");
        mv.addObject("students", studentService.findStudents());
        return mv;
    }

    @GetMapping("/students/create")
    public ModelAndView createStudentGetView() {
        ModelAndView mv = new ModelAndView("/students/create.jsp");
        List<Section> sections = sectionService.findSections();
        mv.addObject("sections", sections);
        return mv;
    }

    @PostMapping("/students/create")
    public String createStudentPostView(HttpServletRequest request) {
        Map<String, String> jsonData = new HashMap<String, String>();
        for (String key : request.getParameterMap().keySet()) {
            jsonData.put(key, request.getParameter(key));
        }
        studentService.insertStudent(jsonData);
        return "redirect:/students/view";
    }

    @GetMapping("/students")
    public ModelAndView getStudent(@RequestParam Long id) {
        Student student = studentService.findStudent(id, null, null);
        ModelAndView mv = new ModelAndView("/students/viewStudent.jsp");
        mv.addObject("student", student);
        return mv;
    }

    @PostMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students/view";
    }

    @GetMapping("/students/edit/{id}")
    public ModelAndView updateStudentGetView(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/students/edit.jsp");
        Student student = studentService.findStudent(id, null, null);
        List<Section> sections = sectionService.findSections();
        mv.addObject("sections", sections);
        mv.addObject("student", student);
        return mv;
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudentPostView(@PathVariable Long id, HttpServletRequest request) {
        Map<String, String> jsonData = new HashMap<String, String>();
        for (String key : request.getParameterMap().keySet()) {
            jsonData.put(key, request.getParameter(key));
        }
        studentService.updateStudent(id, jsonData);
        return "redirect:/students/view";
    }
}
