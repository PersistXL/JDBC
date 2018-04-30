package cn.persistXl.app;

import org.junit.Test;

/**
 * @author persistXL
 * @data 2018/4/30 20:24
 */
public class App {

    @Test
    public void testname() throws Exception{
        //转账
        AccountDao accountDao = new AccountDao();
        accountDao.trans();
        //使用事物，转账

    }
}
