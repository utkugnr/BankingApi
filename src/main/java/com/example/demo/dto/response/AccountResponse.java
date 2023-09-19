package com.example.demo.dto.response;

import com.example.demo.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    Long id;
    String accountType;
    BigDecimal accountBalance;
    LocalDate creationDate;
    Long accountOwnerId;
    String accountOwnerName;

    public AccountResponse(Account entity){
        this.id = entity.getId();
        this.accountType = entity.getAccountType();
        this.accountBalance = entity.getAccountBalance();
        this.creationDate = entity.getCreationDate();
        this.accountOwnerId = entity.getCustomer().getId();
        this.accountOwnerName = entity.getCustomer().getCustomerName();
    }
}
