package com.example.footballMonk.controller.customer;

import com.example.footballMonk.dto.Response;
import com.example.footballMonk.exception.customer.CustomerAlreadyExistsException;
import com.example.footballMonk.exception.customer.CustomerNotExistsException;
import com.example.footballMonk.exception.customer.InvalidPassword;
import com.example.footballMonk.models.Customer;
import com.example.footballMonk.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @PostMapping("/createAccount")
    public ResponseEntity<Response> createCustomer(@RequestBody Customer customer) {
        Response response = new Response();
        try {
            Customer customer1 = customerService.createCustomer(customer);
            response.setObject(customer1);
            response.setStatus(true);
            response.setMessage("account_created");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (CustomerAlreadyExistsException e) {
            response.setMessage("user_exists_with_this_email");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("internal_server_error");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/loginAccount")
    public ResponseEntity<Response> loginCustomer(@RequestBody Customer customer) {
        Response response = new Response();
        try {
            Customer customer1 = customerService.loginCustomer(customer);
            response.setObject(customer1);
            response.setStatus(true);
            response.setMessage("logged_in");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (InvalidPassword e) {
            response.setMessage("invalid_password");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CustomerNotExistsException e) {
            response.setMessage("user_not_exist");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("internal_server_error");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateAccount")
    public ResponseEntity<Response> updateAccount(@RequestBody Customer customer) {
        Response response = new Response();
        try {
            Customer customer1 = customerService.updateCustomer(customer);
            response.setObject(customer1);
            response.setStatus(true);
            response.setMessage("updated");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (CustomerNotExistsException e) {
            response.setMessage("user_not_exist");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (Exception e) {
            response.setMessage("internal_server_error");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
