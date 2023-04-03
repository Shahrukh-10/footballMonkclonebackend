package com.example.footballMonk.controller.cart;

import com.example.footballMonk.dto.Response;
import com.example.footballMonk.exception.cart.CartOrProductNullException;
import com.example.footballMonk.exception.cart.OutOfStockException;
import com.example.footballMonk.exception.cart.ProductAlreadyExistsException;
import com.example.footballMonk.models.Cart;
import com.example.footballMonk.models.Product;
import com.example.footballMonk.repository.CartRepository;
import com.example.footballMonk.services.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/addToCart/{id}")
    public ResponseEntity<Response> addToCart(@PathVariable String id, @RequestBody Product product) {
        Response response = new Response();
        try {
            Cart cart = cartService.addToCart(id, product);
            response.setMessage("product_added_successfully");
            response.setStatus(true);
            response.setObject(cart);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CartOrProductNullException e) {
            response.setMessage("cart_or_product_null");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (ProductAlreadyExistsException e) {
            response.setMessage("product_already_exist_in_cart");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }  catch (OutOfStockException e) {
            response.setMessage("product_out_of_stock");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }  catch (Exception e) {
            response.setStatus(false);
            response.setMessage("internal_server_error");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }
}
