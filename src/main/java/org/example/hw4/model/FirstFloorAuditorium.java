package org.example.hw4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "first_floor_auditorium")
@PrimaryKeyJoinColumn(name = "room_number")
public class FirstFloorAuditorium extends Auditorium {

    @Column(name = "is_storage_present")
    private boolean storage_presence;


    public FirstFloorAuditorium() {
    }

    public FirstFloorAuditorium(boolean storage_presence) {
        this.storage_presence = storage_presence;
    }

    public FirstFloorAuditorium(int roomNumber, String floor, UniversityGroup universityGroup, boolean storage_presence) {
        super(roomNumber, floor, universityGroup);
        this.storage_presence = storage_presence;
    }

    public boolean isStorage_presence() {
        return storage_presence;
    }

    public void setStorage_presence(boolean storage_presence) {
        this.storage_presence = storage_presence;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        FirstFloorAuditorium that = (FirstFloorAuditorium) object;
        return storage_presence == that.storage_presence;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), storage_presence);
    }

    @Override
    public String toString() {
        return "FirstFloorAuditorium{" +
                "storage_presence=" + storage_presence +
                ", room number = " + super.getRoomNumber() +
                ", floor = " + super.getFloor() +
                ", universityGroup = " + super.getUniversityGroup().getNumber() +
                '}';
    }

}
