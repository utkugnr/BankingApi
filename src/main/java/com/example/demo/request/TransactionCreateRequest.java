package com.example.demo.request;

import com.example.demo.entity.Account;
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
