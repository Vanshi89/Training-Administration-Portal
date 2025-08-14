package com.tap.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name="instrustorTimeSlots",schema="tap_project")
public class InstructorTimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer slotId;

    @ManyToOne
    @JoinColumn(name="instructor_id",nullable = false)
    private Instructor instructor;

    @Column(name="start_time",nullable = false)
    @NotNull(message="Strat time is required")
    private LocalDateTime startTime;

    @Column(name="end_time",nullable = false)
    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    @Column(name="is_booked",nullable = false)
    private Boolean isBooked=false;


}
