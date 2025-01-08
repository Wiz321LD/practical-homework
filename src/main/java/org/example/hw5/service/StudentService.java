package org.example.hw5.service;

import org.example.hw5.dao.SimpleDAO;
import org.example.hw5.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("studentService")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class StudentService implements SimpleService<Integer, Student> {


    private final SimpleDAO<Integer, Student> STUDENT_DAO;


    @Autowired
    private StudentService(@Qualifier("studentDAO") SimpleDAO<Integer, Student> studentDao) {
        STUDENT_DAO = studentDao;
    }


    @Override
    public Student create(Student element) {
        return STUDENT_DAO.save(element);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Transactional(readOnly = true)
    @Override
    public Student findById(Integer id) {
        return STUDENT_DAO.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> findAll() {
        return STUDENT_DAO.findAll();
    }

    @Override
    public void update(Student element) {
        STUDENT_DAO.update(element);
    }

    @Override
    public void delete(Integer id) {
        STUDENT_DAO.delete(id);
    }

}