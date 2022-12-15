package util;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 封装一个对数据库的连接
 * @getCon（）方法返回一个对数据库的连接，后续对这个连接
 */
public class DB_Connector {
    private static String cname = "com.mysql.cj.jdbc.Driver";
    private static String uname = "root";
    private static String upwd = "020809";
    private static String url ="jdbc:mysql://localhost:3306/accounting_ledger";
    /**
     * 静态驱动
     */
    static {
        try {
        // 创建驱动
            Class.forName(cname);
        } catch (Exception e) {
        // 处理异常
            e.printStackTrace();
        }
    }
    /**
     * 静态连接
     * @return 连接
     */
    public static Connection getCon() {
        // 定义连接
        Connection con = null;
        try {
        // 连接驱动
            con = DriverManager.getConnection(url, uname, upwd);
            if(!con.isClosed())
                System.out.println("Successfully connect to the DB!");
        } catch (Exception e) {
        // 处理异常
            e.printStackTrace();
        }
        // 返回连接
        return con;
    }
}
