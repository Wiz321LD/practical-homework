package org.example.hw5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("ALL")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Generated()
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "group_number_fk")
    @JsonIgnore
    private UniversityGroup universityGroup;

    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name = "student_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id_fk"))
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    @JsonIgnore
    private List<Teacher> teachers;

    @Column(name = "grade")
    private int grade;


    public Student(String name, String surname, Date birthDate, UniversityGroup universityGroup, List<Teacher> teachers, int grade) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.universityGroup = universityGroup;
        this.teachers = teachers;
        this.grade = grade;
    }


    @SuppressWarnings("unused")
    public void addTeacher(Teacher teacher){
        if (teachers == null){
            teachers = new ArrayList<>();
        }
        teachers.add(teacher);
        if (teacher.getStudents() == null){
            teacher.setStudents(new ArrayList<>());
        }
        teacher.getStudents().add(this);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", universityGroup=" + universityGroup.getNumber() +
                ", grade=" + grade +
                ", teachers=" + teachers.stream().
                    map((x) -> x.getName() + " " + x.getSurname()).toList() +
                '}';
    }

}
