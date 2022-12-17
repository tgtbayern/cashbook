package DAO;

import entity.User;
import util.DB_Connector;

import java.sql.*;
import java.util.ArrayList;


public class userDAO {
    public void add(User user) throws SQLException {
        // 定义连接为null
        Connection con;
        // 定义执行对象为null
        PreparedStatement ps;
        try {
            // 执行连接
            con = DB_Connector.getCon();
            // 执行sql语句
            ps = con.prepareStatement("insert into user(username,password) values (?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            // 处理异常
            e.printStackTrace();
        }
    }

    public boolean isSearch(User user) throws SQLException {
        String str;
        ResultSet rs = null;
        // 定义连接为null
        Connection con;
        // 定义执行对象为null
        PreparedStatement ps;
        try {
            str="select * from user where username="+user.getUsername()+";";
            // 执行连接
            con = DB_Connector.getCon();
            // 执行sql语句
            Statement statement=con.createStatement();
            rs=statement.executeQuery(str);
        } catch (Exception e) {
            // TODO: handle exception
            // 处理异常
            e.printStackTrace();
        }
        // 返回更新后的数据库是否有内容
        return rs.next();
    }

}
