package org.example.hw6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;

import java.util.*;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("unused")
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
    @Fetch(FetchMode.JOIN)
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    @JsonIgnore
    private List<Student> students;


    public Teacher(String name, String surname, Date birthDate, List<Student> students) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
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
