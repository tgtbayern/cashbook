package DAO;
import entity.Record;
import util.DB_Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class recordDAO {
    public ArrayList<Record> add(Record record) throws SQLException {
        ResultSet rs = null;
        // 定义连接为null
        Connection con;
        // 定义执行对象为null
        PreparedStatement ps;
        try {
        // 执行连接
            con = DB_Connector.getCon();
        // 执行sql语句
            ps = con.prepareStatement("insert into ledger(time,amount,type,category) values (?,?,?,?)");
            ps.setDate(1,record.getDate());
            ps.setDouble(2, record.getAmount());
            ps.setString(3, String.valueOf(record.getType()));
            ps.setString(4, String.valueOf(record.getCategory()));
            ps.executeUpdate();
            rs=ps.executeQuery("select * from ledger");
        } catch (Exception e) {
        // TODO: handle exception
        // 处理异常
            e.printStackTrace();
        }
        // 返回
        return res(rs);
    }
    public ArrayList<Record> delete(Record record) throws SQLException {
        ResultSet rs = null;
        // 定义连接为null
        Connection con;
        // 定义执行对象为null
        PreparedStatement ps=null;
        try {
            // 执行连接
            con = DB_Connector.getCon();
            // 执行sql语句
            ps = con.prepareStatement("delete from ledger where time='"+
                    record.getDate()+
                    "' and amount="+
                    record.getAmount()+
                    " and type='"+record.getType()+
                    "' and category='"+
                    record.getCategory()+
                    "';");
            ps.executeUpdate();
            rs= ps.executeQuery("select * from ledger");
            System.out.println("in delete we have"+
                    "delete from ledger where time='"+
                    record.getDate()+
                    "' and amount="+
                    record.getAmount()+
                    " and type='"+record.getType()+
                    "' and category='"+
                    record.getCategory()+
                    "';");

        } catch (Exception e) {
            // TODO: handle exception
            // 处理异常
            e.printStackTrace();
        }
        return res(rs);
    }
    public ArrayList<Record> update(Record oldRecord,Record newRecord) throws SQLException {
        delete(oldRecord);
        return add(newRecord);
    }

    public ArrayList<Record> search(Date start, Date end, double min, double max,
                                     Record.Type type,Record.Category category) throws SQLException {
        //最早的日期
        java.sql.Date date1=new java.sql.Date(0);
        //最晚的日期（util中的date）
        Date utilDate = new Date(System.currentTimeMillis());
        //转换成sql date
        java.sql.Date date2 = new java.sql.Date(utilDate.getTime());
        ResultSet rs=null;
        // 定义连接为null
        Connection con;
        // 定义执行对象为null
        PreparedStatement ps = null;
        //被执行的sql语句
        String str;
        try {
            // 执行连接
            con = DB_Connector.getCon();
             //执行sql语句
            if(start==null)
                start=date1;
            if(end==null)
                end=date2;
            if(max==0)
                max=999999;
            if(type==null&&category==null){
                str="select * from ledger where time>='"+
                        start+
                        "' and time<='"+
                        end+
                        "' and amount>="+
                        min+
                        " and amount<="+
                        max+";";
                System.out.println("1");
                System.out.println(str);
            }else if(category!=null&&type==null){
                str="select * from ledger where time>='"+
                        start+
                        "' and time<='"+
                        end+
                        "' and amount>="+
                        min+
                        " and amount<="+
                        max+
                        " and category='"+
                        category+
                        "'";
                System.out.println("2");
                System.out.println(str);
            } else if(type!=null&&category==null){
                str="select * from ledger where time>='"+
                        start+
                        "' and time<='"+
                        end+
                        "' and amount>="+
                        min+
                        " and amount<="+
                        max+
                        " and type='"+
                        type+
                        "'";
                System.out.println("3");
                System.out.println(str);
            }else{
            str="select * from ledger where time>='"+
                    start+
                    "' and time<='"+
                    end+
                    "' and amount>="+
                    min+
                    " and amount<="+
                    max+
                    " and type='"+
                    type+
                    "' and category='"+
                    category+
                    "'";
                System.out.println("4");
                System.out.println(str);
            }
            Statement statement=con.createStatement();
            rs=statement.executeQuery(str);

        } catch (Exception e) {
            // TODO: handle exception
            // 处理异常
            e.printStackTrace();
        }
        return res(rs);
    }
    public ArrayList<Record> res(ResultSet rs) throws SQLException {
        ArrayList<Record> list=new ArrayList<Record>();
        if(rs==null)
            return null;
        while (rs.next()){
            java.sql.Date sDate=rs.getDate("time");
            double amount=rs.getDouble("amount");
            String sType=rs.getString("type");
            String sCategory=rs.getString("category");
            Record record=new Record(sDate,amount,sType,sCategory);
            list.add(record);
        }
        return list;
    }
}
