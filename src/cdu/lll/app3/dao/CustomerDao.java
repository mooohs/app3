package cdu.lll.app3.dao;

import cdu.lll.app3.model.Customer;
import cdu.lll.app3.model.Product;

import java.util.List;

public interface CustomerDao {

    Customer get(int id);
    Customer get(String name,String password);

    List<Customer> findAll();
    List<Customer> findByPage(int start, int num);
    List<Customer> findVip(boolean isVip);

    int count();
    int insert(Customer customer);
    int update(Customer customer);
    int delete(int id);
}
