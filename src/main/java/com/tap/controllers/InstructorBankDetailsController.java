package com.tap.controllers;

import com.tap.dto.InstructorBankDetailCreationDto;
import com.tap.dto.InstructorBankDetailDto;
import com.tap.services.InstructorBankDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/instructors/{instructorId}/bank-details")
public class InstructorBankDetailsController {

    private final InstructorBankDetailsService service;

    public InstructorBankDetailsController(InstructorBankDetailsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<InstructorBankDetailDto>> getBankDetails(@PathVariable UUID instructorId) {
        return ResponseEntity.ok(service.getBankDetailsByInstructorId(instructorId));
    }

    @PostMapping
    public ResponseEntity<InstructorBankDetailDto> addBankDetail(@PathVariable UUID instructorId,
                                                                 @RequestBody InstructorBankDetailCreationDto dto) {
        return ResponseEntity.ok(service.addBankDetail(instructorId, dto));
    }

    @PutMapping("/{bankDetailId}")
    public ResponseEntity<InstructorBankDetailDto> updateBankDetail(@PathVariable UUID instructorId,
                                                                    @PathVariable Integer bankDetailId,
                                                                    @RequestBody InstructorBankDetailCreationDto dto) {
        return ResponseEntity.ok(service.updateBankDetail(bankDetailId, dto));
    }

    @DeleteMapping("/{bankDetailId}")
    public ResponseEntity<Void> deleteBankDetail(@PathVariable UUID instructorId,
                                                 @PathVariable Integer bankDetailId) {
        service.deleteBankDetail(bankDetailId);
        return ResponseEntity.noContent().build();
    }
}