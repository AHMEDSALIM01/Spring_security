package com.example.demo.service.Implementation;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final OrderItemService orderItemService;

    public Product save(Product product) {
        if(product == null || product.equals(new Product())){

            throw new IllegalStateException("there is no product to add !");
        }
        if(product.getQuantity() <= 0 ){
            throw new IllegalStateException("Quantity cannot be less than 1!");
        }

        return productRepository.save(product);

    }


    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

}
