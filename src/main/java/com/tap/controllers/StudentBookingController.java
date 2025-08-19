package com.tap.controllers;

import com.tap.dto.BookingRequestDto;
import com.tap.dto.StudentBookingDto;
import com.tap.services.StudentBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class StudentBookingController {

    private final StudentBookingService bookingService;

    public StudentBookingController(StudentBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDto bookingRequest) {
        try {
            StudentBookingDto bookingDto = bookingService.createBooking(bookingRequest);
            return new ResponseEntity<>(bookingDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
