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

@WebServlet("/search")
public class mainPageServletSearch extends HttpServlet {
    Date start;
    Date end;
    double min;
    double max;
    Record.Type type;
    Record.Category category;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("xxx");
        ArrayList<Record> list;
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        recordDAO dao=new recordDAO();
        //读取从html传来的参数并类型转换
//        if(request.getParameter("start")==null)
//            start=new Date(0);
//        else
//            start= Date.valueOf(request.getParameter("start"));
//
//        if(request.getParameter("end")==null)
//            end=new Date(3195,12,31);
//        else
//            end= Date.valueOf(request.getParameter("start"));
//
       if(request.getParameter("min")==null)
            min=0;
        else
            min= Double.parseDouble(request.getParameter("min"));

        if(request.getParameter("max")==null)
            max=9999999;
        else
            max= Double.parseDouble(request.getParameter("max"));

//        if(request.getParameter("type")==null)
//            type=null;
//        else
//            type= Record.Type.valueOf(request.getParameter("type"));
//
//        if(request.getParameter("category")==null)
//            category=null;
//        else
//            category= Record.Category.valueOf(request.getParameter("category"));

        //执行search方法
        try {
            list=dao.search(start,end,min,max,type,category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //把数据封装在arraylist中发送回html
        request.getSession().setAttribute("result2", list);
        for (Record item:list
        ) {
            System.out.println(item);
        }
    }
}
