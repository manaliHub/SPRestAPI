package org.example.controller;

import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

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
        Product p = null;
        try {
            p = productService.createProduct(product);
            System.out.println("Product is created :" + p.getId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(p);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        if(productList.size()<=0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
        return ResponseEntity.of(Optional.of(productList));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product = productService.getProduct(id);
        if(product == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.of(Optional.of(product));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id){
        try {
            productService.deleteProduct(id);
            System.out.println("Product is deleted :" + id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * this put will upadte the decription of product of given id
     * @param product
     * @param id
     */
    @PutMapping("/product/{id}")
    public  ResponseEntity<Product> updateeProduct(@RequestBody Product product,@PathVariable int id){
        try {
            productService.updateeProduct(product, id);
            System.out.println("Product is updated :" + product.getId());
            return ResponseEntity.of(Optional.of(product));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * this patch will upadte the decription of product of given id
     * @param product
     * @param id
     */
    @PatchMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,@PathVariable int id){
        try {
        productService.updateProduct(product, id);
        System.out.println("Product is updated :"+product.getId());
            return ResponseEntity.of(Optional.of(product));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
