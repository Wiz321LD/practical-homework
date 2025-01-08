package org.example.hw5.repository;

import org.example.hw5.model.FirstFloorAuditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("firstFloorAuditoriumRepository")
public interface FirstFloorAuditoriumRepository extends JpaRepository<FirstFloorAuditorium, Integer> {

}