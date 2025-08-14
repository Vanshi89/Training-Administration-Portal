package com.tap.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="instructorResumes",schema="tap_project")
public class InstructorResume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resumeId;

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;


    @Column(name="resume_url",nullable = false,length=255,unique = true)
    private String resumeUrl;

    @Column(name="uploaded_at",nullable = false)
    private LocalDateTime uploadedAt;

}


