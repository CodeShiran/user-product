package com.example.userproduct.repository;

import com.example.userproduct.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
