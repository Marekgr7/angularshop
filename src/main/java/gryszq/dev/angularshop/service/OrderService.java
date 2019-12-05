package gryszq.dev.angularshop.service;

import gryszq.dev.angularshop.model.Cart;
import gryszq.dev.angularshop.model.Order;
import gryszq.dev.angularshop.model.User;
import gryszq.dev.angularshop.model.UserCart;
import gryszq.dev.angularshop.repository.OrderRepository;
import gryszq.dev.angularshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private UserService userService;

    private UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserService userService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order){
        orderRepository.save(order);
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public List<Order> getUserOrders(Long id){

//        Map<String, BigInteger> mappedResult = new HashMap<>();
//        List<Object[]> queryResult = historyRepository.getUsersListeningHours();
//        for (Object[] obj : queryResult ) {
//            String ld = (String) obj[0];
//            BigInteger count = (BigInteger) obj[1];
//            mappedResult.put(ld, count);
//        }
//        return mappedResult;

        List<Object[]> queryResult = orderRepository.findAllQuery(id);

        List<Order> queryOrderList = new ArrayList<>();
        for(Object[] obj : queryResult){
            BigInteger queryId = (BigInteger) obj[0];
            Boolean queryStatus = (Boolean) obj[1];
            Double queryTotal = (Double) obj[2];
            queryOrderList.add(new Order(queryId.longValue(),queryTotal,queryStatus));
        }
        return queryOrderList;
    }

    public List<UserCart> getUserCart(Long id, Long orderId){

        List<Object[]> queryCartResult = orderRepository.findUserCart(id, orderId);

        List<UserCart> queryCartList = new ArrayList<>();
        for(Object[] obj: queryCartResult){
            BigInteger queryId = (BigInteger) obj[0];
            String queryName = (String) obj[1];
            Double queryWeight = (Double) obj[2];
            BigInteger queryQty = (BigInteger) obj[3];
            Double queryPrice = (Double) obj[4];

//            String name, Long id, Long qty, Long price
            queryCartList.add(new UserCart(queryId.longValue(),queryName,queryWeight,queryQty.longValue(),queryPrice));
        }
        return queryCartList;
    };

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){

    }

}
