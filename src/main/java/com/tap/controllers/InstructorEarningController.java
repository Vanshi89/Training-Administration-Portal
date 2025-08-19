package com.tap.controllers;

import com.tap.dto.InstructorEarningCreationDto;
import com.tap.dto.InstructorEarningDto;
import com.tap.services.InstructorEarningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/instructors/{instructorId}/earnings")
public class InstructorEarningController {

    private final InstructorEarningService service;

    public InstructorEarningController(InstructorEarningService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<InstructorEarningDto> createEarning(@PathVariable UUID instructorId,
                                                              @RequestBody InstructorEarningCreationDto dto) {
        return ResponseEntity.ok(service.createEarning(instructorId, dto));
    }

    @GetMapping
    public ResponseEntity<List<InstructorEarningDto>> getEarningsByInstructor(@PathVariable UUID instructorId) {
        return ResponseEntity.ok(service.getEarningsByInstructor(instructorId));
    }

//    @GetMapping
//    public ResponseEntity<List<InstructorEarningDto>> list(@PathVariable UUID instructorId) {
//        return ResponseEntity.ok(service.getEarningsByInstructor(instructorId));
//    }

    @PutMapping
    public ResponseEntity<InstructorEarningDto> updateEarning(@PathVariable UUID instructorId,
                                                              @RequestBody InstructorEarningCreationDto dto) {
        return ResponseEntity.ok(service.updateEarningByInstructor(instructorId, dto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEarning(@PathVariable UUID instructorId,
                                              @RequestBody InstructorEarningCreationDto dto) {
        service.deleteEarningByInstructor(instructorId, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{earningId}")
    public ResponseEntity<Void> delete(@PathVariable UUID instructorId, @PathVariable Integer earningId) {
        service.deleteEarning(earningId);
        return ResponseEntity.noContent().build();
    }
}