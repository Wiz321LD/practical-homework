package org.example.hw5.controller;

import org.example.hw5.model.Student;
import org.example.hw5.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {


    private final SimpleService<Integer, Student> STUDENT_SERVICE;


    @Autowired
    public StudentController(@Qualifier("studentService") SimpleService<Integer, Student> studentService) {
        STUDENT_SERVICE = studentService;
    }


    @ResponseBody
    @GetMapping("/{id}")
    public Student getStudent(@RequestBody @PathVariable("id") int id) {
        return STUDENT_SERVICE.findById(id);
    }

    @ResponseBody
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return STUDENT_SERVICE.findAll();
    }

    @ResponseBody
    @GetMapping("/string")
    public String getString(){
        return "Hello World";
    }

}
