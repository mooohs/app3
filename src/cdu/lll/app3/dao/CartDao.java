package cdu.lll.app3.dao;

import cdu.lll.app3.model.CartItem;

import java.util.Map;

public interface CartDao {

    //购物车
    Map<Integer,Integer>find(int customerId);
    int insert(CartItem cartItem);
    int update(CartItem cartItem);
    int delete(CartItem cartItem);
    int delete(int customerId);


}
