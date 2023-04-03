package com.example.footballMonk.services.customer;

import com.example.footballMonk.exception.customer.CustomerAlreadyExistsException;
import com.example.footballMonk.exception.customer.CustomerNotExistsException;
import com.example.footballMonk.exception.customer.InvalidPassword;
import com.example.footballMonk.models.Cart;
import com.example.footballMonk.models.Customer;
import com.example.footballMonk.repository.CartRepository;
import com.example.footballMonk.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Customer createCustomer(Customer customer) throws CustomerAlreadyExistsException {
        Criteria criteria = Criteria.where("email").is(customer.getEmail());
        Query query = new Query(criteria);
        if (mongoTemplate.exists(query, Customer.class)) {
            throw new CustomerAlreadyExistsException("Customer with this email already exists.");
        } else {
            Cart cart = new Cart();
            cart = cartRepository.save(cart);
            Customer customer1 = Customer.builder()
                    .email(customer.getEmail())
                    .cart(cart)
                    .password(passwordEncoder.encode(customer.getPassword())).build();
            return mongoTemplate.save(customer1);
        }
    }

    @Override
    public Customer loginCustomer(Customer customer) throws CustomerNotExistsException, InvalidPassword {
        Criteria criteria = Criteria.where("email").is(customer.getEmail());
        Query query = new Query(criteria);
        if (mongoTemplate.exists(query, Customer.class)) {
            Customer customer1 =  mongoTemplate.findOne(query, Customer.class);
            if (passwordEncoder.matches(customer.getPassword(),customer1.getPassword())){
                return customer1;
            }else {
                throw new InvalidPassword("Incorrect password");
            }
        } else {
            throw new CustomerNotExistsException("Customer with this " + customer.getEmail() + " doesn't exists.");
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) throws CustomerNotExistsException {
        Criteria criteria = Criteria.where("email").is(customer.getEmail());
        Query query = new Query(criteria);
        if (mongoTemplate.exists(query, Customer.class)) {
            Customer customer1 = mongoTemplate.findOne(query, Customer.class);
            customer1 = updateCustomerDetails(customer1, customer);
            return mongoTemplate.save(customer1);
        } else {
            throw new CustomerNotExistsException("Customer does not exists with this email");
        }
    }


    public Customer updateCustomerDetails(Customer customer, Customer updatedCustomer) {
        customer.setFName(updatedCustomer.getFName());
        customer.setLName(updatedCustomer.getLName());
        customer.setAge(updatedCustomer.getAge());
        customer.setAddress(updatedCustomer.getAddress());
        customer.setDob(updatedCustomer.getDob());

        return customer;
    }
}
