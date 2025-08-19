package com.tap.services;

import com.tap.dto.BookingRequestDto;
import com.tap.dto.StudentBookingDto;
import com.tap.entities.Instructor;
import com.tap.entities.InstructorTimeSlot;
import com.tap.entities.Student;
import com.tap.entities.StudentBooking;
import com.tap.exceptions.ResourceNotFoundException;
import com.tap.mappers.UserMapper;
import com.tap.repositories.InstructorRepository;
import com.tap.repositories.InstructorTimeSlotRepository;
import com.tap.repositories.StudentBookingRepository;
import com.tap.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentBookingService {

    private final StudentBookingRepository bookingRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final InstructorTimeSlotRepository timeSlotRepository;
    private final UserMapper userMapper;

    public StudentBookingService(StudentBookingRepository bookingRepository, StudentRepository studentRepository,
                                 InstructorRepository instructorRepository, InstructorTimeSlotRepository timeSlotRepository,
                                 UserMapper userMapper) {
        this.bookingRepository = bookingRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public StudentBookingDto createBooking(BookingRequestDto bookingRequest) {
        Student student = studentRepository.findById(bookingRequest.studentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Instructor instructor = instructorRepository.findById(bookingRequest.instructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));
        InstructorTimeSlot timeSlot = timeSlotRepository.findById(bookingRequest.timeSlotId())
                .orElseThrow(() -> new ResourceNotFoundException("Time slot not found"));

        if (timeSlot.getIsBooked()) {
            throw new IllegalStateException("Time slot is not available");
        }

        StudentBooking booking = new StudentBooking();
        booking.setStudent(student);
        booking.setInstructor(instructor);
        booking.setSlot(timeSlot);
        booking.setStatus("CONFIRMED");

        timeSlot.setIsBooked(true);
        timeSlotRepository.save(timeSlot);

        StudentBooking savedBooking = bookingRepository.save(booking);
        return userMapper.toStudentBookingDto(savedBooking);
    }
}
