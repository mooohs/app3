package cdu.lll.app3.dao.impl;

import cdu.lll.app3.dao.CustomerDao;
import cdu.lll.app3.model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl extends BaseDao implements CustomerDao {
    @Override
    public Customer get(int id) {
        Customer customer = null;
        String sql = "SELECT * FROM customer_table WHERE id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPassword(rs.getString("password"));
                customer.setVip(rs.getBoolean("isVip"));
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取顾客失败：id=" + id);
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer get(String name, String password) {
        Customer customer = null;
        String sql = "SELECT * FROM customer_table WHERE name=? AND password=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPassword(rs.getString("password"));
                customer.setVip(rs.getBoolean("isVip"));
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取登录顾客失败：name=" + name + ",password=" + password);
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = new ArrayList<Customer>();
        String sql = "SELECT * FROM customer_table ORDER BY name";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPassword(rs.getString("password"));
                customer.setVip(rs.getBoolean("isVip"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取顾客列表失败" );
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<Customer> findByPage(int start, int num) {
        List<Customer> customerList = new ArrayList<Customer>();
        String sql = "SELECT * FROM customer_table ORDER BY id LIMIT ?,?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,start);
            pstmt.setInt(2,num);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPassword(rs.getString("password"));
                customer.setVip(rs.getBoolean("isVip"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("DAO 分页获取顾客列表失败:" );
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public List<Customer> findVip(boolean isVip) {
        List<Customer> customerList = new ArrayList<Customer>();
        String sql = "SELECT * FROM customer_table WHERE isVip=";

        if (isVip){
            sql+="1";
        }else {
            sql+="0";
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPassword(rs.getString("password"));
                customer.setVip(rs.getBoolean("isVip"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取vip顾客列表失败:" );
            e.printStackTrace();
        }
        return customerList;
    }

    @Override
    public int insert(Customer customer) {
       int rows=0;
       String sql="INSERT INTO customer_table(name,password,isVip) VALUES(?,?,?) ";
       try {
           pstmt=conn.prepareStatement(sql);
           pstmt.setString(1,customer.getName());
           pstmt.setString(2,customer.getPassword());
           pstmt.setBoolean(3,customer.isVip());
           rows=pstmt.executeUpdate();
           System.out.println("DAO 添加顾客："+ rows +","+customer);
       } catch (SQLException e) {
           System.out.println("DAO 添加顾客失败");
           e.printStackTrace();
       }
       return rows;
    }

    @Override
    public int update(Customer customer) {
        int rows=0;
        String sql="INSERT INTO customer_table SET name=?,password=?,isVip=? WHERE id=? ";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,customer.getName());
            pstmt.setString(2,customer.getPassword());
            pstmt.setBoolean(3,customer.isVip());
            pstmt.setInt(4,customer.getId());
            rows=pstmt.executeUpdate();
            System.out.println("DAO 修改顾客："+ rows +","+customer);
        } catch (SQLException e) {
            System.out.println("DAO 修改顾客失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM customer_table WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rows = pstmt.executeUpdate();
            System.out.println("DAO 删除顾客：" + rows + ",id=" + id);
        } catch (SQLException e) {
            System.out.println("DAO 删除顾客失败");
            e.printStackTrace();
        }
        return rows;
    }
    @Override
    public int count() {
        int count = 0;
        String sql = "SELECT count(*) FROM customer_table";
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(sql);
            while (rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取顾客记录总数失败");
            e.printStackTrace();
        }
        return count;
    }

}
