package cdu.lll.app3.dao.impl;

import cdu.lll.app3.dao.ProductDao;
import cdu.lll.app3.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends BaseDao implements ProductDao {
    @Override
    public Product get(int id) {
        Product product = null;
        String sql = "SELECT * FROM product_table WHERE id=?";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setSale(rs.getInt("sale"));
                product.setStock(rs.getLong("stock"));
                product.setMarketDate(rs.getLong("marketDate"));
                product.setImgUrl(rs.getString("imgUrl"));
                product.setInfo(rs.getString("info"));
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取商品失败：id=" + id);
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<Product>();

        String sql = "SELECT * FROM product_table order by name";//排序

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setSale(rs.getInt("sale"));
                product.setStock(rs.getLong("stock"));
                product.setMarketDate(rs.getLong("marketDate"));
                product.setImgUrl(rs.getString("imgUrl"));
                product.setInfo(rs.getString("info"));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("DAO 获取商品列表失败");
            e.printStackTrace();
        }
        return productList;
    }

    //分页
    @Override
    public List<Product> findByPage(int start, int num) {
        List<Product> productList = new ArrayList<Product>();
        String sql = "SELECT * FROM product_table ORDER BY marketDate DESC LIMIT ?,?";//降序

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, num);
            rs = pstmt.executeQuery();//执行查询语句 返回一个结果集
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setSale(rs.getInt("sale"));
                product.setStock(rs.getLong("stock"));
                product.setMarketDate(rs.getLong("marketDate"));
                product.setImgUrl(rs.getString("imgUrl"));
                product.setInfo(rs.getString("info"));
                productList.add(product);

            }
        } catch (SQLException e) {
            System.out.println("DAO 分页获取商品列表失败");
            e.printStackTrace();
        }
        return productList;
    }


    @Override
    public int insert(Product product) {
        int rows = 0;
        String sql = "INSERT INTO  product_table (name,price,sale,stock,marketDate,imgUrl,info)VALUES (?,?,?,?,?,?,?)";//降序
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setBigDecimal(2, product.getPrice());
            pstmt.setInt(3, product.getSale());
            pstmt.setLong(4, product.getStock());
            pstmt.setLong(5, product.getMarketDate());
            pstmt.setString(6, product.getImgUrl());
            pstmt.setString(7, product.getInfo());
            rows = pstmt.executeUpdate();
            System.out.println("DAO 添加商品：" + rows + "," + product);
        } catch (SQLException e) {
            System.out.println("DAO 添加商品失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int update(Product product) {
        int rows = 0;
        String sql = "UPDATE  product_table SET name=?,price=?,sale=?,stock=?,markeDate=?,imgUrl=?,info=? WHERE id=?";//降序
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setBigDecimal(2, product.getPrice());
            pstmt.setInt(3, product.getSale());
            pstmt.setLong(4, product.getStock());
            pstmt.setLong(5, product.getMarketDate());
            pstmt.setString(6, product.getImgUrl());
            pstmt.setString(7, product.getInfo());
            pstmt.setInt(8, product.getId());
            rows = pstmt.executeUpdate();
            System.out.println("DAO 修改商品：" + rows + "," + product);
        } catch (SQLException e) {
            System.out.println("DAO 添加商品失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM product_table WHERE id=?";//降序
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rows = pstmt.executeUpdate();
            System.out.println("DAO 删除商品：" + rows + ",id=" + id);
        } catch (SQLException e) {
            System.out.println("DAO 删除商品失败");
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int count() {
        int count = 0;
        String sql = "SELECT count(*) FROM product_table";//降序
        try {
            stmt = conn.createStatement();
            rs=stmt.executeQuery(sql);
           while (rs.next()){
               count=rs.getInt(1);
           }
        } catch (SQLException e) {
            System.out.println("DAO 获取商品记录总数失败");
            e.printStackTrace();
        }
        return count;
    }
}
