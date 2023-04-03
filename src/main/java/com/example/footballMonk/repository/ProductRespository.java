package com.example.footballMonk.repository;

import com.example.footballMonk.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRespository extends MongoRepository<Product,String> {

}
