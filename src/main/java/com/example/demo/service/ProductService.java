package com.example.demo.service;

import com.example.demo.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
     Product save(Product product);
     Optional<Product> getById(Long id);
     List<Product> getAll() ;
}
