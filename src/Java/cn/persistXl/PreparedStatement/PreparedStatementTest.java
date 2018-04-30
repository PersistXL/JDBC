package cn.persistXl.PreparedStatement;

import cn.persistXl.util.JdbcUtil;
import org.junit.Test;

import java.sql.*;

/**
 * @author persistXL
 * @data 2018/4/29 20:26
 */

/**
 * 使用PreparedStatement执行sql语句
 */
public class PreparedStatementTest {
    /**
     * 增加
     */
    @Test
    public void InsertTest(){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //准备sql
            String sql = "insert into student (name,gender) values (?,?)";
            //?表示一个参数占位符
            //执行预编译sql语句（检查语法）
            stmt = conn.prepareStatement(sql);
            //设置参数值
            stmt.setString(1,"李四");
            stmt.setString(2,"男");
            //发送参数，执行sql
            stmt.executeUpdate();
            System.out.println(stmt);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn, stmt);
        }
    }

    /**
     * 修改
     */
    @Test
    public void UpdateTest(){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //准备sql
            String sql = "UPDATE student SET name=? WHERE id=?";
            //?表示一个参数占位符
            //执行预编译sql语句（检查语法）
            stmt = conn.prepareStatement(sql);
            //设置参数值
            stmt.setString(1,"王五");
            stmt.setInt(2,2);
            //发送参数，执行sql
            stmt.executeUpdate();
            System.out.println(stmt);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn, stmt);
        }
    }

    /**
     * 删除
     */
    @Test
    public void DeleteTest(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //准备sql
            String sql = "DELETE FROM student WHERE id=?";
            //?表示一个参数占位符

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn, stmt, rs);
        }
    }

    /**
     * 查询
     */
    @Test
    public void SelectTest(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //准备预编译sql
            String sql = "select * from student";
            //预编译
            stmt = conn.prepareStatement(sql);
            //执行sql
            rs = stmt.executeQuery();
            boolean flag = rs.next();
            System.out.println(flag);
            if (flag) {
                //便利rs
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String gender = rs.getString("gender");
                    System.out.println(id + "," + name + "," + gender);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn, stmt, rs);
        }
    }
}
