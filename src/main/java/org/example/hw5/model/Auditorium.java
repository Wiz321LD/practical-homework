package org.example.hw5.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "auditorium")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class Auditorium {

    @Id
    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "floor")
    private String floor;

    @OneToOne
    @JoinColumn(name = "main_group_number", referencedColumnName = "number")
    private UniversityGroup universityGroup;


    @Override
    public String toString() {
        return "Auditorium{" +
                "roomNumber=" + roomNumber +
                ", floor='" + floor + '\'' +
                '}';
    }

}
