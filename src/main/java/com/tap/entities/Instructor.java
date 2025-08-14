package com.tap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Formula;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "instructors", schema = "tap_project")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {

    @Id
    @Column(name = "instructor_id")
    private UUID instructorId;


    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "instructor_id")
    private User user;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name ="last_name", nullable = false)
    private String lastName;

    @Column(name = "full_name", insertable = false)
    @Formula("first_name || ' ' || last_name")
    private String fullName;

    @Column(name = "is_verified")
    @ColumnDefault("false")
    private Boolean isVerified = false;

    // relationships
    // one-to-one relationship with the qualifications. one instructor will have one qualificaiton row
    @OneToOne(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private InstructorQualification qualification;

    // one-to-many relationship with skills, one instructor can have more than one skills
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<InstructorSkill> skills;

    // one-to-many relationship with bank details, one instructor can have more than one bank details
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<InstructorBankDetail> bankDetails;

    // one-to-many relationsh with earnings
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InstructorEarning> earnings;

    // one-to-many relationship with time sltos
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<InstructorTimeSlot> timeSlots;

    // one-to-many relationship with courses
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses;



}
