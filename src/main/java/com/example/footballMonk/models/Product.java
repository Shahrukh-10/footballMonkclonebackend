package com.example.footballMonk.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String customerId;
    private String productName;
    private String productImageUrl;
    private int price;
    private String description;
    private int quantity = 100;
    private boolean stock = quantity > 0;

}
