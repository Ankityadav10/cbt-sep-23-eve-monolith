package com.cbteve.cbtsep23evemonolith.entities;

import com.cbteve.cbtsep23evemonolith.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, String>
{
    @Transactional
    @Modifying
    @Query("update Product p set p.name = ?1, p.unit = ?2 where p.hscode = ?3")
    int updateNameAndUnitByHscode(String name, String unit, String hscode);
}