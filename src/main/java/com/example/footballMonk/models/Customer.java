package com.example.footballMonk.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
@Builder
public class Customer {
    @Id
    private String id;
    @Field(value = "f_name")
    private String fName;
    @Field(value = "l_name")
    private String lName;
    private String address;
    private String email;
    private String password;
    private Date dob;
    private int age;
    @DBRef
    private Cart cart;
    @DBRef
    private List<Order> orders;
}
