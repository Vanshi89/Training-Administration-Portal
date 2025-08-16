package com.tap.mappers;

import com.tap.dto.InstructorDto;
import com.tap.dto.InstructorResumeDto;
import com.tap.dto.StudentDto;
import com.tap.dto.UserCreationDto;
import com.tap.dto.UserDto;
import com.tap.entities.Instructor;
import com.tap.entities.InstructorResume;
import com.tap.entities.Student;
import com.tap.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserCreationDto userCreationDto) {
        User user = new User();
        user.setUsername(userCreationDto.getUsername());
        user.setPassword(userCreationDto.getPassword());
        user.setEmail(userCreationDto.getEmail());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());

        if (user instanceof Instructor) {
            dto.setRole("instructor");
        } else if (user instanceof Student) {
            dto.setRole("student");
        } else {
            dto.setRole("admin");
        }
        
        return dto;
    }

    public InstructorDto toInstructorDto(Instructor instructor) {
        InstructorDto dto = new InstructorDto();
        dto.setUserId(instructor.getUserId());
        dto.setUsername(instructor.getUsername());
        dto.setEmail(instructor.getEmail());
        dto.setFirstName(instructor.getFirstName());
        dto.setLastName(instructor.getLastName());
        dto.setFullName(instructor.getFullName());
        dto.setIsVerified(instructor.getIsVerified());
        return dto;
    }

    public StudentDto toStudentDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setUserId(student.getUserId());
        dto.setUsername(student.getUsername());
        dto.setEmail(student.getEmail());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setFullName(student.getFullName());
        dto.setPhoneNumber(student.getPhoneNumber());
        dto.setAge(student.getAge());
        dto.setIsVerified(student.getIsVerified());
        return dto;
    }

    public InstructorResumeDto toInstructorResumeDto(InstructorResume resume) {
        InstructorResumeDto dto = new InstructorResumeDto();
        dto.setResumeId(resume.getResumeId());
        dto.setInstructorId(resume.getInstructor().getUserId());
        dto.setResumeUrl(resume.getResumeUrl());
        dto.setUploadedAt(resume.getUploadedAt());
        return dto;
    }
}
