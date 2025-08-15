package com.tap.services;

import com.tap.dto.InstructorCreationDto;
import com.tap.entities.Instructor;
import com.tap.repositories.InstructorRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Transactional
    public Instructor createInstructor(InstructorCreationDto dto) {
        Instructor instructor = new Instructor();
        instructor.setUsername(dto.getUsername());
        instructor.setPassword(dto.getPassword()); // Remember to hash passwords in a real application!
        instructor.setEmail(dto.getEmail());
        instructor.setFirstName(dto.getFirstName());
        instructor.setLastName(dto.getLastName());
        instructor.setAuthorization(false); // Default to not authorized
        instructor.setIsVerified(false);

        return instructorRepository.save(instructor);
    }
}
