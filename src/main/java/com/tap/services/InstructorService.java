package com.tap.services;

import com.tap.dto.InstructorCreationDto;
import com.tap.dto.InstructorDto;
import com.tap.entities.Instructor;
import com.tap.mappers.UserMapper;
import com.tap.repositories.InstructorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final UserMapper userMapper;

    public InstructorService(InstructorRepository instructorRepository, UserMapper userMapper) {
        this.instructorRepository = instructorRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public InstructorDto createInstructor(InstructorCreationDto dto) {
        Instructor instructor = new Instructor();
        instructor.setUsername(dto.getUsername());
        instructor.setPassword(dto.getPassword()); // Remember to hash passwords in a real application!
        instructor.setEmail(dto.getEmail());
        instructor.setFirstName(dto.getFirstName());
        instructor.setLastName(dto.getLastName());
        instructor.setAuthorization(false); // Default to not authorized
        instructor.setIsVerified(false);

        Instructor savedInstructor = instructorRepository.save(instructor);
        return userMapper.toInstructorDto(savedInstructor);
    }

    public InstructorDto getInstructorById(UUID id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        return userMapper.toInstructorDto(instructor);
    }

    public List<InstructorDto> getAllInstructors() {
        return instructorRepository.findAll().stream()
                .map(userMapper::toInstructorDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public InstructorDto updateInstructor(UUID id, InstructorCreationDto dto) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        instructor.setUsername(dto.getUsername());
        instructor.setEmail(dto.getEmail());
        instructor.setFirstName(dto.getFirstName());
        instructor.setLastName(dto.getLastName());
        // Note: Password updates should be handled separately and securely

        Instructor updatedInstructor = instructorRepository.save(instructor);
        return userMapper.toInstructorDto(updatedInstructor);
    }
}
