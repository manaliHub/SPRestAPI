package org.example.controller;

import org.example.model.Product;
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

    /**
     * This is just sample
     * @return
     */
    @GetMapping("/")
    public String getDummyProduct(){
        return "product";
    }

    /*@PostMapping("/product")
    public String saveProduct(@RequestBody Product product){
        System.out.println("Product is saved :"+product.getId());
        return product.getId();
    }*/

    /**
     * Here I am sending request model class as reponse.
     * if you want you can create different model class for response
     * @param product
     * @return
     */
    @PostMapping("/product")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        product.setId(UUID.randomUUID().toString());
        System.out.println("Product is saved :"+product.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
     List<Product> prodList = new ArrayList<>();
     prodList.add(new Product("1","name1","description1",new BigDecimal(1.00)));
        prodList.add(new Product("2","name2","description2",new BigDecimal(2.00)));
     return  prodList;
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable String id){
       Product product = new Product(id,"name1","description1",new BigDecimal(1.00));
        return  product;
    }

    /**
     * this patch will upadte the decription of product of given id
     * @param product
     * @param id
     */
    @PatchMapping("/product/{id}")
    public void updateProduct(@RequestBody Product product,@PathVariable String id){
        Product product1 = new Product(id,"name1","description1",new BigDecimal(1.00));
        System.out.println("Product is updated :"+product1.getId()+" "+product1.getDescription() +" "+product1.getName());
        product1.setDescription(product.getDescription());
        System.out.println("Product is updated :"+product1.getId()+" "+product1.getDescription()+" "+product1.getName());;
    }

    /**
     * this put will upadte the decription of product of given id
     * @param product
     * @param id
     */
    @PutMapping("/product/{id}")
    public void updateeProduct(@RequestBody Product product,@PathVariable String id){
        Product product1 = new Product(id,"name1","description1",new BigDecimal(1.00));
        System.out.println("Product is updated :"+product1.getId()+" "+product1.getDescription() +" "+product1.getName());
        product1.setDescription(product.getDescription());
        System.out.println("Product is updated :"+product1.getId()+" "+product1.getDescription()+" "+product1.getName());;
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable String id){
        System.out.println("Product is updated");
    }

}
