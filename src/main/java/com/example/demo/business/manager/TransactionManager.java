package com.example.demo.business.manager;

import com.example.demo.business.service.TransactionService;
import com.example.demo.entity.Transaction;
import com.example.demo.request.TransactionCreateRequest;

public class TransactionManager implements TransactionService {
    @Override
    public Transaction create(TransactionCreateRequest newTransactionRequest) {
        return null;
    }

    @Override
    public Transaction getOneTransaction(Long transactionId) {
        return null;
    }

    @Override
    public Transaction update(Long transactionId, Transaction newTransaction) {
        return null;
    }

    @Override
    public void delete(Long transactionId) {

    }
}
