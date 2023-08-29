package com.cbteve.cbtsep23evemonolith.persistence;

import org.springframework.stereotype.Service;

@Service
public class OrderService
{
    OrderRepository orderRepository;
    ProductofferRepository productofferRepository;

    ProductOfferService productOfferService;

    OrderService(OrderRepository orderRepository,
                 ProductofferRepository productofferRepository,
                 ProductOfferService productOfferService)
    {
        this.orderRepository = orderRepository;
        this.productofferRepository = productofferRepository;
        this.productOfferService = productOfferService;
    }


    public FullOrder composeFullOrder(String orderid)
    {
        FullOrder fullOrder = new FullOrder();
        fullOrder.setOrder(orderRepository.findById(orderid).get());
        fullOrder.setFullProductOffer(productOfferService.composeFullOffer(orderRepository.findById(orderid).get().getOfferid()));
        return fullOrder;
    }
}
