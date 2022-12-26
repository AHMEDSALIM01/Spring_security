package com.example.demo.repository;


import com.example.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Override
    default void deleteById(Long aLong) {

    }
}
