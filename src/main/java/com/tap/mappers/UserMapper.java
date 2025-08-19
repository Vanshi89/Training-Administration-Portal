package com.tap.mappers;

import com.tap.dto.*;
import com.tap.entities.*;
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

    public StudentPreferenceDto toStudentPreferenceDto(StudentPreference preference) {
        StudentPreferenceDto dto = new StudentPreferenceDto();
        dto.setPreferenceId(preference.getPreferenceId());
        dto.setStudentId(preference.getStudent().getUserId());
        dto.setRequiredSkills(preference.getRequiredSkills());
        dto.setPreferredTimeSlots(preference.getPreferredTimeSlots());
        dto.setPreferredStartTime(preference.getPreferredStartTime());
        dto.setPreferredEndTime(preference.getPreferredEndTime());
        return dto;
    }

    public StudentBankDetailsDto tostudentBankDetailsDto(StudentBankDetails bankDetails){
        StudentBankDetailsDto dto = new StudentBankDetailsDto();
        dto.setBankDetailId(bankDetails.getBankDetailId());
        dto.setStudentId(bankDetails.getStudent().getUserId());
        dto.setAccountHolderName(bankDetails.getAccountHolderName());
        dto.setBankName(bankDetails.getBankName());
        dto.setAccountNumber(bankDetails.getAccountNumber());
        dto.setIfscCode(bankDetails.getIfscCode());
        dto.setAccountType(bankDetails.getAccountType());
        dto.setBranchName(bankDetails.getBranchName());
        dto.setCreatedAt(bankDetails.getCreatedAt());

        return dto;
    }

    public StudentPaymentDto tostudentPaymentDto(StudentPayment payment){
        StudentPaymentDto dto = new StudentPaymentDto();
        dto.setPaymentId(payment.getPaymentId());
        dto.setStudent(payment.getStudent().getUserId());
        if(payment.getCourse() != null){
            dto.setCourseId(payment.getCourse().getCourseId());
        }
        dto.setStudentBankDetails(
                payment.getStudentBankDetails() != null ? payment.getStudentBankDetails().getBankDetailId() : null
        );
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setPaymentStatus(payment.getPaymentStatus());
        dto.setPaidAt(payment.getPaidAt());
        dto.setNotes(payment.getNotes());
        return dto;
    }

    public CourseDto toCourseDto(Course course) {
        return new CourseDto(
                course.getCourseId(),
                course.getInstructor().getUserId(),
                course.getTitle(),
                course.getDescription(),
                course.getSkill() != null ? course.getSkill().getInstructorSkillId() : null,
                course.getSkill() != null ? course.getSkill().getSkillName() : null,
                course.getPrice(),
                course.getDuration(),
                course.getLevel() != null ? course.getLevel().getLevelId() : null,
                course.getLevel() != null ? course.getLevel().getLevelName().name() : null,
                course.getCreatedAt(),
                course.getUpdatedAt(),
                course.getIsPublished()
        );
    }

    public StudentCourseEnrollmentDto toStudentCourseEnrollmentDto(StudentCourseEnrollment enrollment) {
        return new StudentCourseEnrollmentDto(
                enrollment.getEnrollmentId(),
                enrollment.getStudent().getUserId(),
                enrollment.getStudent().getFullName(),
                enrollment.getCourse().getCourseId(),
                enrollment.getCourse().getTitle(),
                enrollment.getStatus(),
                enrollment.getProgress(),
                enrollment.getEnrolledAt()
        );
    }

    public StudentBookingDto toStudentBookingDto(StudentBooking booking) {
        return new StudentBookingDto(
                booking.getBookingId(),
                booking.getStudent().getUserId(),
                booking.getStudent().getFullName(),
                booking.getInstructor().getUserId(),
                booking.getInstructor().getFullName(),
                booking.getSlot().getSlotId(),
                booking.getSlot().getStartTime(),
                booking.getSlot().getEndTime(),
                booking.getStatus(),
                booking.getBookedAt()
        );
    }

    public InstructorEarningDto toInstructorEarningDto(InstructorEarning earning) {
        InstructorEarningDto dto = new InstructorEarningDto();
        dto.setEarningId(earning.getEarningId());
        dto.setInstructorId(earning.getInstructor().getUserId());
        if(earning.getStudent()!=null){
            dto.setStudentId(earning.getStudent().getUserId());
        }
        if(earning.getBankDetail()!=null){
            dto.setBankDetailId(earning.getBankDetail().getBankDetailId());
        }
        dto.setAmount(earning.getAmount());
        dto.setPaymentMethod(earning.getPaymentMethod());
        dto.setNotes(earning.getNotes());
        dto.setReceivedAt(earning.getReceivedAt());
        return dto;
    }
}
