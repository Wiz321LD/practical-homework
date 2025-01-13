package org.example.hw6.service;

import org.example.hw6.model.Teacher;
import org.example.hw6.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("teacherService")
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Transactional
public class TeacherService implements SimpleService<Integer, Teacher> {


    private final TeacherRepository TEACHER_REPO;


    @Autowired
    public TeacherService(TeacherRepository teacherRepo) {
        TEACHER_REPO = teacherRepo;
    }


    @Override
    public Teacher create(Teacher element) {
        return TEACHER_REPO.save(element);
    }

    @Transactional(readOnly = true)
    @Override
    public Teacher findById(Integer id) {
        return TEACHER_REPO.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> findAll() {
        return TEACHER_REPO.findAll();
    }

    @Override
    public void update(Teacher element) {
        TEACHER_REPO.save(element);
    }

    @Override
    public void delete(Integer id) {
        TEACHER_REPO.deleteById(id);
    }

}