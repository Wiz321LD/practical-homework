package org.example.hw6.service;

import org.example.hw6.model.Student;
import org.example.hw6.repository.StudentRepository;
import org.example.hw6.util.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("studentService")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class StudentService implements SimpleService<Integer, Student> {


    private final StudentRepository STUDENT_REPO;


    @Autowired
    public StudentService(StudentRepository studentRepo) {
        STUDENT_REPO = studentRepo;
    }


    @Override
    public Student create(Student element) {
        return STUDENT_REPO.save(element);
    }

    @Transactional(readOnly = true)
    @Override
    public Student findById(Integer id) {
        return STUDENT_REPO.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found!"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAll() {
        return STUDENT_REPO.findAll();
    }

    @Override
    public void update(Student element) {
        STUDENT_REPO.save(element);
    }

    @Override
    public void delete(Integer id) {
        STUDENT_REPO.deleteById(id);
    }

}