package com.tap.controllers;

import com.tap.dto.InstructorTimeSlotDto;
import com.tap.services.InstructorTimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/instructors/{instructorId}/slots")
public class InstructorTimeSlotController {

    @Autowired
    private InstructorTimeSlotService service;

    @PostMapping
    public ResponseEntity<InstructorTimeSlotDto> createSlot(
            @PathVariable UUID instructorId,
            @RequestBody InstructorTimeSlotDto dto) {
        return ResponseEntity.status(201).body(service.createSlot(instructorId, dto));
    }

    @GetMapping
    public ResponseEntity<List<InstructorTimeSlotDto>> getSlots(@PathVariable UUID instructorId) {
        return ResponseEntity.ok(service.getSlots(instructorId));
    }

    @PutMapping("/{slotId}")
    public ResponseEntity<InstructorTimeSlotDto> updateSlot(
            @PathVariable UUID instructorId,
            @PathVariable Integer slotId,
            @RequestBody InstructorTimeSlotDto dto) {
        return ResponseEntity.ok(service.updateSlot(instructorId, slotId, dto));
    }

    @DeleteMapping("/{slotId}")
    public ResponseEntity<Void> deleteSlot(@PathVariable Integer slotId) {
        service.deleteSlot(slotId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllSlots(@PathVariable UUID instructorId) {
        service.deleteAllSlots(instructorId);
        return ResponseEntity.ok("All slots deleted for instructor " + instructorId);
    }
}