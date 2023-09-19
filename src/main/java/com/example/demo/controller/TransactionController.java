package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.dto.request.TransactionCreateRequest;
import com.example.demo.dto.request.TransactionUpdateRequest;
import com.example.demo.dto.response.TransactionResponse;
import com.example.demo.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import javax.naming.InsufficientResourcesException;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping
    public Transaction createOneTransaction(@RequestBody TransactionCreateRequest newTransactionRequest) throws InsufficientResourcesException {
        return transactionService.create(newTransactionRequest);
    }
    @GetMapping("/history")
    public List<TransactionResponse> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
    @GetMapping("/history/sender/{senderAccountId}")
    public List<TransactionResponse> getAllTransactionsBySenderAccountId(@PathVariable Long senderAccountId){
        return transactionService.getAllTransactionsBySenderAccountId(senderAccountId);
    }
    @GetMapping("/history/receiver/{receiverAccountId}")
    public List<TransactionResponse> getAllTransactionsByReceiverAccountId(@PathVariable Long receiverAccountId){
        return transactionService.getAllTransactionsByReceiverAccountId(receiverAccountId);
    }
    @GetMapping("/history/{transactionId}")
    public List<TransactionResponse> getOneTransaction(@PathVariable Long transactionId){
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
