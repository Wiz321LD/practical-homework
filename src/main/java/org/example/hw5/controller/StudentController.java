package org.example.hw5.controller;

import org.example.hw5.model.Student;
import org.example.hw5.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/string")
    public String getString(){
        return "Hello World";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll(){
        final List<Student> students = STUDENT_SERVICE.findAll();

        return students != null && !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getOne(@PathVariable(name = "id") int id){
        final Student student = STUDENT_SERVICE.findById(id);

        return student != null
                ? new ResponseEntity<>(student, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createBody")
    public ResponseEntity<HttpStatus> addStudentThroughBody(@RequestBody Student student){
        STUDENT_SERVICE.create(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable(name = "id") int id,
            @RequestBody Student student
    ){
        STUDENT_SERVICE.update(student);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        STUDENT_SERVICE.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
