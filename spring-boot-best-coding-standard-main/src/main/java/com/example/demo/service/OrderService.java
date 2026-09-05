package com.example.demo.service;

import com.example.demo.api.model.request.OrderRequest;
import com.example.demo.api.model.response.OrderResponse;
import com.example.demo.persitance.model.Order;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderService {

     List<OrderResponse> fetchAllOrders();
    OrderResponse addOrder(OrderRequest order);
    OrderResponse modifyOrder(OrderRequest order,Long id);
    OrderResponse fetchOrderById(Long id);
    void removeOrderById(Long id);
    OrderResponse modifyStatus(String status,Long id);

}
