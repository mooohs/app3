package cdu.lll.app3.service;

import cdu.lll.app3.model.Order;
import cdu.lll.app3.model.Product;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Order get(String orderId);
    List<Order> findByPage(int page, int pageSize);
    List<Order> findByPage(String sPage);
    List<Order> findByCustomer(String sCustomerId);

    int count();

    boolean createOrder(Order order);
    boolean updateStatus(Order order);
    boolean del(String orderId);
}
