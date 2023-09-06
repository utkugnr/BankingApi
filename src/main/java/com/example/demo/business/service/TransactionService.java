package com.example.demo.business.service;

import com.example.demo.entity.Transaction;
import com.example.demo.request.TransactionCreateRequest;

public interface TransactionService {
    Transaction create(TransactionCreateRequest newTransactionRequest);
    Transaction getOneTransaction(Long transactionId);
    Transaction update(Long transactionId , Transaction newTransaction);
    void delete(Long transactionId);

}
