package com.example.demo.rest;

import com.example.demo.domain.Product;
import com.example.demo.service.Implementation.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductResource {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping(path = "/product")
    public List<Product> product(){
        List<Product> productList=productService.getAll();
        return productList;
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.save(product);
    }
}
