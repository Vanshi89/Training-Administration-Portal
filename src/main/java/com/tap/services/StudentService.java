package com.tap.services;

import com.tap.dto.StudentCreationDto;
import com.tap.dto.StudentDto;
import com.tap.dto.StudentPreferenceDto;
import com.tap.entities.Student;
import com.tap.entities.StudentPreference;
import com.tap.exceptions.ResourceNotFoundException;
import com.tap.mappers.UserMapper;
import com.tap.repositories.StudentRepository;
import com.tap.repositories.StudentPreferenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserMapper userMapper;
    private final StudentPreferenceRepository preferenceRepository;

    public StudentService(StudentRepository studentRepository, UserMapper userMapper, StudentPreferenceRepository preferenceRepository) {
        this.studentRepository = studentRepository;
        this.userMapper = userMapper;
        this.preferenceRepository = preferenceRepository;
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

    @Transactional
    public StudentPreferenceDto createOrUpdatePreference(UUID studentId, StudentPreferenceDto dto) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        StudentPreference preference = preferenceRepository.findByStudent(student)
                .orElse(new StudentPreference());

        preference.setStudent(student);
        preference.setRequiredSkills(dto.getRequiredSkills());
        preference.setPreferredTimeSlots(dto.getPreferredTimeSlots());
        preference.setPreferredStartTime(dto.getPreferredStartTime());
        preference.setPreferredEndTime(dto.getPreferredEndTime());

        StudentPreference savedPreference = preferenceRepository.save(preference);
        return userMapper.toStudentPreferenceDto(savedPreference);
    }

    public StudentPreferenceDto getPreferenceByStudentId(UUID studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        StudentPreference preference = preferenceRepository.findByStudent(student)
                .orElseThrow(() -> new ResourceNotFoundException("Preferences not found for student with id: " + studentId));
        return userMapper.toStudentPreferenceDto(preference);
    }
}
