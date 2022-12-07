package cdu.lll.app3.service;

import cdu.lll.app3.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findByPage(int page, int pageSize);
    List<Customer> findByPage(String sPage,String sPageSize);
    List<Customer> findByPage(String sPage);

    Customer get(String sid);
    Customer get(String name,String password);

    int count();
    boolean add(Customer customer);

}
