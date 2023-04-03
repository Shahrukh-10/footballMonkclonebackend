package com.example.footballMonk.services.cart;

import com.example.footballMonk.exception.cart.CartOrProductNullException;
import com.example.footballMonk.exception.cart.OutOfStockException;
import com.example.footballMonk.exception.cart.ProductAlreadyExistsException;
import com.example.footballMonk.models.Cart;
import com.example.footballMonk.models.Product;

public interface CartService {

    public Cart addToCart(String cartId , Product product) throws CartOrProductNullException, ProductAlreadyExistsException, OutOfStockException;
}
