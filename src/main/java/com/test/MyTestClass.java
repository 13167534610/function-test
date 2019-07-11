package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description:
 * @Author: wangqiang
 * @Date:2019/2/1 14:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring-application.xml"})
public class MyTestClass {
    @Autowired
    private JdbcTemplate testTemplate;
    @Autowired
    private JdbcTemplate test1Template;

    @Test
    @Transactional
    public void test01() throws SQLException {

        Connection connection = testTemplate.getDataSource().getConnection();
        connection.setAutoCommit(false);
        Connection connection1 = test1Template.getDataSource().getConnection();
        connection1.setAutoCommit(false);
        System.out.println("=======================================");
        String sql1 = "INSERT INTO test0 VALUES ('123','456')";
        int update1 = testTemplate.update(sql1);
        System.out.println(update1);
        String sql2 = "INSERT INTO test1 VALUES ('123','456')";
        int update2 = test1Template.update(sql2);
        System.out.println(update2);
        if (update2 == 1){
            connection.rollback();
            connection1.rollback();
        }
        //connection.setAutoCommit(true);
        System.out.println("=======================================");
    }
}
