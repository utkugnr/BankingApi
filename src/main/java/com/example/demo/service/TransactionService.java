package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.request.TransactionCreateRequest;
import com.example.demo.request.TransactionUpdateRequest;
import com.example.demo.response.TransactionResponse;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private AccountService accountService;
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService=accountService;
    }
    @Transactional
    public Transaction create(TransactionCreateRequest newTransactionRequest) throws InsufficientResourcesException {
        Account senderAccount = accountService.getOneAccount(newTransactionRequest.getSenderAccountId());
        Account receiverAccount = accountService.getOneAccount(newTransactionRequest.getReceiverAccountId());
        if(senderAccount.getAccountBalance().compareTo(newTransactionRequest.getTransferAmount())<0)
            throw new InsufficientResourcesException("Insufficient balance");
        else{
            Transaction toSave = new Transaction();
            toSave.setTransferAmount(newTransactionRequest.getTransferAmount());
            toSave.setTransactionType(newTransactionRequest.getTransactionType());
            toSave.setTransactionTime(newTransactionRequest.getTransactionTime());
            toSave.setSenderAccount(senderAccount);
            toSave.setReceiverAccount(receiverAccount);
            toSave = transactionRepository.save(toSave);

            accountService.withdraw(senderAccount.getId(),newTransactionRequest.getTransferAmount());
            accountService.deposit(receiverAccount.getId(),newTransactionRequest.getTransferAmount());
            return toSave;
        }
    }
    public List<TransactionResponse> getAllTransactions(){
        List<Transaction> list;
        list = transactionRepository.findAll();
       return list.stream().map(transaction -> new TransactionResponse(transaction)).collect(Collectors.toList());
    }
    public List<TransactionResponse> getOneTransaction(Long transactionId) {
        Optional<Transaction> list;
        list = transactionRepository.findById(transactionId);
        return list.stream().map(transaction -> new TransactionResponse(transaction)).collect(Collectors.toList());
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

    public List<TransactionResponse> getAllTransactionsBySenderAccountId(Long accountId) {
        List<Transaction> list;
        list = transactionRepository.findBySenderAccountId(accountId);
        return list.stream().map(transaction -> new TransactionResponse(transaction)).collect(Collectors.toList());
    }
    public List<TransactionResponse> getAllTransactionsByReceiverAccountId(Long accountId) {
        List<Transaction> list;
        list = transactionRepository.findByReceiverAccountId(accountId);
        return list.stream().map(transaction -> new TransactionResponse(transaction)).collect(Collectors.toList());
    }
}
