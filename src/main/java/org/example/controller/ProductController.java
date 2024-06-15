package org.example.controller;

import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    public ProductService productService;

    /**
     * This is just sample
     * @return
     */
    @GetMapping("/")
    public String getDummyProduct(){
        return "product";
    }

    /**
     * Here I am sending request model class as reponse.
     * if you want you can create different model class for response
     * @param product
     * @return
     */
    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product p = productService.createProduct(product);
        System.out.println("Product is created :"+p.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(p);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id){
        Product product = productService.getProduct(id);
        return  product;
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        System.out.println("Product is deleted :"+id);
    }

    /**
     * this put will upadte the decription of product of given id
     * @param product
     * @param id
     */
    @PutMapping("/product/{id}")
    public  Product updateeProduct(@RequestBody Product product,@PathVariable int id){
        productService.updateeProduct(product, id);
        System.out.println("Product is updated :"+product.getId());
        return product;
    }

    /**
     * this patch will upadte the decription of product of given id
     * @param product
     * @param id
     */
    @PatchMapping("/product/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable int id){
        productService.updateProduct(product, id);
        System.out.println("Product is updated :"+product.getId());
        return product;
    }
}
