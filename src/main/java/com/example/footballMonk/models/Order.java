package com.example.footballMonk.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Document(value = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private List<Product> products;
    @CreatedDate
    @Field(value = "order_created_date")
    private Date orderCreated;
    @Field("total_price")
    private int totalPrice;
    private String status;
}
