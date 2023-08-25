package com.cbteve.cbtsep23evemonolith.entities;

import com.cbteve.cbtsep23evemonolith.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}