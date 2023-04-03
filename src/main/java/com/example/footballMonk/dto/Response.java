package com.example.footballMonk.dto;

import com.example.footballMonk.models.Product;
import lombok.Data;

import java.util.List;

@Data
public class Response {
    private String message;
    private boolean status;
    private Object object;
    private List<Product> products;
}
