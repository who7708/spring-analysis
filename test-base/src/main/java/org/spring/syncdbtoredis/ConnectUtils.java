package org.spring.syncdbtoredis;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.Driver;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-11
 */
public class ConnectUtils {
    private static final String DRIVE_CLASS_NAME = Driver.class.getName();
    private static final String URL = "jdbc:mysql:///test?useUnicode=true&characterEncoding=utf8";

    private static final String USER = "root";
    private static final String PASSWORD = "root1234";

    public static Connection connect() {
        Connection conn = null;

        //load driver
        try {
            Class.forName(DRIVE_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("load driver failed!");
            e.printStackTrace();
        }

        // connect db
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("connect failed!");
            e.printStackTrace();
        }

        return conn;
    }

    public static DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DRIVE_CLASS_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    // public static DataSource getDataSource2() {
    //     HikariDataSource dataSource = new HikariDataSource();
    //     dataSource.setDriverClassName(DRIVE_CLASS_NAME);
    //     dataSource.setJdbcUrl(URL);
    //     dataSource.setUsername(USER);
    //     dataSource.setPassword(PASSWORD);
    //     return dataSource;
    // }
}