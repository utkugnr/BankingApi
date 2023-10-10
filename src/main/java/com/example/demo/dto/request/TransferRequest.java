package com.example.demo.dto.request;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    BigDecimal transferAmount;
    String transactionType;
    LocalDateTime transactionTime;
    Long senderAccountId;
    Long receiverAccountId;
}
