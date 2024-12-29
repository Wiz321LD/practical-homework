package org.example.hw4.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Generated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
@NamedEntityGraph(
        name = "graphOfUniversityGroupAndTeachers",
        attributeNodes = {
                @NamedAttributeNode(value = "universityGroup", subgraph = "universityGroup-subgraph"),
                @NamedAttributeNode(value = "teachers", subgraph = "teachers-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(name = "universityGroup-subgraph", attributeNodes = {@NamedAttributeNode(value = "students")}),
                @NamedSubgraph(name = "teachers-subgraph", attributeNodes = {@NamedAttributeNode(value = "students")})
        }
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Generated
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_number_fk")
    private UniversityGroup universityGroup;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name = "student_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id_fk"))
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    private List<Teacher> teachers;

    @Column(name = "grade")
    private int grade;


    public Student(){}

    public Student(String name, String surname, Date birthDate, UniversityGroup universityGroup, List<Teacher> teachers, int grade) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.universityGroup = universityGroup;
        this.teachers = teachers;
        this.grade = grade;
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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public int getGrade() {
        return grade;
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

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

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
