package gryszq.dev.angularshop.repository;

import gryszq.dev.angularshop.model.Order;
import gryszq.dev.angularshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findOrdersByUser(User user);

    @Query(value = "select order_id, status,total from user_order_list, user u, user_order where user_order.order_id = user_order_list.order_list_order_id " +
            "and user_order_list.user_id_user = u.id_user and u.id_user = :USERID", nativeQuery = true)
    List<Object[]> findAllQuery(@Param("USERID")Long id);

    @Query(value = "select user_order.order_id, product.name, product.weight, product_list.qty, product.price from user, user_order_list , product, product_list, user_order where product_list.order_id_order_id = user_order_list.order_list_order_id and\n" +
            "product_list.product_id_product_id = product.product_id and user_order.order_id = user_order_list.order_list_order_id and user.id_user = user_order_list.user_id_user and user.id_user = :USERID and user_order.order_id = :ORDERID " ,nativeQuery = true)
    List<Object[]> findUserCart(@Param("USERID")Long id, @Param("ORDERID") Long orderId);
}
