package org.example.service;

import org.example.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//instead of service annotation u can use component annotation also
@Service
public class ProductService {

    //This list consider as coming from database
    private static List<Product> productList = new ArrayList<>();
    static{
        productList.add(new Product(1,"name1","desc1",new BigDecimal(1.00)));
        productList.add(new Product(2,"name2","desc2",new BigDecimal(2.00)));
        productList.add(new Product(3,"name3","desc3",new BigDecimal(3.00)));
    }

    //get all products : for GET call
    public List<Product> getAllProducts() {
        return productList;
    }

    //get product by Id : for GET call
    public Product getProduct(int id) {
       Product product = productList.stream().filter(e->e.getId()==id).findFirst().get();
       return product;
    }

    //create product : for POST call
    public Product createProduct(Product product) {
        productList.add(product);
        return product;
    }

    //delete product by Id : for DELETE call
    public void deleteProduct(int id) {
        productList = productList.stream().filter(e->e.getId()!=id).collect(Collectors.toList());
    }

    //update product by Id : for PUT call
    public void updateeProduct(Product product, int id) {
        productList = productList.stream().map(e-> {
            if (e.getId() == id) {
                e.setDescription(product.getDescription());
                e.setName(product.getName());
                e.setPrice(product.getPrice());
            }
            return e;
        }).collect(Collectors.toList());
    }

    //update product by Id : for PATCH call
    public void updateProduct(Product product, int id) {
        productList = productList.stream().map(e-> {
            if (e.getId() == id) {
                e.setDescription(product.getDescription());
                e.setName(product.getName());
                e.setPrice(product.getPrice());
            }
            return e;
        }).collect(Collectors.toList());
    }
}
