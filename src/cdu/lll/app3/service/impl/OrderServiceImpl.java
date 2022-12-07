package cdu.lll.app3.service.impl;

import cdu.lll.app3.dao.OrderDao;
import cdu.lll.app3.dao.impl.BaseDao;
import cdu.lll.app3.dao.impl.OrderDaoImpl;
import cdu.lll.app3.model.Order;
import cdu.lll.app3.service.CartService;
import cdu.lll.app3.service.OrderService;
import cdu.lll.app3.service.ProductService;

import java.util.List;

public class OrderServiceImpl extends BaseDao implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    CartService cartService = new CartServiceImpl();
    ProductService productService = new ProductServiceImpl();

    @Override
    public Order get(String orderId) {
        if (orderId == null || orderId.equals("")) {
            return null;
        }
        return orderDao.get(orderId);
    }

    @Override
    public List<Order> findByPage(int page, int pageSize) {
        return orderDao.findByPage((page - 1) * pageSize, pageSize);
    }

    @Override
    public List<Order> findByPage(String sPage) {
        if (sPage == null || sPage.equals("")) {
            return null;
        }
        return findByPage(Integer.parseInt(sPage), 10);
    }

    @Override
    public List<Order> findByCustomer(String sCustomerId) {
        return null;
    }

    @Override
    public int count() {
        return orderDao.count();
    }

    @Override
    public boolean createOrder(Order order) {
        if (orderDao.insert(order) == 1) {
            cartService.clear(order.getCustomer().getId());
            productService.updateStock(order.getProducts());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStatus(Order order) {
        return orderDao.update(order) == 1 ? true : false;
    }

    @Override
    public boolean del(String orderId) {
        if (orderId == null || orderId.equals("")) {
            return false;
        }
        return orderDao.delete(orderId) == 1 ? true : false;
    }
}
