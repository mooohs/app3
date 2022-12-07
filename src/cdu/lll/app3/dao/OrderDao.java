package cdu.lll.app3.dao;

import cdu.lll.app3.model.Order;
import cdu.lll.app3.model.Product;

import java.util.List;

public interface OrderDao {
    //下单页面
    Order get(String orderId);
    List<Order> findAll();
    List<Order> findByPage(int start,int num);
    List<Order> findByCustomer(int customerId);

    int count();
    int insert(Order order);
    int update(Order order);
    int delete(String orderId);
}
