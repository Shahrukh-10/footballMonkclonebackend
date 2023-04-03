package com.example.footballMonk.services.product;

import com.example.footballMonk.exception.product.ProductExistException;
import com.example.footballMonk.exception.product.ProductNotFoundException;
import com.example.footballMonk.models.Product;

import java.util.List;

public interface ProductService {
    public boolean addProduct(Product product) throws ProductExistException;
    public boolean updateProduct(Product product) throws ProductExistException, ProductNotFoundException;
}
