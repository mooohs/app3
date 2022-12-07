package cdu.lll.app3.dao.impl;

import cdu.lll.app3.dao.CustomerDao;
import cdu.lll.app3.dao.OrderDao;
import cdu.lll.app3.model.Customer;
import cdu.lll.app3.model.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public Order get(String orderId) {
        Order order = null;
        String sql = "SELECT * FROM order_table WHERE orderId=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderId"));
                order.setCustomer(customerDao.get(rs.getInt("customerId")));
                order.setMoney(rs.getBigDecimal("money"));
                order.setProducts(rs.getString("products"));
                order.setName(rs.getString("name"));
                order.setAddress(rs.getString("address"));
                order.setTel(rs.getString("tel"));
                order.setCreateTime(rs.getLong("createTime"));
                order.setUpdateTime(rs.getInt("updateTime"));
                order.setStatusCode(rs.getInt("statusCode"));//订单状态

            }
        } catch (SQLException e) {
            System.out.println("DAO 获取订单失败：orderId=" + orderId);
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orderList = new ArrayList<Order>();
        String sql = "SELECT * FROM order_table ORDER BY createTime DESC";

        try {
            stmt = conn.createStatement();//创建对象
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderId"));
                order.setCustomer(customerDao.get(rs.getInt("customerId")));
                order.setMoney(rs.getBigDecimal("money"));
                order.setProducts(rs.getString("products"));
                order.setName(rs.getString("name"));
                order.setAddress(rs.getString("address"));
                order.setTel(rs.getString("tel"));
                order.setCreateTime(rs.getLong("createTime"));
                order.setUpdateTime(rs.getInt("updateTime"));
                order.setStatusCode(rs.getInt("statusCode"));//订单状态
                orderList.add(order);

            }
        } catch (SQLException e) {
            System.out.println("DAO 获取订单列表失败");
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Order> findByPage(int start, int num) {
        List<Order> orderList = new ArrayList<Order>();
        String sql = "SELECT * FROM order_table ORDER BY createTime DESC LIMIT ?,?";

        try {
            pstmt = conn.prepareStatement(sql);//创建对象
            pstmt.setInt(1, start);
            pstmt.setInt(2, num);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderId"));
                order.setCustomer(customerDao.get(rs.getInt("customerId")));
                order.setMoney(rs.getBigDecimal("money"));
                order.setProducts(rs.getString("products"));
                order.setName(rs.getString("name"));
                order.setAddress(rs.getString("address"));
                order.setTel(rs.getString("tel"));
                order.setCreateTime(rs.getLong("createTime"));
                order.setUpdateTime(rs.getInt("updateTime"));
                order.setStatusCode(rs.getInt("statusCode"));//订单状态
                orderList.add(order);

            }
        } catch (SQLException e) {
            System.out.println("DAO 分页获取订单列表失败");
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Order> findByCustomer(int customerId) {
        List<Order> orderList = new ArrayList<Order>();
        String sql = "SELECT * FROM order_table ORDER BY createTime DESC WHERE customerId=?";

        try {
            pstmt = conn.prepareStatement(sql);//创建对象
            pstmt.setInt(1, customerId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderId"));
                order.setCustomer(customerDao.get(rs.getInt("customerId")));
                order.setMoney(rs.getBigDecimal("money"));
                order.setProducts(rs.getString("products"));
                order.setName(rs.getString("name"));
                order.setAddress(rs.getString("address"));
                order.setTel(rs.getString("tel"));
                order.setCreateTime(rs.getLong("createTime"));
                order.setUpdateTime(rs.getInt("updateTime"));
                order.setStatusCode(rs.getInt("statusCode"));//订单状态
                orderList.add(order);

            }
        } catch (SQLException e) {
            System.out.println("DAO 获取顾客订单列表失败：customerId=" + customerId);
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public int insert(Order order) {
        int rows = 0;
        String sql = "INSERT INTO order_table (orderId,customerId,money,products,name,address,tel,createTime,updateTime,statusCode)VALUES (?,?,?,?,?,?,?,?,?,?)";//降序
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrderId());
            pstmt.setInt(2, order.getCustomer().getId());
            pstmt.setBigDecimal(3, order.getMoney());
            pstmt.setString(4, order.getProductsString());
            pstmt.setString(5, order.getName());
            pstmt.setString(6, order.getAddress());
            pstmt.setString(7, order.getTel());
            pstmt.setLong(8, order.getCreateTime());
            pstmt.setLong(9, order.getUpdateTime());
            pstmt.setInt(10, order.getStatusCode());
            rows = pstmt.executeUpdate();
            System.out.println("DAO 添加订单：" + rows + "," + order);
        } catch (SQLException e) {
            System.out.println("DAO 添加商品失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int update(Order order) {
        int rows = 0;
        String sql = "update order_table SET updateTime=?,statusCode=? WHERE orderId=? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, order.getUpdateTime());
            pstmt.setInt(2, order.getStatusCode());
            pstmt.setString(3, order.getOrderId());
            rows = pstmt.executeUpdate();
            System.out.println("DAO 修改订单：" + rows + "," + order);
        } catch (SQLException e) {
            System.out.println("DAO 修改订单失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delete(String orderId) {
        int rows = 0;
        String sql = "DELETE FROM order_table WHERE orderId=? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderId);
            rows = pstmt.executeUpdate();
            System.out.println("DAO 删除订单：" + rows + ",orderId" + orderId);
        } catch (SQLException e) {
            System.out.println("DAO 删除订单失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int count() {
        int count = 0;
        String sql = "SELECT count(*) FROM order_table  ";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取订单记录总数失败");
            e.printStackTrace();
        }
        return count;
    }
}
