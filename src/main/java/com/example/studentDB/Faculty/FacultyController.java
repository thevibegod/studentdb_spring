package com.example.studentDB.Faculty;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class FacultyController {

    FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @GetMapping("/faculty/view")
    public ModelAndView getFaculties() {
        ModelAndView mv = new ModelAndView("/faculty/view.jsp");
        mv.addObject("faculties", facultyService.findFaculties());
        return mv;
    }

    @GetMapping("/faculty/create")
    public ModelAndView createFacultyGetView() {
        ModelAndView mv = new ModelAndView("/faculty/create.jsp");
        Faculty.FacultyChoice[] choices = Faculty.FacultyChoice.values();
        mv.addObject("choices", choices);
        return mv;
    }

    @PostMapping("/faculty/create")
    public String createFacultyPostView(Faculty faculty) {
        facultyService.insertFaculty(faculty);
        return "redirect:/faculty/view";
    }

    @GetMapping("/faculty")
    public ModelAndView getFaculty(@RequestParam Long id) {
        Faculty faculty = facultyService.findFaculty(id, null, null);
        ModelAndView mv = new ModelAndView("/faculty/viewFaculty.jsp");
        mv.addObject("faculty", faculty);
        return mv;
    }

    @PostMapping("/faculty/delete/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return "redirect:/faculty/view";
    }

    @GetMapping("/faculty/edit/{id}")
    public ModelAndView updateFacultyGetView(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("/faculty/edit.jsp");
        Faculty faculty = facultyService.findFaculty(id, null, null);
        Faculty.FacultyChoice[] choices = Faculty.FacultyChoice.values();
        mv.addObject("faculty", faculty);
        mv.addObject("choices", choices);
        return mv;
    }

    @PostMapping("/faculty/edit/{id}")
    public String updateFacultyPostView(@PathVariable Long id, Faculty faculty) {
        facultyService.updateFaculty(id, faculty.getJson());
        return "redirect:/faculty/view";
    }
}
