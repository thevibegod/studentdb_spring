package com.example.studentDB.Department;

import com.example.studentDB.Exceptions.InsufficientParameters;
import com.example.studentDB.Exceptions.ObjectNotFound;
import com.example.studentDB.Exceptions.ServiceLevelException;

import com.example.studentDB.Section.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentService {

    DepartmentRepository departmentRepository;
    SectionRepository sectionRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, SectionRepository sectionRepository) {
        this.departmentRepository = departmentRepository;
        this.sectionRepository = sectionRepository;
    }

    public Department insertDepartment(Department department) throws ServiceLevelException {

        departmentRepository.save(department);
        return departmentRepository.findByShortCode(department.getShortCode()).orElseThrow(
                () -> {
                    throw new ServiceLevelException("Cannot create object.");
                }
        );


    }

    public Department findDepartment(Long id, String shortCode) throws InsufficientParameters {
        if (id == null) {
            if (shortCode == null) {
                throw new InsufficientParameters("Expecting atleast one of the following params: id,shortCode");
            }
            return departmentRepository.findByShortCode(shortCode).orElseThrow(() -> {
                throw new ObjectNotFound("Department with shortCode:" + shortCode + " not found.");
            });
        }
        return departmentRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Department with id:" + id + " not found.");
        });
    }

    public List<Department> findDepartments() throws ServiceLevelException {
        return departmentRepository.findAll();
    }

    @Transactional
    public Department updateDepartment(Long id, String name, String shortCode) throws ObjectNotFound {
        Department department = departmentRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Department with id: " + id + " does not exist.");
        });
        try {
            if (name != null) {
                department.setName(name);
            }
            if (shortCode != null) {
                department.setShortCode(shortCode);
            }
            return department;
        } catch (Exception e) {
            throw new ServiceLevelException(e.getMessage());
        }

    }


    public void deleteDepartment(Long id) throws ObjectNotFound {
        Department department = departmentRepository.findById(id).orElseThrow(() -> {
            throw new ObjectNotFound("Department with id: " + id + " does not exist.");
        });

        sectionRepository.deleteAll(sectionRepository.findSectionsByDepartment(department));
        departmentRepository.delete(department);
    }
}
