package org.example.hw5.repository;

import org.example.hw5.model.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("universityGroupRepository")
public interface UniversityGroupRepository extends JpaRepository<UniversityGroup, Integer> {

}
