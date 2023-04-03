package com.example.footballMonk.repository;

import com.example.footballMonk.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
