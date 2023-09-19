package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateRequest {
    BigDecimal transferAmount;
    String transactionType;
    LocalDateTime transactionTime;
    Long senderAccountId;
    Long receiverAccountId;
}
