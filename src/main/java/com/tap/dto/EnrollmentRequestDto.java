package com.tap.dto;

import java.util.UUID;

public record EnrollmentRequestDto(
        UUID studentId,
        UUID courseId
) {
}
