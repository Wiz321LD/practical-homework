package org.example.hw5.repository;

import org.example.hw5.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
