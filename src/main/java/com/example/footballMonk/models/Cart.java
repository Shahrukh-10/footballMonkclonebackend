package com.example.footballMonk.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "cart")
public class Cart {

    @Id
    private String id;
    private List<Product> products;
    @Field("total_price")
    private int totalPrice;
}
