package org.example.hw5.service;

import org.example.hw5.dao.SimpleDAO;
import org.example.hw5.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("teacherService")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class TeacherService implements SimpleService<Integer, Teacher> {


    private final SimpleDAO<Integer, Teacher> TEACHER_DAO;


    @Autowired
    private TeacherService(@Qualifier("teacherDAO") SimpleDAO<Integer, Teacher> teacherDao) {
        TEACHER_DAO = teacherDao;
    }


    @Override
    public Teacher create(Teacher element) {
        return TEACHER_DAO.save(element);
    }

    @Transactional(readOnly = true)
    @Override
    public Teacher findById(Integer id) {
        return TEACHER_DAO.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> findAll() {
        return TEACHER_DAO.findAll();
    }

    @Override
    public void update(Teacher element) {
        TEACHER_DAO.update(element);
    }

    @Override
    public void delete(Integer id) {
        TEACHER_DAO.delete(id);
    }

}