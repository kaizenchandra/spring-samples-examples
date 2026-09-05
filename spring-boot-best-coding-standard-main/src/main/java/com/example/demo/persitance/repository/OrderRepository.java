package com.example.demo.persitance.repository;

import com.example.demo.persitance.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
