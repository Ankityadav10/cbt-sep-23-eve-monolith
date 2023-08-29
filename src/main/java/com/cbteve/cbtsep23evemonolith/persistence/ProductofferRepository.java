package com.cbteve.cbtsep23evemonolith.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductofferRepository extends JpaRepository<Productoffer, String> {

    public List<Productoffer> findAllBySellername(String sellername);
}