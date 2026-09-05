package com.example.demo.controller;

import com.example.demo.api.model.APIResponse;
import com.example.demo.api.model.request.OrderRequest;
import com.example.demo.api.model.request.ProductRequest;
import com.example.demo.api.model.response.OrderResponse;
import com.example.demo.api.model.response.ProductResponse;
import com.example.demo.persitance.model.Order;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//sonali
/*
    controller: get, get by id, add, update, delete, patch
    service: get, get by id, add, update, delete, patch
    repository: get, get by id, add, update, delete, patch
    entity:
    model: request, response
 */
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<APIResponse<List<OrderResponse>>> getAllOrder(){
        List<OrderResponse> allOrder = orderService.fetchAllOrders();
       return ResponseEntity.ok(new APIResponse<>(allOrder,"success",HttpStatus.OK.value()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<OrderResponse>> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.addOrder(orderRequest);
        return new ResponseEntity<>(new APIResponse<>(orderResponse, "Success", HttpStatus.CREATED.value()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<OrderResponse>> getOrderById(@PathVariable Long id) {
        OrderResponse orderResponse = orderService.fetchOrderById(id);
        return new ResponseEntity<>(new APIResponse<>(orderResponse, "Success", HttpStatus.OK.value()), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.removeOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse<OrderResponse>> updateOrder(@RequestBody OrderRequest orderRequest, @PathVariable Long id) {
        OrderResponse orderResponse = orderService.modifyOrder(orderRequest,id);
        return  ResponseEntity.ok(new APIResponse<>(orderResponse, "Success", HttpStatus.OK.value()));
    }

    @PatchMapping(value = "/{id}/{status}")
    public ResponseEntity<APIResponse<OrderResponse>> updateStatus(@PathVariable String status, @PathVariable Long id) {
        OrderResponse orderResponse = orderService.modifyStatus(status, id);
        return  ResponseEntity.ok(new APIResponse<>(orderResponse, "Success", HttpStatus.OK.value()));
    }
}
