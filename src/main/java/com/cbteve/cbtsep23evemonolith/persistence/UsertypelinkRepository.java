package com.cbteve.cbtsep23evemonolith.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsertypelinkRepository extends JpaRepository<Usertypelink, String> {


    List<Usertypelink> findByUsername(String username);

    List<Usertypelink> findByType(String type);


}