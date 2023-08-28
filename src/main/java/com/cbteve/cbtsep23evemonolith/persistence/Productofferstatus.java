package com.cbteve.cbtsep23evemonolith.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "productofferstatuses")
public class Productofferstatus {
    @Id
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Column(name = "offerid", length = 10)
    private String offerid;

    @Column(name = "status", length = 10)
    private String status;

}