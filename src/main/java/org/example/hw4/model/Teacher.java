package org.example.hw4.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Teacher {

    private int teacherId;
    private String name;
    private String surname;
    private Date birthDate;
    private Set<Student> students = new HashSet<Student>();


    public Teacher(){}

    public Teacher(int teacherId, String name, String surname, Date birthDate, Set<Student> students) {
        this.teacherId = teacherId;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.students = students;
    }


    public int getTeacherId() {
        return teacherId;
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", students=" + students.toString() +
                '}';
    }

}
