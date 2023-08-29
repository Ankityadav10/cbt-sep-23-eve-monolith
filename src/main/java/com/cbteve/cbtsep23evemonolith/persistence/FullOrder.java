package com.cbteve.cbtsep23evemonolith.persistence;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullOrder {

    Order order;
    FullProductOffer fullProductOffer;

}
