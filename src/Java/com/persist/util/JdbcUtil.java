package com.persist.util;

/**
 * @author persistXL
 * @data 2018/4/29 18:27
 */

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * jdbc 的工具类
 */
public class JdbcUtil {
    private static String url = null;
    private static String name = null;
    private static String password = null;
    private static String driverClass = null;

    /**
     * 静态代码块（只加载一次）
     */
    static {
        try {
            //读取db.properties文件
            Properties props = new Properties();
            /**
             *  . 代表java命令运行的目录
             *  在java项目下，. java命令的运行目录从项目的根目录开始
             *  在web项目下，  . java命令的而运行目录从tomcat/bin目录开始
             *  所以不能使用点.
             */
            //FileInputStream in = new FileInputStream("./src/db.properties");          //若使用java项目时没有问题，若使用web项目时文件的路径就有问题

            /**
             * 使用类路径的读取方式
             *  / : 斜杠表示classpath的根目录
             *     在java项目下，classpath的根目录从bin目录开始
             *     在web项目下，classpath的根目录从WEB-INF/classes目录开始
             */
            InputStream in = JdbcUtil.class.getResourceAsStream("/db.properties");

            //加载文件
            props.load(in);
            //读取信息
            url = props.getProperty("url");
            name = props.getProperty("user");
            password = props.getProperty("password");
            driverClass = props.getProperty("driverClass");

            //注册驱动程序
            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("驱动程序注册失败");
        }

    }

    /**
     * 抽取获取连接对象的方法
     */
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, name, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源的方法
     */
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
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
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection conn, PreparedStatement pstmt) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
