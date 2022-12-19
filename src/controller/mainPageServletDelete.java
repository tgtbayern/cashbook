package controller;

import DAO.recordDAO;
import entity.Record;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/delete")
public class mainPageServletDelete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Record> list;
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        recordDAO dao=new recordDAO();
        //读取从html传来的参数并类型转换
        java.sql.Date date= Date.valueOf(request.getParameter("date"));
        double amount= Double.parseDouble(request.getParameter("amount"));
        Record.Type type= Record.Type.valueOf(request.getParameter("type"));
        Record.Category category= Record.Category.valueOf(request.getParameter("category"));
        Record record=new Record(date,amount,type,category);
        //执行search方法
        try {
            list=dao.delete(record);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //把数据封装在arraylist中发送回html
        request.getSession().setAttribute("result3", list);
        for (Record item:list
        ) {
            System.out.println(item);
        }
    }
}
