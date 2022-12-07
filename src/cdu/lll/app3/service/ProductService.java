package cdu.lll.app3.service;

import cdu.lll.app3.model.Product;

import java.util.List;
import java.util.Map;
//相对具体的业务逻辑服务层。
//给controller层的类提供接口进行调用。一般就是自己写的方法封装起来，就是声明一下，具体实现在serviceImpl中。
public interface ProductService {
    List<Product> findByPage(int page,int pageSize);
    List<Product> findByPage(String sPage,String sPageSize);
    List<Product> findByPage(String sPage);

    Product get(String sid);
    Product get(int id);

    int count();
    boolean add(Product product);
    boolean del(String sid);
    boolean mod(Product product);
    boolean updateStock(Map<Integer,Integer> products);
    //Map以按键/数值对的形式存储数据，和数组非常相似，在数组中存在的索引，它们本身也是对象。

}
