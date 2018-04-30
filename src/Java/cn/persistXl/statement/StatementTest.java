package cn.persistXl.statement;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author persistXL
 * @data 2018/4/29 16:57
 */
public class StatementTest {
    private String url = "jdbc:mysql://localhost:3306/jdbc";
    private String user = "root";
    private String password = "root";

    /**
     *
     * 执行DDL语句
     */
    @Test
    public void test(){
        int count = 0;
        Connection conn = null;
        Statement stmt = null;
        try {
            //连接数据库，注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接对象
            conn = DriverManager.getConnection(url, user, password);
            //创建statement对象
            stmt = conn.createStatement();
            //准备sql
            String sql = "CREATE TABLE student(id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(20),gender VARCHAR(4))";
            //发送sql语句并执行,得到返回的结果
            count = stmt.executeUpdate(sql);
            //输出
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接(顺序：先打开都关闭)
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
