package com.example.footballMonk.repository;

import com.example.footballMonk.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart,String> {
}
