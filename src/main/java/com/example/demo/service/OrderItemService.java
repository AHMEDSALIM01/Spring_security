package com.example.demo.service;

import com.example.demo.domain.OrderItem;
import com.example.demo.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    public OrderItem save( OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
}
