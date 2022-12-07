package cdu.lll.app3.service;

import java.util.Map;

public interface CartService {

    Map<Integer, Integer> getProducts(int customerId);

    boolean in(int customerId,String sProductId);
    boolean update(int customerId,String sProductId,int num);
    boolean out(int customerId,String sProductId);
    boolean clear(int customerId);
}
