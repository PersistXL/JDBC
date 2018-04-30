package cn.persistXl.statement;

/**
 * @author persistXL
 * @data 2018/4/29 19:50
 */

import cn.persistXl.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 使用statement执行DQL语句（查询语句）
 */
public class InsertTest {
    @Test
    public void inseret(){
        Connection conn = null;
        Statement stmt = null;

        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //创建statement
            stmt = conn.createStatement();
            //准备sql
            String sql = "SELECT * FROM student";
            //执行sql
            ResultSet rs = stmt.executeQuery(sql);
            //移动光标
            boolean flag = rs.next();
            if (flag) {
                //取出列的值(根据索引值)

               /*
               int id = rs.getInt(1);
                String name = rs.getString(2);
                String gender = rs.getString(3);
                System.out.println(id+name+gender);
                */

               //根据列的名称

               /*
               int id = rs.getInt("id");
                String name = rs.getString("gender");
                String gender = rs.getString("name");
                System.out.println(id+name+gender);
                */

                //遍历结果
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("gender");
                    String gender = rs.getString("name");
                    System.out.println(id+name+gender);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn, stmt);
        }


    }
}
