package com.persist.util;

import org.apache.commons.beanutils.BeanUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author persistXL
 * @data 2018/5/1 16:13
 * 通用的Dao，自己写的所有的dao都继承此类；
 * 此类定义了2个通用方法
 * 1.更新
 * 2.查询
 */
@SuppressWarnings("ALL")
public class BaseDao {
    //初始化参数

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    /**
     * 更新的通用方法
     * sql 更新的sql语句（update/insert/delete）
     * paramsValues sql语句中占位符对应的值（如果没有占位符，传入null）
     * @param sql
     * @param paramsValues
     */
    public void update(String sql, Object[] paramsValues) {
        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //创建执行命令的stmt对象
            pstmt = conn.prepareStatement(sql);
            //参数元数据：得到占位符参数的个数
            int count = pstmt.getParameterMetaData().getParameterCount();
            //设置占位符参数的值
            if (paramsValues != null && paramsValues.length > 0) {
                //循环给参数赋值
                for (int i=0;i<count;i++) {
                    pstmt.setObject(i + 1, paramsValues[i]);
                }
            }
            //执行更新
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtil.close(conn,pstmt,null);
        }
    }
    /**
     * 查询全部
     */
    public <T> List<T> query(String sql,Object[] paramValues,Class<T> clazz){
        //对象
        T t ;
        //返回的集合
        List<T> list = new ArrayList<>();
        //获取连接
        conn = JdbcUtil.getConnection();
        try {
            //创建pstmt对象
            pstmt = conn.prepareStatement(sql);
            //获取占位符的个数，并设置每个参数的值
            int count = pstmt.getParameterMetaData().getParameterCount();
            if (paramValues != null && paramValues.length > 0) {
                for (int i = 0;i<paramValues.length;i++) {
                    pstmt.setObject(i + 1, paramValues[i]);
                }
            }
            //执行查询
            rs = pstmt.executeQuery();
            //获取结果集与数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //--->获取列的个数
            int columnCount = rsmd.getColumnCount();
            //遍历rs
            while (rs.next()) {
                //要封装的对象
                t = clazz.newInstance();
                //遍历每一行，每一列，封装数据
                for (int i = 0;i<columnCount;i++) {
                    //获取每一列的列的名称
                    String columenName = rsmd.getCatalogName(i + 1);
                    //获取每一列的列的名称，对应的值
                    Object values = rs.getObject(columenName);
                    //封装：设置到t对象的属性中
                    BeanUtils.copyProperty(t, columenName, values);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtil.close(conn, pstmt, null);
        }
    }
}
