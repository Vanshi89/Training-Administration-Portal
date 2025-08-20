package com.tap.controllers;

import com.tap.dto.InstructorSkillDto;
import com.tap.services.InstructorSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/instructors/{instructorId}/skills")
public class InstructorSkillController {

    private final InstructorSkillService service;

    public InstructorSkillController(InstructorSkillService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InstructorSkillDto> addSkill(@PathVariable UUID instructorId,
                                                       @RequestBody InstructorSkillDto dto) {
        return ResponseEntity.ok(service.addSkill(instructorId, dto));
    }

    @GetMapping
    public ResponseEntity<List<InstructorSkillDto>> getSkills(@PathVariable UUID instructorId) {
        return ResponseEntity.ok(service.getSkillsByInstructor(instructorId));
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable UUID instructorId,
                                            @PathVariable Integer skillId) {
        service.deleteSkill(instructorId, skillId);
        return ResponseEntity.noContent().build();
    }
}