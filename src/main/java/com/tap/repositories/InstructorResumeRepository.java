package com.tap.repositories;

import com.tap.entities.InstructorResume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorResumeRepository extends JpaRepository<InstructorResume, Integer> {
}
