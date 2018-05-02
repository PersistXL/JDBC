package cn.persistXl.app;

import cn.persistXl.util.JdbcUtil;
import org.junit.Test;

import java.sql.*;

/**
 * @author persistXL
 * @data 2018/4/30 20:25
 */
public class AccountDao {
    //全局参数
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    //转账，没有使用事物
    public void trans() {

        //准备sql
        String sql_zs = "";
        String sql_ls = "";

        try {

            //获取连接
            conn = JdbcUtil.getConnection();
            //使用默认开启的隐士事物

            /**
             * 第一次执行sql
             */
            //预编译sql
            pstmt = conn.prepareStatement(sql_zs);
            //设置参数
            //执行sql
            pstmt.executeUpdate();

            /**
             * 第二次执行sql
             */
            //预编译sql
            pstmt = conn.prepareStatement(sql_ls);
            //设置参数
            //执行sql
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt, null);
        }
    }

    //使用事物
    public void trans1() {
        //准备sql
        String sql_zs = "";
        String sql_ls = "";

        try {

            //获取连接
            conn = JdbcUtil.getConnection();

            //设置事物为手动提交

            conn.setAutoCommit(false);

            /**
             * 第一次执行sql
             */
            //预编译sql
            pstmt = conn.prepareStatement(sql_zs);
            //设置参数
            //执行sql
            pstmt.executeUpdate();

            /**
             * 第二次执行sql
             */
            //预编译sql
            pstmt = conn.prepareStatement(sql_ls);
            //设置参数
            //执行sql
            pstmt.executeUpdate();

        } catch (Exception e) {
            try {
                // 出现异常需要回滚事物
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                //所有操作执行成功，提交事物
                conn.commit();
                JdbcUtil.close(conn, pstmt, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //使用事物，回滚到指定的代码段

    public void trans2() {

        Savepoint sp = null;
        //准备sql
        String sql_zs1 = "";
        String sql_ls1 = "";
        String sql_zs2 = "";
        String sql_ls2 = "";

        try {

            //获取连接
            conn = JdbcUtil.getConnection();
            conn.setAutoCommit(false);

/*** 第一次转账 */


            /**
             * 第一次执行sql
             */
            //预编译sql

            pstmt = conn.prepareStatement(sql_zs1);
            //执行sql

            pstmt.executeUpdate();

            /**
             * 第二次执行sql
             */
            //预编译sql

            pstmt = conn.prepareStatement(sql_ls1);
            //执行sql

            pstmt.executeUpdate();

            //回滚到这个位置
            sp = conn.setSavepoint("trans");

/*** 第二次转账 */

            pstmt = conn.prepareStatement(sql_zs2);
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(sql_ls2);
            pstmt.executeUpdate();

        } catch (Exception e) {
            try {
                //回滚到指定位置
                conn.rollback(sp);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt, null);
        }
    }
}
