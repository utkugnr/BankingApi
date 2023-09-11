package com.example.demo.controller;

import com.example.demo.entity.Transaction;
import com.example.demo.request.TransactionCreateRequest;
import com.example.demo.request.TransactionUpdateRequest;
import com.example.demo.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping
    public Transaction createOneTransaction(@RequestBody TransactionCreateRequest newTransactionRequest) {
        return transactionService.create(newTransactionRequest);
    }
    @GetMapping
    public List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
    @GetMapping("/sender/{accountId}")
    public List<Transaction> getAllTransactionsBySenderAccountId(@PathVariable Long accountId){
        return transactionService.getAllTransactionsBySenderAccountId(accountId);
    }
    @GetMapping("/receiver/{accountId}")
    public List<Transaction> getAllTransactionsByReceiverAccountId(@PathVariable Long accountId){
        return transactionService.getAllTransactionsByReceiverAccountId(accountId);
    }
    @GetMapping("/{transactionId}")
    public Transaction getOneTransaction(@PathVariable Long transactionId){
        return transactionService.getOneTransaction(transactionId);
    }
    @PutMapping("/{transactionId}")
    public Transaction updateOneTransaction(@PathVariable Long transactionId , @RequestBody TransactionUpdateRequest updateTransaction){
        return transactionService.update(transactionId,updateTransaction);
    }
    @DeleteMapping("/{transactionId}")
    public void deleteOneTransaction(@PathVariable Long transactionId){
        transactionService.delete(transactionId);
    }
}
