package com.example.studentDB.Section;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class SectionAPIController {

    SectionService sectionService;

    @Autowired
    public SectionAPIController(SectionService sectionService) {
        this.sectionService = sectionService;
    }


    @GetMapping("/api/sections/all")
    public List<Section> getSections() {
        return sectionService.findSections();
    }

    @PostMapping("/api/sections")
    public Section createSection(@RequestBody Map<String, String> jsonData) {
        return sectionService.insertSection(jsonData);
    }

    @GetMapping("/api/sections")
    public Section getSection(@RequestParam(required = false) Long id, @RequestParam(required = false, name = "class_advisor_id") Long classAdvisorID) {
        return sectionService.findSection(id, classAdvisorID);
    }

    @PostMapping("/api/sections/delete/{id}")
    public void deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
    }

    @PostMapping("/api/sections/{id}")
    public Section updateSection(@PathVariable Long id, @RequestBody Map<String, String> jsonData) {
        return sectionService.updateSection(id, jsonData);
    }
}
