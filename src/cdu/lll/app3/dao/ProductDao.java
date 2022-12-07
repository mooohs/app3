package cdu.lll.app3.dao;

import cdu.lll.app3.model.Product;

import java.util.List;

public interface ProductDao {

    Product get(int id);
    List<Product> findAll();
    List<Product> findByPage(int start,int num);
    int count();
    int insert(Product product);
    int update(Product product);
    int delete(int id);

}
