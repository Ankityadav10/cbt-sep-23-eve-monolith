package com.cbteve.cbtsep23evemonolith.persistence;

import org.springframework.stereotype.Service;

@Service
public class ProductOfferService {

    ProductofferRepository productofferRepository;

    ProductOfferService(
            ProductofferRepository productofferRepository)

    {
        this.productofferRepository = productofferRepository;
    }

    public FullProductOffer composeFullOffer(String offerid)
    {
        Productoffer productoffer = productofferRepository.findById(offerid).get();
        FullProductOffer fullproductoffer = new FullProductOffer();
        fullproductoffer.setProductoffer(productoffer);
        fullproductoffer.setOfferAmnt(productoffer.getQty()* productoffer.getUnitprice());
        return fullproductoffer;
    }

}
