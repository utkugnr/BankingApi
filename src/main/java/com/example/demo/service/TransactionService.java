package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.request.TransactionCreateRequest;
import com.example.demo.request.TransactionUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public Transaction create(TransactionCreateRequest newTransactionRequest) {
        if(Objects.isNull(newTransactionRequest))
            return null;
        else{
            Transaction toSave = new Transaction();
            toSave.setTransferAmount(newTransactionRequest.getTransferAmount());
            toSave.setTransactionType(newTransactionRequest.getTransactionType());
            toSave.setTransactionTime(newTransactionRequest.getTransactionTime());
            return transactionRepository.save(toSave);
        }
    }
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
    public Transaction getOneTransaction(Long transactionId) {
        return transactionRepository.getById(transactionId);
    }
    public Transaction update(Long transactionId, TransactionUpdateRequest updateTransaction) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isPresent()){
            Transaction foundTransaction = transaction.get();
            foundTransaction.setTransferAmount(updateTransaction.getTransferAmount());
            foundTransaction.setTransactionType(updateTransaction.getTransactionType());
            foundTransaction.setTransactionTime(updateTransaction.getTransactionTime());
            transactionRepository.save(foundTransaction);
            return foundTransaction;
        }
        else
            return null;
    }
    public void delete(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    public List<Transaction> getAllTransactionsBySenderAccountId(Long accountId) {
        return transactionRepository.findBySenderAccountId(accountId);
    }
    public List<Transaction> getAllTransactionsByReceiverAccountId(Long accountId) {
        return transactionRepository.findByReceiverAccountId(accountId);
    }
}
