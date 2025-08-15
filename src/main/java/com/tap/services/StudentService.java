package com.tap.services;

import com.tap.dto.StudentCreationDto;
import com.tap.dto.StudentDto;
import com.tap.entities.Student;
import com.tap.mappers.UserMapper;
import com.tap.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserMapper userMapper;

    public StudentService(StudentRepository studentRepository, UserMapper userMapper) {
        this.studentRepository = studentRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public StudentDto createStudent(StudentCreationDto dto) {
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

        Student savedStudent = studentRepository.save(student);
        return userMapper.toStudentDto(savedStudent);
    }

    public StudentDto getStudentById(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return userMapper.toStudentDto(student);
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(userMapper::toStudentDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentDto updateStudent(UUID id, StudentCreationDto dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setUsername(dto.getUsername());
        student.setEmail(dto.getEmail());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setAge(dto.getAge());

        Student updatedStudent = studentRepository.save(student);
        return userMapper.toStudentDto(updatedStudent);
    }
}
