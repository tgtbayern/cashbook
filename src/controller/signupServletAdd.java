package controller;

import DAO.userDAO;
import entity.Record;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
@WebServlet("/signUpAdd")
public class signupServletAdd extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Record> list;
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        userDAO dao=new userDAO();
        //读取从html传来的参数并类型转换
        String username= request.getParameter("username");
        String password=request.getParameter("password");
        User user=new User(username,password);
        //执行search方法
        try {
            if(!dao.isSearch(username)){
                //用户的数据没问题
                dao.add(user);
                request.getSession().setAttribute("message", "add successfully");
            }else{
                //用户的数据有问题
                request.getSession().setAttribute("message", "same username!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}