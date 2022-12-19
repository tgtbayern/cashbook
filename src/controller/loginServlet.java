package controller;

import DAO.userDAO;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userDAO dao=new userDAO();
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        try {
            if(dao.isSearch(username,password)){
                //用户的账户密码能对上
                request.getSession().setAttribute("message", "login successfully");
            }else{
                //用户的账户密码不能对上
                request.getSession().setAttribute("message", "check username or password!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
