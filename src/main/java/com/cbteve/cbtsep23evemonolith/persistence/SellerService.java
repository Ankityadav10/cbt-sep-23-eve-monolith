package com.cbteve.cbtsep23evemonolith.persistence;


import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService
{
    OrderRepository orderRepository;
    OrderstatusRepository orderstatusRepository;

    SellerService(OrderRepository orderRepository,
                  OrderstatusRepository orderstatusRepository)
    {
        this.orderRepository = orderRepository;
        this.orderstatusRepository =  orderstatusRepository;
    }

    public List<Order> getOrdersOfferwise(String Offerid)
    {
        return orderRepository.findByOfferid(Offerid).stream().
                filter(order -> orderstatusRepository.findAllByOrderid(order.getOrderid()).
                        stream().filter(orderstatus -> orderstatus.getStatus().equals("OPEN")).findAny().isPresent()).
                collect(Collectors.toList());

    }


}
