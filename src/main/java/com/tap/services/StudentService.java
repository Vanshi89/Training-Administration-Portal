package com.tap.services;

import com.tap.dto.StudentCreationDto;
import com.tap.entities.Student;
import com.tap.repositories.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student createStudent(StudentCreationDto dto) {
        Student student = new Student();
        student.setUsername(dto.getUsername());
        student.setPassword(dto.getPassword()); // Remember to hash passwords!
        student.setEmail(dto.getEmail());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setAge(dto.getAge());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setAuthorization(false);
        student.setIsVerified(false);

        return studentRepository.save(student);
    }
}
