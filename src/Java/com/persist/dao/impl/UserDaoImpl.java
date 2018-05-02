package com.persist.dao.impl;

import cn.persistXl.util.JdbcUtil;
import com.persist.dao.UserDao;
import com.persist.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author persistXL
 * @data 2018/5/1 12:41
 */
public class UserDaoImpl implements UserDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    @Override
    public void login(User user) {
        //准备sql
        String sql = "select * from user wehere userName = ? and password = ?";

        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //预编译sql
            pstmt = conn.prepareStatement(sql);
            //设置值
            pstmt.setString(1,"userName");
            pstmt.setString(2,"password");
            //执行sql
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn, pstmt, null);
        }
    }

    @Override
    public void register(User user) {

    }

    @Override
    public void checking(String  userName) {

    }
}
