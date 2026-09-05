package com.example.demo.service;

import com.example.demo.api.ex.OrderNotFoundException;
import com.example.demo.api.model.request.OrderRequest;
import com.example.demo.api.model.response.OrderResponse;
import com.example.demo.persitance.model.Order;
import com.example.demo.persitance.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public List<OrderResponse> fetchAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getOrderNumber(),
                        order.getOrderDate(),
                        order.getTotalAmount(), // This must be BigDecimal
                        order.getStatus()
                ))
                .toList();
    }
    @Override
    public OrderResponse addOrder(OrderRequest createOrderRequest) {
        Order order = Order.builder()
                .orderNumber(createOrderRequest.orderNumber())
                .orderDate(createOrderRequest.orderDate())
                .totalAmount(createOrderRequest.totalAmount())
                .build();
        Order saveOrder = orderRepository.save(order);
        return new OrderResponse(saveOrder.getId(), saveOrder.getOrderNumber(), saveOrder.getOrderDate(),saveOrder.getTotalAmount() ,saveOrder.getStatus());

    }
    @Override
    public OrderResponse modifyOrder(OrderRequest updatedOrderRequest, Long id) {
        Order order = getOrderOrElseThrow(id);
        order.setOrderNumber(updatedOrderRequest.orderNumber());
        order.setOrderDate(updatedOrderRequest.orderDate());
        order.setTotalAmount(updatedOrderRequest.totalAmount());
        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getOrderDate(), order.getTotalAmount(),order.getStatus());

    }
    @Override
    public OrderResponse fetchOrderById(Long id) {
        Order order = getOrderOrElseThrow(id);
        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getOrderDate(), order.getTotalAmount(),order.getStatus());

    }

    @Override
    public void removeOrderById(Long id) {
        Order order = getOrderOrElseThrow(id);
        orderRepository.delete(order);
    }
    @Override
    public OrderResponse modifyStatus(String status, Long id) {
        Order order = getOrderOrElseThrow(id);
        order.setStatus(status);
        orderRepository.save(order);
        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getOrderDate(), order.getTotalAmount(),order.getStatus());
    }

    private Order getOrderOrElseThrow(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }

}
