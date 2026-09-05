package com.example.demo.api.model.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderRequest(String orderNumber, LocalDateTime orderDate, BigDecimal totalAmount,String status) {
}
