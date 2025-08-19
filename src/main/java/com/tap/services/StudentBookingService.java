package com.tap.services;

import com.tap.dto.BookingRequestDto;
import com.tap.dto.StudentBookingDto;
import com.tap.entities.*;
import com.tap.exceptions.ResourceNotFoundException;
import com.tap.mappers.UserMapper;
import com.tap.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentBookingService {

    private final StudentBookingRepository bookingRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final InstructorTimeSlotRepository timeSlotRepository;
    private final UserMapper userMapper;

    private final CourseRepository courseRepository;
    private final StudentCourseEnrollmentRepository enrollmentRepository;

    public StudentBookingService(StudentBookingRepository bookingRepository, StudentRepository studentRepository,
                                 InstructorRepository instructorRepository, InstructorTimeSlotRepository timeSlotRepository,
                                 UserMapper userMapper, CourseRepository courseRepository,
                                 StudentCourseEnrollmentRepository enrollmentRepository) {
        this.bookingRepository = bookingRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.userMapper = userMapper;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public StudentBookingDto createBooking(BookingRequestDto bookingRequest) {
        Student student = studentRepository.findById(bookingRequest.studentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Instructor instructor = instructorRepository.findById(bookingRequest.instructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));
    InstructorTimeSlot timeSlot = timeSlotRepository.findById(bookingRequest.timeSlotId())
                .orElseThrow(() -> new ResourceNotFoundException("Time slot not found"));

    // Fetch and validate course
    Course course = courseRepository.findById(bookingRequest.courseId())
        .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    if (!course.getInstructor().getUserId().equals(instructor.getUserId())) {
        throw new IllegalArgumentException("Course does not belong to instructor");
    }

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

        // Auto-enroll student if not already enrolled in course
        boolean enrolled = enrollmentRepository.existsByStudent_UserIdAndCourse_CourseId(student.getUserId(), course.getCourseId());
        if (!enrolled) {
            StudentCourseEnrollment enrollment = new StudentCourseEnrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setStatus("ENROLLED");
            enrollment.setProgress(java.math.BigDecimal.ZERO);
            enrollmentRepository.save(enrollment);
        }
        return userMapper.toStudentBookingDto(savedBooking);
    }
}
