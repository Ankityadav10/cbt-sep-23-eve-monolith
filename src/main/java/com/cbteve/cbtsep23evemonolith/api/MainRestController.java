package com.cbteve.cbtsep23evemonolith.api;

import com.cbteve.cbtsep23evemonolith.entities.ProductRepository;
import com.cbteve.cbtsep23evemonolith.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController
{

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/saveProduct")
    public ResponseEntity<String> saveProduct(@RequestBody Product product)
    {
        productRepository.save(product);
        return new ResponseEntity<>("New Product Added", HttpStatus.OK);
    }

}
