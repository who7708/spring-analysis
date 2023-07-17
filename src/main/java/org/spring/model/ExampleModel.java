package org.spring.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-13
 */
@Data
public class ExampleModel {
    @ExcelIgnore
    private int id;
    /**
     * 考号
     */
    @ExcelProperty(index = 0, value = "考生号")
    private String examNo;

    /**
     * 考生姓名
     */
    @ExcelProperty(index = 1, value = "考生姓名")
    private String studentName;

    /**
     * 毕业学校
     */
    @ExcelProperty(index = 2, value = "毕业学校")
    private String schoolName;

    /**
     * 院校代码
     */
    @ExcelProperty(index = 3, value = "院校代码")
    private String collegeNo;

    /**
     * 院校名称
     */
    @ExcelProperty(index = 4, value = "院校名称")
    private String collegeName;
}
