package org.example.hw3.model;

import java.util.HashSet;
import java.util.Set;

public class UniversityGroup {

    private int number;
    private Set<Student> students = new HashSet<Student>();


    public UniversityGroup(){}

    public UniversityGroup(int number, Set<Student> students) {
        this.number = number;
        this.students = students;
    }


    public void setNumber(int number) {
        this.number = number;
    }

    public void setStudents(Set<Student> students) {this.students = students;}

    public int getNumber() {
        return this.number;
    }

    public Set<Student> getStudents() {return this.students;}

    @Override
    public String toString() {
        return "UniversityGroup{" +
                "number=" + number +
                '}';
    }

}
