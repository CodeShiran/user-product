package com.example.userproduct.service;

import com.example.userproduct.data.Product;
import com.example.userproduct.data.User;
import com.example.userproduct.repository.ProductRepository;
import com.example.userproduct.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public Product addProduct(Product product, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User " + userId + " not found."));

        product.setUser(user);
        return productRepository.save(product);
    }
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product " + id + " not found."));
    }

    public void deleteProduct(Long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }
}
