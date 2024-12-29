package org.example.hw4.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Generated;

import java.util.Objects;

@Entity
@Table(name = "auditorium")
@Inheritance(strategy = InheritanceType.JOINED)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public abstract class Auditorium {

    @Id
    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "floor")
    private String floor;

    @OneToOne
    @JoinColumn(name = "main_group_number", referencedColumnName = "number")
    private UniversityGroup universityGroup;


    public Auditorium() {
    }

    public Auditorium(int roomNumber, String floor, UniversityGroup universityGroup) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.universityGroup = universityGroup;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public UniversityGroup getUniversityGroup() {
        return universityGroup;
    }

    public void setUniversityGroup(UniversityGroup universityGroup) {
        this.universityGroup = universityGroup;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Auditorium that = (Auditorium) object;
        return roomNumber == that.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roomNumber);
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "roomNumber=" + roomNumber +
                ", floor='" + floor + '\'' +
                '}';
    }

}
