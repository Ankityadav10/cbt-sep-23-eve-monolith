package com.cbteve.cbtsep23evemonolith.api;

import com.cbteve.cbtsep23evemonolith.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class MainRestController
{
    @Autowired
    ProductRepository productRepository; // Dependency Injection
    CredentialRepository credentialRepository;
    UserdetailRepository userdetailRepository;
    UsertypelinkRepository usertypelinkRepository;
    ProductofferRepository productofferRepository;
    ProductofferstatusRepository productofferstatusRepository;

    UsernamewalletlinkRepository usernamewalletlinkRepository;

    OrderRepository orderRepository;

    OrderstatusRepository orderstatusRepository;

    UserdetailService userdetailService;

    ProductOfferService productOfferService;

    SellerService sellerService;

    OrderService orderService;
    PaymentwalletlinkRepository paymentwalletlinkRepository;

    PaymentRepository paymentRepository;


    MainRestController(  // Constructor Injection

                         CredentialRepository credentialRepository,
                         UserdetailRepository userdetailRepository,
                         UsertypelinkRepository usertypelinkRepository,
                         UserdetailService userdetailService,
                         ProductofferRepository productofferRepository,
                         ProductofferstatusRepository productofferstatusRepository,
                         ProductOfferService productOfferService,
                         OrderRepository orderRepository,
                         OrderstatusRepository orderstatusRepository,
                         SellerService sellerService,
                         OrderService orderService,
                         UsernamewalletlinkRepository usernamewalletlinkRepository,
                         PaymentwalletlinkRepository paymentwalletlinkRepository,
                         PaymentRepository paymentRepository
                         )
    {
        this.credentialRepository = credentialRepository;
        this.userdetailRepository = userdetailRepository;
        this.usertypelinkRepository = usertypelinkRepository;
        this.userdetailService = userdetailService;
        this.productofferRepository = productofferRepository;
        this.productofferstatusRepository = productofferstatusRepository;
        this.productOfferService = productOfferService;
        this.orderRepository = orderRepository;
        this.orderstatusRepository = orderstatusRepository;
        this.sellerService = sellerService;
        this.orderService = orderService;
        this.usernamewalletlinkRepository = usernamewalletlinkRepository;
        this.paymentwalletlinkRepository = paymentwalletlinkRepository;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("save/product") //CREATE
    public ResponseEntity<String> saveProduct(@RequestBody Product product)
    {
        productRepository.save(product);
        return new ResponseEntity<>("New Product Added", HttpStatus.OK);
    }

    @GetMapping("get/all/product") //READ ALL
    public List<Product> getAllProduct()
    {
        return productRepository.findAll();
    }

    @GetMapping("get/product/{hscode}") // READ
    public Product getProduct(@PathVariable("hscode") String hscode)
    {
        return productRepository.findById(hscode).get();
    }

    @PostMapping("update/product/{hscode}") //UPDATE
    public Product  updateProduct(@PathVariable("hscode") String hscode, @RequestBody Product product)
    {
        productRepository.updateNameAndUnitByHscode(product.getName(),product.getUnit(),hscode);
        return product;
    }

    @DeleteMapping("delete/product/{hscode}") //DELETE
    public ResponseEntity<String> deleteProduct(@PathVariable("hscode") String hscode)
    {
        productRepository.deleteById(hscode);
        return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody Credential credential)
    {
        credentialRepository.save(credential);

        return ResponseEntity.ok().body("New Signup Successful");
    }

    @PostMapping("save/user/detail")
    public ResponseEntity<Userdetail> saveUserDetails(@RequestBody Userdetail userdetail)
    {
        userdetailRepository.save(userdetail);
        return ResponseEntity.ok().body(userdetail);
    }

    @PostMapping("save/user/type")
    public ResponseEntity<Usertypelink> saveUserType(@RequestBody Usertypelink usertypelink)
    {
        usertypelink.setLinkid(String.valueOf((int)(Math.random()*100000)));
        usertypelinkRepository.save(usertypelink);
        return ResponseEntity.ok().body(usertypelink);
    }

    @GetMapping("get/user/detail/{username}")
    public ResponseEntity<FullUserDetail> getUserDetail(@PathVariable("username") String username) {

        return ResponseEntity.ok().body(userdetailService.getFullUserDetail(username));
    }

    @PutMapping("update/user/detail/{username}")
    public ResponseEntity<Userdetail> updateUserDetail(@PathVariable("username") String username, @RequestBody Userdetail userdetail)
    {
        userdetailRepository.updateFnameAndLnameAndEmailAndPhoneAndCityAndCountryByUsername(
                userdetail.getFname(),
                userdetail.getLname(),
                userdetail.getEmail(),
                userdetail.getPhone(),
                userdetail.getCity(),
                userdetail.getCountry(),
                username
        );

                return ResponseEntity.ok().body(userdetail);
    }

    @PostMapping("save/product/offer")
    public ResponseEntity<Productoffer> saveProductOffer(@RequestBody Productoffer productoffer)
    {

        UUID uuid = UUID.randomUUID(); // generate unique id

        productoffer.setId(String.valueOf((int)(Math.random()*100000)));
        productofferRepository.save(productoffer);

        Productofferstatus productofferstatus = new Productofferstatus();
        productofferstatus.setId(String.valueOf((int)(Math.random()*100000)));
        productofferstatus.setOfferid(productoffer.getId());
        productofferstatus.setStatus("OPEN");

        productofferstatusRepository.save(productofferstatus);

        return ResponseEntity.ok().body(productoffer);
    }

    @GetMapping("get/all/users/type/{type}")
    public List<String> getAllUsersByType(@PathVariable("type") String type)
    {
         return this.userdetailService.getAllUsernamesByType(type);
    }

    @GetMapping("get/all/product/offer")
    public List<FullProductOffer> getAllProductOffer()
    {
        return productofferRepository.findAll().stream().
                map(productoffer -> productOfferService.composeFullOffer(productoffer.getId())).collect(Collectors.toList());
    }

    @PostMapping("save/order")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order)
    {
        order.setOrderid(String.valueOf((int)(Math.random()*100000)));
        orderRepository.save(order);

        Orderstatus orderstatus = new Orderstatus();
        orderstatus.setId(String.valueOf((int)(Math.random()*100000)));
        orderstatus.setOrderid(order.getOrderid());
        orderstatus.setStatus("OPEN");

        orderstatusRepository.save(orderstatus);

        return ResponseEntity.ok().body(order);
    }

    @PutMapping("update/order/{orderstatusid}")
    public ResponseEntity<Orderstatus> updateOrder(@PathVariable("orderstatusid") String orderstatusid,
                                             @RequestBody Orderstatus orderstatus)
    {
        orderstatusRepository.updateStatusByOrderid(orderstatus.getStatus(),orderstatusid);
        return ResponseEntity.ok().body(orderstatus);
    }

    @GetMapping("get/all/orders/sellerwise/{sellername}")
    public List<List<FullOrder>> getOrdersSellerwise(@PathVariable("sellername") String sellername )
    {
        return productofferRepository.findAllBySellername(sellername).stream().
                map(productoffer ->   sellerService.getOrdersOfferwise(productoffer.getId())).
                map(orders -> orders.stream().map(order -> orderService.composeFullOrder(order.getOrderid())).collect(Collectors.toList()) ).
                collect(Collectors.toList());
    }

    @PostMapping("save/order/status/{orderid}")
    public ResponseEntity<List<Orderstatus>> saveOrderStatus(@PathVariable("orderid") String orderid)
    {
        orderstatusRepository.updateStatusByOrderid("ACCEPTED",orderid);

        orderRepository.findByOfferid(  orderRepository.findById(orderid).get().getOfferid()   ).
                stream().filter(order -> !order.getOrderid().equals(orderid)).
                forEach( order -> orderstatusRepository.updateStatusByOrderid("REJECTED",order.getOrderid()));

        Order order = orderRepository.findById(orderid).get();


        Paymentwalletlink paymentwalletlink = new Paymentwalletlink();
        paymentwalletlink.setLinkid(String.valueOf((int)(Math.random()*100000)));
        paymentwalletlink.setAmount(order.getBid());
        paymentwalletlink.setPaymenttype("ORDER");
        paymentwalletlink.setPaymentrefid(orderid);
        paymentwalletlink.setPayerwallet(usernamewalletlinkRepository.findById(order.getBuyername()).get().getWalletid());
        paymentwalletlink.setPayeewallet(usernamewalletlinkRepository.findById(productofferRepository.
                findById(order.getOfferid()).get().getSellername()).get().getWalletid());
        paymentwalletlink.setEscrowwallet(usernamewalletlinkRepository.findById("indiagator").get().getWalletid());

        paymentwalletlinkRepository.save(paymentwalletlink);

        Payment payment = new Payment();
        payment.setPaymentwalletlink(paymentwalletlink.getLinkid());
        payment.setId(String.valueOf((int)(Math.random()*100000)));
        payment.setStatus("DUE");
        payment.setOrderid(orderid);
        payment.setOfferid(order.getOfferid());

        paymentRepository.save(payment);




        return ResponseEntity.ok().body(orderstatusRepository.findAllByOrderid(orderid));

    }

}
