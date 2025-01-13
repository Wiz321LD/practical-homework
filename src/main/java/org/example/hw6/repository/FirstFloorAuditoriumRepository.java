package org.example.hw6.repository;

import org.example.hw6.model.FirstFloorAuditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("firstFloorAuditoriumRepository")
public interface FirstFloorAuditoriumRepository extends JpaRepository<FirstFloorAuditorium, Integer> {

}