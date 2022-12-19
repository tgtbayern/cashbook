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

@WebServlet("/update")
public class mainPageServletUpdate extends HttpServlet {
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
        Date date1= Date.valueOf(request.getParameter("date1"));
        double amount1= Double.parseDouble(request.getParameter("amount1"));
        Record.Type type1= Record.Type.valueOf(request.getParameter("type1"));
        Record.Category category1= Record.Category.valueOf(request.getParameter("category1"));
        Record oldRecord=new Record(date1,amount1,type1,category1);

        Date date2= Date.valueOf(request.getParameter("date2"));
        double amount2= Double.parseDouble(request.getParameter("amount2"));
        Record.Type type2= Record.Type.valueOf(request.getParameter("type2"));
        Record.Category category2= Record.Category.valueOf(request.getParameter("category2"));
        Record newRecord=new Record(date2,amount2,type2,category2);
        //执行search方法
        try {
            list=dao.update(oldRecord,newRecord);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //把数据封装在arraylist中发送回html
        request.getSession().setAttribute("result4", list);
        for (Record item:list
        ) {
            System.out.println(item);
        }
    }
}
