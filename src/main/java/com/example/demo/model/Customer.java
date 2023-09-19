package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "customer_id")
   private Long id;
   @Column(name = "customer_name")
   private String customerName;
   @Column(name = "customer_email")
   private String customerEmail;
   @Column(name = "customer_phone")
   private String customerPhone;
   @Column(name = "customer_address")
   private String customerAddress;

}
