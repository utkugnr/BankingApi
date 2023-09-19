package com.example.demo.dto.response;

import com.example.demo.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    Long id;
    BigDecimal transferAmount;
    String transactionType;
    Long senderAccountId;
    Long receiverAccountId;

    public TransactionResponse(Transaction entity){
        this.id=entity.getId();
        this.transferAmount=entity.getTransferAmount();
        this.transactionType=entity.getTransactionType();
        this.senderAccountId=entity.getSenderAccount().getId();
        this.receiverAccountId=entity.getReceiverAccount().getId();
    }
}
