package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateRequest {
    String accountType;
    BigDecimal accountBalance;
    LocalDate creationDate;
    Long customerId;
}
