package com.example.userproduct.controller;

import com.example.userproduct.data.Product;
import com.example.userproduct.data.User;
import com.example.userproduct.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Product savedProduct = productService.addProduct(product, user.getUser_id());
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Product existingProduct = productService.getProduct(id);
        if (existingProduct.getUser().getUser_id() != user.getUser_id()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // Update only non-null fields
        if (product.getProduct_name() != null) existingProduct.setProduct_name(product.getProduct_name());
        if (product.getPrice() != null) existingProduct.setPrice(product.getPrice());
        // Add similar checks for other fields

        Product updatedProduct = productService.addProduct(existingProduct, user.getUser_id());
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("Unauthorized access");
        }
        Product product = productService.getProduct(id);
        if (product.getUser().getUser_id() != user.getUser_id()) {
            throw new RuntimeException("Forbidden access to delete this product");
        }
        productService.deleteProduct(id);
    }
}
