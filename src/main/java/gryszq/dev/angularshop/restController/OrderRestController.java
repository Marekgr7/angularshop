package gryszq.dev.angularshop.restController;

import gryszq.dev.angularshop.model.*;
import gryszq.dev.angularshop.service.OrderService;
import gryszq.dev.angularshop.service.ProductListService;
import gryszq.dev.angularshop.service.ProductService;
import gryszq.dev.angularshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    private OrderService orderService;

    private ProductListService productListService;

    private ProductService productService;

    private UserService userService;

    private String username;

    @Autowired
    public OrderRestController(OrderService orderService, ProductListService productListService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productListService = productListService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping
    public void saveOrder(@RequestBody Order order){
        orderService.save(order);
    }

    @PostMapping("/status")
    public void setStatusTrue(Order order){
        order.setStatus(true);
        orderService.save(order);
    }

    @PostMapping("/setusername")
    public void setUsername(@RequestBody String username){
        //TODO COOKIE
        this.username = username;
        System.out.println("username to" + username);
    }

    @PostMapping("/cart")
    public void saveCart(@RequestBody List<Cart> cart){

        List<Cart> cartList = new ArrayList<>();
        cartList = cart;
        ProductList productList = new ProductList();

        //save order to database (try out with random user - need to be sent)
//        User user = userService.findUserById(1L);

        User user = userService.findByUsername(username);

        Long totalAmount = 0L;

        for(Cart item : cartList){
            totalAmount = totalAmount + item.getPrice()*item.getQty();
        }

        Order order = new Order(user,totalAmount);
        saveOrder(order);

        productList.setOrderId(order);

        Product product = new Product();

        for(Cart item : cartList){
            product = productService.getById(item.getId());
            productList.setProductId(product);
            productList.setQty(item.getQty());
            productListService.save(productList);
        }

        user.addOrder(order);
        userService.save(user);
    }

    @GetMapping("/userorder")
    public List<Order> getUserOrder(){

       try {
           User user = userService.findByUsername(username);
           List<Order> userOrderList = orderService.getUserOrders(user.getId());
           return userOrderList;
       } catch (Exception e)
       {
           e.printStackTrace();
       }
       return null;
    }

    @GetMapping("/usercart")
    public List<UserCart> getUserCart(@RequestParam Long id){
        try{
            User user = userService.findByUsername(username);
            List<UserCart> cartList = orderService.getUserCart(user.getId(),id);
            return cartList;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
