package org.example.hw5.repository;

import org.example.hw5.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("teacherRepository")
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
