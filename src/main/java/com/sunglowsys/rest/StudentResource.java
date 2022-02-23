package com.sunglowsys.rest;

import com.sunglowsys.domain.Student;
import com.sunglowsys.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class StudentResource {
    private final Logger log = LoggerFactory.getLogger(StudentResource.class);

    private final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home(Pageable pageable, Model model){
        log.debug("REST Request to get Students: {}",pageable.toString());
        Page<Student> page = studentService.getAllStudents(pageable);
        List<Student> result = page.getContent();
        System.out.println(result);
        model.addAttribute("student", result);
        return "index";

    }
    @GetMapping("/students/create")
    public String createStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        return "create-student";
    }

    @PostMapping("/students")
    public String createStudents(@ModelAttribute("student") Student student) throws URISyntaxException {
        log.debug("Rest Request to save Student: {}",student);
        if (student.getId() == null){
            studentService.saveStudent(student);
        }
        if (student.getId() != null){
            studentService.updateStudent(student);
        }
        return "redirect:/api/";
    }

    @GetMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, Model model){
        log.debug("REST Request to update Student: {}",id);
        Student student = studentService.findById(id).get();
        model.addAttribute("student",student);
        return "create-student";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        log.debug("REST Request to delete Student");
        studentService.deleteStudent(id);
        return "redirect:/api/";

    }
}
