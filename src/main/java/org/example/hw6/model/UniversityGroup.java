package org.example.hw6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "university_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UniversityGroup {

    @Id
    @Column(name = "number")
    private int number;

    @OneToMany(mappedBy = "universityGroup", fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @Cascade({org.hibernate.annotations.CascadeType.REMOVE})
    @JsonIgnore
    private List<Student> students;


    @SuppressWarnings("unused")
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
