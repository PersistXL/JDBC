package cn.persistXl.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * jdbc链接数据库
 * @author persistXL
 * @data 2018/4/29 14:51
 */
public class TestJdbc {
    //链接数据库的url

    private String url = "jdbc:mysql://127.0.0.1:3306/oj";

    private String user = "root";
    private String password = "root";
    /**
     * 包括两部分 jdbc协议：数据库子协议：主机：端口/需要链接的数据库
     */


    /**
     *
     * 第一种相连接数据库的方法
     * @throws Exception
     */
@Test
    public void test() throws Exception {
        //创建驱动程序类对象

        Driver driver = new com.mysql.jdbc.Driver();

        //设置一个Properties
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        //链接数据库,返回链接对象
        Connection conn = driver.connect(url,properties);

        System.out.println(conn);
    }

    /**
     * 使用驱动管理器类链接数据库
     * 第二种链接数据库的方法
     */
@Test
    public void test1() throws SQLException {

        //注册驱动程序(可注册多个驱东程序)
        Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);

        //链接到具体的数据库
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    /**
     *最终的简洁版本
     */
@Test
    public void test2() throws SQLException {


       /* Driver driver = new com.mysql.jdbc.Driver();

        //注册驱动程序(可注册多个驱东程序)
        DriverManager.registerDriver(driver);*/

       //通过字节码对象的方式加载静态代码块，从而注册驱动程序

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //链接到具体的数据库
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

}
