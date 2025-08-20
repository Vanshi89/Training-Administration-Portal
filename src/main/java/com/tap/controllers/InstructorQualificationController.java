package com.tap.controllers;

import com.tap.dto.InstructorQualificationDto;
import com.tap.services.InstructorQualificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.tap.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/instructors/{instructorId}/qualification")
public class InstructorQualificationController {

    private final InstructorQualificationService service;

    public InstructorQualificationController(InstructorQualificationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InstructorQualificationDto> createOrUpdate(@PathVariable UUID instructorId,
                                                                     @RequestBody InstructorQualificationDto dto) {
        return ResponseEntity.ok(service.createOrUpdate(instructorId, dto));
    }

    @PutMapping
    public ResponseEntity<?> update(@PathVariable UUID instructorId,
                                    @RequestBody InstructorQualificationDto dto) {
        try {
            return ResponseEntity.ok(service.updateQualification(instructorId, dto));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<InstructorQualificationDto> getQualification(@PathVariable UUID instructorId) {
        return ResponseEntity.ok(service.getByInstructor(instructorId));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteQualification(@PathVariable UUID instructorId) {
        service.deleteByInstructor(instructorId);
        return ResponseEntity.noContent().build();
    }
}