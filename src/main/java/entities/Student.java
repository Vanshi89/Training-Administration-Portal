package entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "students", schema = "tap_project")
@Getter
@Setter
public class Student {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID studentId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "student_id")
    private User user;

    @Column(name = "first_name",nullable = false,length = 50)
    private String firstName;

    @Column(name = "last_name",nullable = false,length = 50)
    private String lastName;

    @Generated
    @Formula("first_name || ' ' || last_name")
    @Column(name = "full_name",insertable = false,updatable = false)
    private String fullName;

    @Column(name = "age")
    private Integer age;

    @Column(nullable = false, unique = true ,length = 10)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 70)
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email cannot be blank")
    private String emailId;

    @Column(nullable = false, unique = true)
    private Boolean isVerified = true;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentBooking> bookings;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentPayment> payments;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentCourseEnrollment> enrollments;
}
