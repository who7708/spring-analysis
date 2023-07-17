package org.spring.boot.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import org.spring.model.ExampleModel;
import org.spring.syncdbtoredis.ConnectUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-13
 */
public class TestEasyExcel {
    @Test
    public void test1() throws InterruptedException {
        System.out.println("===== test1 =====");
        // EasyExcel.read(new File("D:\\data\\excel\\10-12-1507231711433237.xlsx"), ExampleModel.class, new ReadListener<ExampleModel>() {
        //     @Override
        //     public void invoke(ExampleModel data, AnalysisContext context) {
        //         System.out.println(data);
        //     }
        //
        //     @Override
        //     public void doAfterAllAnalysed(AnalysisContext context) {
        //         System.out.println(context.getCustom());
        //     }
        // }).sheet().doRead();

        String sql = "insert into exam_info(exam_no, student_name, school_name, college_no, college_name) values(?,?,?,?,?)";

        Connection conn = ConnectUtils.connect();
        EasyExcel.read(new File("D:\\data\\excel\\10-12-1507231711433237.xlsx"), ExampleModel.class,
                new PageReadListener<ExampleModel>(dataList -> {
                    QueryRunner insertRunner = new QueryRunner();
                    for (ExampleModel data : dataList) {
                        System.out.println(data);
                        // insertRunner.insertBatch(conn, sql, new ScalarHandler<Integer>(), );
                        try {
                            insertRunner.insert(conn, sql, new ScalarHandler<Integer>(), data.getExamNo(), data.getStudentName(),
                                    data.getSchoolName(), data.getCollegeNo(), data.getCollegeName());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                })).sheet().doRead();

        System.out.println("hello");
        TimeUnit.SECONDS.sleep(100);
    }
}
