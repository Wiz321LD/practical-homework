package org.example.hw4.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "university_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UniversityGroup {

    @Id
    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy = "universityGroup", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Student> students;


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

    public void addStudent(Student student) {
        if (this.students == null) {
            this.students = new ArrayList<>();
        }
        this.students.add(student);
        student.setUniversityGroup(this);
    }

    @Override
    public String toString() {
        return "UniversityGroup{" +
                "number=" + number +
                ", students=" + students.stream().
                    map((x) -> x.getName() + " " + x.getSurname()).toList() +
                '}';
    }

}
