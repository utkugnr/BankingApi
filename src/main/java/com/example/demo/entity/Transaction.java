package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "transaction_id")
   private Long id;
   @Column(name = "transfer_amount")
   private BigDecimal transferAmount;
   @Column(name = "transaction_type")
   private String transactionType;
   @Column(name = "transaction_time")
   private LocalDateTime transactionTime;

   @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
   @JoinColumn(name = "sender_account_id",nullable = false)
   Account senderAccount;

   @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
   @JoinColumn(name = "receiver_account_id",nullable = false)
   Account receiverAccount;


}
