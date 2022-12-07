package cdu.lll.app3.dao.impl;

import java.sql.*;

public class BaseDao {

    //数据访问层，与底层 MySQL、Oracle、Hbase 等进行数据交互。

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/javaweb?serverTimezone=Asia/Shanghai";
    String dbUsername = "root";
    String dbPassword = "111";

    protected Connection conn = null;
    protected Statement stmt = null;//sql语句执行接口 操作不带参数的sql语句
    protected PreparedStatement pstmt = null;//预编译的statement 带参数
    protected ResultSet rs = null;//执行查询语句 返回一个结果集

    public BaseDao() {
        connect();
    }

    public void connect() {
        try {
            //加载驱动程序
            Class.forName(driver).newInstance();
            //创建数据库连接
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (Exception e) {
           e.printStackTrace();
            System.out.println("数据库连接失败："+ e.getMessage());
        }

    }
//避免重复出现

    public void close() {
        try {
           if (rs!=null&&!rs.isClosed()){
               rs.close();
           }
            if (stmt!=null&&!stmt.isClosed()){
                stmt.close();
            }
            if (pstmt!=null&&!pstmt.isClosed()){
               pstmt.close();
            }
            if (conn!=null&&!conn.isClosed()){
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
