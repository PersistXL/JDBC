package com.persist.dao;

import com.persist.entity.User;

/**
 * @author persistXL
 * @data 2018/5/1 12:40
 */
public interface UserDao {
    /**
     * 登录
     * @param user
     */
    void login(User user);

    /**
     * 注册
     * @param user
     */
    void register(User user);

    /**
     * 查重
     * @param userName
     */
    void checking(String userName);
}
