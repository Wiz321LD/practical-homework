package org.example.hw4.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "university_group")
public class UniversityGroup {

    @Id
    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy = "universityGroup")
    private List<Student> students = new ArrayList<>();


    public UniversityGroup(){}

    public UniversityGroup(int number, List<Student> students) {
        this.number = number;
        this.students = students;
    }


    public void setNumber(int number) {
        this.number = number;
    }

    public void setStudents(List<Student> students) {this.students = students;}

    public int getNumber() {
        return this.number;
    }

    public List<Student> getStudents() {return this.students;}

    @Override
    public String toString() {
        return "UniversityGroup{" +
                "number=" + number +
                '}';
    }

}
