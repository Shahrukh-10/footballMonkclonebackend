package com.example.footballMonk.services.product;

import com.example.footballMonk.exception.product.ProductExistException;
import com.example.footballMonk.exception.product.ProductNotFoundException;
import com.example.footballMonk.models.Product;
import com.example.footballMonk.repository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean addProduct(Product product) throws ProductExistException {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("productName").is(product.getProductName()),
                Criteria.where("productImageUrl").is(product.getProductImageUrl())
        );
        Query query = new Query(criteria);
        if (mongoTemplate.exists(query, Product.class)) {
            throw new ProductExistException("Product with this image or url already exists");
        } else {
            if (product.getQuantity() > 0 && product.getPrice() > 0) {
                mongoTemplate.save(product);
            } else {
                return false;
                
        }
        return true;
    }


    @Override
    public boolean updateProduct(Product product) throws ProductNotFoundException {
        Criteria criteria = Criteria.where("id").is(product.getId());
        Query query = new Query(criteria);
        if (mongoTemplate.exists(query, Product.class)) {
            Product product1 = mongoTemplate.findOne(query, Product.class);
            product1 = updateProductDetails(product1, product);
            mongoTemplate.save(product1);
            return true;
        } else {
            throw new ProductNotFoundException("No product found with this id");
        }
    }

    public Product updateProductDetails(Product product, Product updatedProduct) {
        product.setProductName(updatedProduct.getProductName());
        product.setProductImageUrl(updatedProduct.getProductImageUrl());
        product.setDescription(updatedProduct.getDescription());
        product.setQuantity(updatedProduct.getQuantity());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.isStock());

        return product;
    }
}
