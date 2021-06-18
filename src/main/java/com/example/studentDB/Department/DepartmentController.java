package com.example.studentDB.Department;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class DepartmentController {

    DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/depts/view")
    public ModelAndView viewDepartments() {
        List<Department> departmentList = departmentService.findDepartments();
        ModelAndView mv = new ModelAndView("/depts/view.jsp");
        mv.addObject("depts", departmentList);
        return mv;
    }

    @GetMapping("/depts")
    public ModelAndView viewDepartment(@RequestParam Long id) {
        Department department = departmentService.findDepartment(id, null);
        ModelAndView mv = new ModelAndView("/depts/viewDept.jsp");
        mv.addObject("dept", department);
        return mv;
    }

    @GetMapping("/depts/create")
    public String createDepartmentGetView() {
        return "/depts/create.jsp";
    }

    @PostMapping("/depts/create")
    public String createDepartment(Department department) {
        departmentService.insertDepartment(department);
        return "redirect:/depts/view";
    }

    @GetMapping("/depts/edit/{id}")
    public ModelAndView updateDeptGetView(@PathVariable Long id) {
        Department department = departmentService.findDepartment(id, null);
        ModelAndView mv = new ModelAndView("/depts/edit.jsp");
        mv.addObject("department", department);
        return mv;
    }

    @PostMapping("/depts/edit/{id}")
    public String updateDepartment(@PathVariable Long id, Department department) {
        departmentService.updateDepartment(id, department.getName(), department.getShortCode());
        return "redirect:/depts/view";
    }

    @PostMapping("/depts/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/depts/view";
    }
}