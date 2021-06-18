package com.example.studentDB.Section;

import com.example.studentDB.Department.Department;
import com.example.studentDB.Department.DepartmentService;
import com.example.studentDB.Faculty.Faculty;
import com.example.studentDB.Faculty.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class SectionController {

    SectionService sectionService;
    DepartmentService departmentService;
    FacultyService facultyService;

    @Autowired
    public SectionController(SectionService sectionService, DepartmentService departmentService, FacultyService facultyService) {
        this.sectionService = sectionService;
        this.departmentService = departmentService;
        this.facultyService = facultyService;
    }


    @GetMapping("/sections/view")
    public ModelAndView getSections() {
        ModelAndView mv = new ModelAndView("/sections/view.jsp");
        mv.addObject("sections", sectionService.findSections());
        return mv;
    }

    @GetMapping("/sections/create")
    public ModelAndView createSectionGetView() {
        ModelAndView mv = new ModelAndView("/sections/create.jsp");
        List<Department> departments = departmentService.findDepartments();
        List<Faculty> faculties = facultyService.findFaculties();
        mv.addObject("departments", departments);
        mv.addObject("faculties", faculties);
        return mv;
    }

    @PostMapping("/sections/create")
    public String createSectionPostView(HttpServletRequest request) {
        Map<String, String> jsonData = new HashMap<String, String>();
        for (String key : request.getParameterMap().keySet()) {
            jsonData.put(key, request.getParameter(key));
        }
        sectionService.insertSection(jsonData);
        return "redirect:/sections/view";
    }

    @GetMapping("/sections")
    public ModelAndView getSection(@RequestParam Long id) {
        Section section = sectionService.findSection(id, null);
        ModelAndView mv = new ModelAndView("/sections/viewSection.jsp");
        mv.addObject("section", section);
        return mv;
    }

    @PostMapping("/sections/delete/{id}")
    public String deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
        return "redirect:/sections/view";
    }

    @GetMapping("/sections/edit/{id}")
    public ModelAndView updateSectionGetView(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/sections/edit.jsp");
        Section section = sectionService.findSection(id, null);
        List<Department> departments = departmentService.findDepartments();
        List<Faculty> faculties = facultyService.findFaculties();
        mv.addObject("faculties", faculties);
        mv.addObject("departments", departments);
        mv.addObject("section", section);
        return mv;
    }

    @PostMapping("/sections/edit/{id}")
    public String updateSectionPostView(@PathVariable Long id, HttpServletRequest request) {
        Map<String, String> jsonData = new HashMap<String, String>();
        for (String key : request.getParameterMap().keySet()) {
            jsonData.put(key, request.getParameter(key));
        }
        Section section = sectionService.updateSection(id, jsonData);
        return "redirect:/sections/view" + section.getId();
    }
}
