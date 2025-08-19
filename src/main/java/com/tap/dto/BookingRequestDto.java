package com.tap.dto;

import java.util.UUID;

public record BookingRequestDto(
        UUID studentId,
        UUID instructorId,
        Integer timeSlotId,
        UUID courseId
) {}
