package com.cbteve.cbtsep23evemonolith.api;

import com.cbteve.cbtsep23evemonolith.MyNum;
import com.cbteve.cbtsep23evemonolith.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @Qualifier("one")
    @Autowired
    @Lazy
    MyNum num1; // Dependency Injection

    @Qualifier("one")
    @Autowired
    MyNum num2; // Dependency Injection

    @Qualifier("one")
    @Autowired
    MyNum num3; // Dependency Injection



    @GetMapping("/getNumOne")
    public MyNum getNumOne()
    {
        return num1;
    }

    @GetMapping("/getNumTwo")
    public MyNum getNumTwo()
    {
        return num2.increment();
    }

    @GetMapping("/getNumThree")
    public MyNum getNumThree()
    {
        return num3.increment();
    }

}
