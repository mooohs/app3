package cdu.lll.app3.dao.impl;

import cdu.lll.app3.dao.CartDao;
import cdu.lll.app3.model.CartItem;
import cdu.lll.app3.model.Customer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartDaoImpl extends BaseDao implements CartDao {
    @Override
    public Map<Integer, Integer> find(int customerId) {
        Map<Integer,Integer>map =new HashMap<Integer,Integer>();//map集合 接口  hashMap实现类
        String sql = "SELECT * FROM cart_table WHERE customerId=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                map.put(rs.getInt("productId"), rs.getInt("number"));
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取购物车物品失败：customerId=" + customerId);
            e.printStackTrace();
        }
        return map;

    }

    @Override
    public int insert(CartItem cartItem) {
        int rows=0;
        String sql="INSERT INTO cart_table(customerId,productId,number) VALUES(?,?,?) ";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,cartItem.getCustomerId());
            pstmt.setInt(2,cartItem.getProductId());
            pstmt.setInt(3,cartItem.getProductNum());
            rows=pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO 加入购物车失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int update(CartItem cartItem) {
        int rows=0;
        String sql="UPDATE cart_table SET number=? WHERE customerId=? AND productId=?";
        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,cartItem.getProductNum());
            pstmt.setInt(2,cartItem.getCustomerId());
            pstmt.setInt(3,cartItem.getProductId());
            rows=pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO 更新购物车商品数量失败" +cartItem);
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delete(CartItem cartItem) {
        int rows = 0;
        String sql = "DELETE FROM cart_table WHERE customerId=? AND productId=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cartItem.getCustomerId());
            pstmt.setInt(2, cartItem.getProductId());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO 删除购物车商品失败"+ cartItem);
            e.printStackTrace();
        }
        return rows;
    }
//清空购物车
    @Override
    public int delete(int customerId) {
        int rows = 0;
        String sql = "DELETE FROM cart_table WHERE customerId=? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerId);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO 清空购物车失败:customerId"+ customerId);
            e.printStackTrace();
        }
        return rows;
    }
}
