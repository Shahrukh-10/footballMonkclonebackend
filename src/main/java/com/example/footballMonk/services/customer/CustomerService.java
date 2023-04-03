package com.example.footballMonk.services.customer;

import com.example.footballMonk.exception.customer.CustomerAlreadyExistsException;
import com.example.footballMonk.exception.customer.CustomerNotExistsException;
import com.example.footballMonk.exception.customer.InvalidPassword;
import com.example.footballMonk.models.Customer;

public interface CustomerService {

    public Customer createCustomer(Customer customer) throws CustomerAlreadyExistsException ;

    public Customer loginCustomer(Customer customer) throws CustomerNotExistsException, InvalidPassword;
    public Customer updateCustomer(Customer customer) throws CustomerNotExistsException;


}
