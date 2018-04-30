package cn.persistXl.PreparedStatement;

/**
 * @author persistXL
 * @data 2018/4/29 21:38
 */

import cn.persistXl.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * 模拟用户登录
 */
public class Index {
    //模拟用户输入
    private String name = "admin";
    private String password = "admin";

    /**
     * statement存在sql注入风险
     */
 /*   @Test
    public void idnex(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
           //获取连接
            conn = JdbcUtil.getConnection();
            //创建statement
            stmt = conn.createStatement();
            //准备sql
            String sql = "select * from user where name = '"+name+"' and password = '"+password+"'";
            //执行sql
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                //登录成功
                System.out.println("登录成功");
            } else {
                //登录失败
                System.out.println("登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn, stmt, rs);
        }
    }*/




    @Test
    public void idnex(){
        /**
         *  PreparedStatement有效预防sql注入
         *
         */
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //准备sql
            String sql = "select * from user where name =? and password = ?";
            //创建sql预编译
            stmt = conn.prepareStatement(sql);
            //设置参数
            stmt.setString(1, name);
            stmt.setString(2, password);
            //执行sql
            rs = stmt.executeQuery();
            if (rs.next()) {
                //登录成功
                System.out.println("登录成功");
            } else {
                //登录失败
                System.out.println("登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn, stmt, rs);
        }
    }
}
