package org.example.hw4.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Generated;

import java.util.*;

@Entity
@Table(name = "teacher")
@NamedEntityGraph(
        name = "graphOfStudents",
        attributeNodes = {
                @NamedAttributeNode(value = "students", subgraph = "students-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(name = "students-subgraph", attributeNodes = {
                        @NamedAttributeNode(value = "universityGroup"),
                        @NamedAttributeNode(value = "teachers")
                })
        }
)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated
    @Column(name = "teacher_id")
    private int teacherId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Student> students;


    public Teacher(){}

    public Teacher(String name, String surname, Date birthDate, List<Student> students) {
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

    public List<Student> getStudents() {
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

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        if (students == null){
            students = new ArrayList<>();
        }
        students.add(student);
        if (student.getTeachers() == null){
            student.setTeachers(new ArrayList<>());
        }
        student.getTeachers().add(this);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", students=" + students.stream().
                    map((x) -> x.getName() + " " + x.getSurname()).toList() +
                '}';
    }

}
