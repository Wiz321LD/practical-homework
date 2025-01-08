package org.example.hw5.repository;

import org.example.hw5.model.SecondFloorAuditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("secondFloorAuditoriumRepository")
public interface SecondFloorAuditoriumRepository extends JpaRepository<SecondFloorAuditorium, Integer> {

}