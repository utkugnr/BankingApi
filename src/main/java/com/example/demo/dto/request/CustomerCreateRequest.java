package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateRequest {
    String customerName;
    String customerEmail;
    String customerPhone;
    String customerAddress;
}
