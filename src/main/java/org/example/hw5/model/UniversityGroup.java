package org.example.hw5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "university_group")
public class UniversityGroup {

    @Id
    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy = "universityGroup", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    @JsonIgnore
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
