package org.example.hw6.repository;

import org.example.hw6.model.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("universityGroupRepository")
public interface UniversityGroupRepository extends JpaRepository<UniversityGroup, Integer> {

}
