package com.cbteve.cbtsep23evemonolith.api;

import com.cbteve.cbtsep23evemonolith.entities.ProductRepository;
import com.cbteve.cbtsep23evemonolith.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MainRestController
{
    @Autowired
    ProductRepository productRepository; // Dependency Injection

    @PostMapping("save/product") //CREATE
    public ResponseEntity<String> saveProduct(@RequestBody Product product)
    {
        productRepository.save(product);
        return new ResponseEntity<>("New Product Added", HttpStatus.OK);
    }

    @GetMapping("get/all/product") //READ ALL
    public List<Product> getAllProduct()
    {
        return productRepository.findAll();
    }

    @GetMapping("get/product/{hscode}") // READ
    public Product getProduct(@PathVariable("hscode") String hscode)
    {
        return productRepository.findById(hscode).get();
    }

    @PostMapping("update/product/{hscode}") //UPDATE
    public Product  updateProduct(@PathVariable("hscode") String hscode, @RequestBody Product product)
    {
        productRepository.updateNameAndUnitByHscode(product.getName(),product.getUnit(),hscode);
        return product;
    }

    @DeleteMapping("delete/product/{hscode}") //DELETE
    public ResponseEntity<String> deleteProduct(@PathVariable("hscode") String hscode)
    {
        productRepository.deleteById(hscode);
        return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
    }

}
