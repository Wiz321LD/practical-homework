package org.example.hw3.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Student {

    private int studentId;
    private String name;
    private String surname;
    private Date birthDate;
    private UniversityGroup universityGroup;


    public Student(){}

    public Student(int studentId, String name, String surname, Date birthDate, UniversityGroup universityGroup) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.universityGroup = universityGroup;
    }


    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public UniversityGroup getUniversityGroup() {
        return universityGroup;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setUniversityGroup(UniversityGroup universityGroup) {
        this.universityGroup = universityGroup;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", universityGroup=" + universityGroup +
                '}';
    }

}
