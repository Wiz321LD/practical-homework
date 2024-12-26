package org.example.hw4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "second_floor_auditorium")
@PrimaryKeyJoinColumn(name = "room_number")
public class SecondFloorAuditorium extends Auditorium {

    @Column(name = "type")
    private String type;


    public SecondFloorAuditorium() {
    }

    public SecondFloorAuditorium(String type) {
        this.type = type;
    }

    public SecondFloorAuditorium(int roomNumber, String floor, UniversityGroup universityGroup, String type) {
        super(roomNumber, floor, universityGroup);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        SecondFloorAuditorium that = (SecondFloorAuditorium) object;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "SecondFloorAuditorium{" +
                "type='" + type + '\'' +
                ", roomNumber=" + super.getRoomNumber() +
                ", floor=" + super.getFloor() +
                ", universityGroup=" + super.getUniversityGroup().getNumber() +
                '}';
    }

}
