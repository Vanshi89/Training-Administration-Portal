package com.tap.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class StudentPaymentDto {
    private Integer paymentId;
    private UUID student;
    private Integer studentBankDetails;
    private BigDecimal amount;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime paidAt;
    private String notes;
}
