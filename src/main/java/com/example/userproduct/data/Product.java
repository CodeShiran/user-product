package com.example.userproduct.data;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;

    @Column(nullable = false)
    private String product_name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;
}
