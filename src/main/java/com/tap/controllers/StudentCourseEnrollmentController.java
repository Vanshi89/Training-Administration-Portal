package com.tap.controllers;

import com.tap.dto.EnrollmentRequestDto;
import com.tap.dto.ProgressUpdateDto;
import com.tap.dto.StudentCourseEnrollmentDto;
import com.tap.services.StudentCourseEnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enrollments")
public class StudentCourseEnrollmentController {

    private final StudentCourseEnrollmentService enrollmentService;

    public StudentCourseEnrollmentController(StudentCourseEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<StudentCourseEnrollmentDto> enrollStudent(@RequestBody EnrollmentRequestDto enrollmentRequest) {
        StudentCourseEnrollmentDto enrollmentDto = enrollmentService.enrollStudentInCourse(enrollmentRequest);
        return new ResponseEntity<>(enrollmentDto, HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentCourseEnrollmentDto>> getEnrollmentsByStudentId(@PathVariable UUID studentId) {
        List<StudentCourseEnrollmentDto> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);
        return ResponseEntity.ok(enrollments);
    }

    @PutMapping("/{enrollmentId}/progress")
    public ResponseEntity<StudentCourseEnrollmentDto> updateProgress(
            @PathVariable Long enrollmentId,
            @RequestBody ProgressUpdateDto progressUpdateDto) {
        StudentCourseEnrollmentDto updatedEnrollment = enrollmentService.updateEnrollmentProgress(enrollmentId, progressUpdateDto.progress());
        return ResponseEntity.ok(updatedEnrollment);
    }
}
