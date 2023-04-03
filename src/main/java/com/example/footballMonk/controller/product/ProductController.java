package com.example.footballMonk.controller.product;

import com.example.footballMonk.dto.Response;
import com.example.footballMonk.exception.product.ProductExistException;
import com.example.footballMonk.exception.product.ProductNotFoundException;
import com.example.footballMonk.models.Product;
import com.example.footballMonk.repository.ProductRespository;
import com.example.footballMonk.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRespository productRespository;

    @PostMapping("/add")
    public ResponseEntity<Response> addProduct(@RequestBody Product product) {
        Response response = new Response();
        try {

            boolean status = productService.addProduct(product);
            if (status) {
                response.setStatus(true);
                response.setMessage("product_added");
            } else {
                response.setStatus(false);
                response.setMessage("product_not_added");
            }
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        } catch (ProductExistException e) {
            response.setMessage("product_already_exists");
            response.setStatus(false);
            return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
        } catch (Exception e) {
            response.setMessage("internal_server_error");
            response.setStatus(false);
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Response> updateProduct(@RequestBody Product product) {
        Response response = new Response();
        try {
            boolean status = productService.updateProduct(product);
            if (status) {
                response.setStatus(true);
                response.setMessage("product_updated");
            }
            return new ResponseEntity<Response>(response, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            response.setMessage("product_not_found");
            response.setStatus(false);
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setMessage("internal_server_error");
            response.setStatus(false);
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productRespository.findAll();
    }
}
