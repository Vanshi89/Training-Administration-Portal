package com.tap.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.Formula;

import java.util.UUID;

@Entity
@Table(name="students",schema="tap_project")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id_id", updatable = false, nullable = false)
    private UUID StudentId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "student_id")
    @NotNull(message = "User reference is required")
    private User user;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "first name is required")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Formula("first_name || ' ' || last_name")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 0, message = "Age must be non-negative")
    @Max(value = 120, message = "Age must be realistic")
    @Column(name = "age")
    private Integer age;

    @Column(name = "phone_number", nullable = false, unique = true)
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified = true;

}
