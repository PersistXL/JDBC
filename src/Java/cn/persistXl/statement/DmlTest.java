package cn.persistXl.statement;

import cn.persistXl.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author persistXL
 * @data 2018/4/29 18:06
 */

/**
 * 使用statement执行DML语句
 */
public class DmlTest {
    /**
     * 增加
     */
    private String url = "jdbc:mysql://localhost:3306/jdbc";
    private String name = "root";
    private String password = "root";

    @Test
    public void testInsert() throws SQLException{
        Connection conn = null;
        Statement stmt = null;
        try {
           /* //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接对象
            conn = DriverManager.getConnection(url, name, password);
            */

            conn = JdbcUtil.getConnection();

            //创建Statement对象
            stmt = conn.createStatement();
            //准备sql语句
            String sql = "insert into student(name,gender) VALUES ('zhangsan','nan')";
            //执行sql
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            /*if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }*/

            JdbcUtil.close(conn, stmt);
        }
    }

    @Test
    public void testUpdate() throws SQLException{
        Connection conn = null;
        Statement stmt = null;
        try {
          /*  //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接对象
            conn = DriverManager.getConnection(url, name, password);*/

            conn = JdbcUtil.getConnection();

            //创建Statement对象
            stmt = conn.createStatement();
            //准备sql语句
            String sql = "UPDATE student SET gender='nv' WHERE id='1'";
            //执行sql
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            /*if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }*/
            JdbcUtil.close(conn, stmt);
        }
    }

}
