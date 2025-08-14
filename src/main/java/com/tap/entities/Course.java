package com.tap.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="courses",schema="tap_project")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="course_id", updatable = false,nullable = false)
    private UUID courseId;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="instructor_id",nullable = false)
    @NotNull(message="Instructor is required")
    private Instructor instuctor;

    @Column(name="title",nullable = false)
    @NotBlank(message = "Course title is required")
    private String title;

    @Column(name="description",length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private InstructorSkill skill;

    @Column(name="price",precision = 10,scale =2)
    @DecimalMin(value="0.0",inclusive = false,message = "price must be greater than zero")
    private BigDecimal price;

    @Column(name="duration")
    private Duration duration;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="level_id")
    private ProficiencyLevel level;

    @Column(name="created_at",nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name="is_published",nullable = false)
    private Boolean IsPublished=false;

}
