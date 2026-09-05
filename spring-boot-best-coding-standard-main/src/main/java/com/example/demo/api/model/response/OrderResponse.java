package com.example.demo.api.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse( Long id,String orderNumber,LocalDateTime orderDate,BigDecimal totalAmount,String status) {
}
