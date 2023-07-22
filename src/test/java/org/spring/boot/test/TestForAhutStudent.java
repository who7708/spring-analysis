package org.spring.boot.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import org.spring.model.AhutStudentModel;
import org.spring.syncdbtoredis.ConnectUtils;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-20
 */
public class TestForAhutStudent {
    @Test
    public void testByConnection() throws InterruptedException {
        System.out.println("===== test1 =====");
        // EasyExcel.read(new File("D:\\data\\excel\\10-12-1507231711433237.xlsx"), AhutStudentModel.class, new ReadListener<AhutStudentModel>() {
        //     @Override
        //     public void invoke(AhutStudentModel data, AnalysisContext context) {
        //         System.out.println(data);
        //     }
        //
        //     @Override
        //     public void doAfterAllAnalysed(AnalysisContext context) {
        //         System.out.println(context.getCustom());
        //     }
        // }).sheet().doRead();

        String sql = "insert into ahut_student(student_no, name, sex, award_record, chinese, math, english, comprehensive, total) values(?,?,?,?,?,?,?,?,?)";

        Connection conn = ConnectUtils.connect();
        EasyExcel.read(new File("D:\\data\\2009计算机新生高考成绩.xls"), AhutStudentModel.class,
                new PageReadListener<AhutStudentModel>(dataList -> {
                    QueryRunner insertRunner = new QueryRunner();
                    for (AhutStudentModel data : dataList) {
                        System.out.println(data);
                        // insertRunner.insertBatch(conn, sql, new ScalarHandler<Integer>(), );
                        try {
                            insertRunner.insert(conn, sql, new ScalarHandler<Integer>(), data.getStudentNo(), data.getName(),
                                    data.getSex(), data.getAwardRecord(), data.getChinese(), data.getMath(), data.getEnglish(),
                                    data.getComprehensive(), data.getTotal());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }))
                .sheet()
                .headRowNumber(1)
                .doRead();

        System.out.println("hello");
        TimeUnit.SECONDS.sleep(100);
    }

    @Test
    public void testByDataSource() throws Exception {
        System.out.println("===== test1 =====");
        String sql = "insert into ahut_student(student_no, name, sex, award_record, chinese, math, english, comprehensive, total) values(?,?,?,?,?,?,?,?,?)";
        DataSource ds = ConnectUtils.getDataSource();
        Connection conn = ds.getConnection();
        EasyExcel.read(new File("D:\\data\\2009计算机新生高考成绩.xls"), AhutStudentModel.class,
                new PageReadListener<AhutStudentModel>(dataList -> {
                    QueryRunner insertRunner = new QueryRunner(ds);
                    for (AhutStudentModel data : dataList) {
                        System.out.println(data);
                        // insertRunner.insertBatch(conn, sql, new ScalarHandler<Integer>(), );
                        try {
                            insertRunner.insert(conn, sql, new ScalarHandler<Integer>(), data.getStudentNo(), data.getName(),
                                    data.getSex(), data.getAwardRecord(), data.getChinese(), data.getMath(), data.getEnglish(),
                                    data.getComprehensive(), data.getTotal());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }))
                .sheet()
                .headRowNumber(1)
                .doRead();

        System.out.println("hello");
        TimeUnit.SECONDS.sleep(100);
    }
}
