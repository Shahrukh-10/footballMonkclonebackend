package com.example.footballMonk.services.cart;

import com.example.footballMonk.exception.cart.CartOrProductNullException;
import com.example.footballMonk.exception.cart.OutOfStockException;
import com.example.footballMonk.exception.cart.ProductAlreadyExistsException;
import com.example.footballMonk.models.Cart;
import com.example.footballMonk.models.Product;
import com.example.footballMonk.repository.CartRepository;
import com.example.footballMonk.repository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRespository productRespository;

    @Override
    public Cart addToCart(String cartId, Product product) throws CartOrProductNullException, ProductAlreadyExistsException, OutOfStockException {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        Product product1 = productRespository.findById(product.getId()).orElse(null);
        if (!product1.isStock()) throw new OutOfStockException("This product is out of stock");
        if (product.getQuantity() > product1.getQuantity()) throw new OutOfStockException("Stock is not enough");
        if (cart == null || product1 == null) throw new CartOrProductNullException("Cart or product is null");
        List<Product> productList;
        if (cart.getProducts() == null || cart.getProducts().size() == 0) {
            productList = new ArrayList<>();
        } else {
            productList = cart.getProducts();
            for (Product prod : productList) {
                if (prod.getId().equals(product.getId())) {
                    throw new ProductAlreadyExistsException("Product already exist in cart");
                }
            }
        }
        product.setPrice(product1.getPrice());
        product.setProductName(product1.getProductName());
        product.setProductImageUrl(product1.getProductImageUrl());
        product.setDescription(product1.getDescription());
        productList.add(product);
        cart.setProducts(productList);
        int totalCartPrice = getTotalCartPrice(productList);
        cart.setTotalPrice(totalCartPrice);
        int available_product_quantity = product1.getQuantity() - product.getQuantity();
        if (available_product_quantity > 0) {
            product1.setQuantity(available_product_quantity);
        }else {
            product1.setStock(false);
            product1.setQuantity(0);
        }
        productRespository.save(product1);
        return cartRepository.save(cart);
    }

    public int getTotalCartPrice(List<Product> products) {
        int totalCartPrice = 0;
        for (Product product : products) {
            int totalPrice = product.getPrice() * product.getQuantity();
            totalCartPrice += totalPrice;
        }
        return totalCartPrice;
    }

}
