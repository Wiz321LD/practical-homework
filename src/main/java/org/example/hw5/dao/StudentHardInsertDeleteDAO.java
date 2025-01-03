package org.example.hw5.dao;

import java.util.List;

public interface StudentHardInsertDeleteDAO<K, E> {

    //CREATE
    void insert100_000BatchRawsInTable();

    //DELETE
    void delete100_000BatchRawsInTable();

    //READ
    E getStudentWhereGradeMoreThan2000();

    //READ
    List<E> getStudentsWhereGradeMoreThan2000();

    //READ
    E findStudentAndTeachers(K studentId);

}