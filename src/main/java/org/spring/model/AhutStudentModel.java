package org.spring.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-13
 */
@Data
public class AhutStudentModel {
    @ExcelIgnore
    private int id;

    @ExcelProperty(index = 0, value = "学号")
    private String studentNo;

    @ExcelProperty(index = 1, value = "姓名")
    private String name;

    @ExcelProperty(index = 2, value = "性别")
    private String sex;

    @ExcelProperty(index = 3, value = "获奖记录")
    private String awardRecord;

    @ExcelProperty(index = 4, value = "语文")
    private BigDecimal chinese;

    @ExcelProperty(index = 5, value = "数学")
    private BigDecimal math;

    @ExcelProperty(index = 6, value = "外语")
    private BigDecimal english;

    @ExcelProperty(index = 7, value = "理综分")
    private BigDecimal comprehensive;

    @ExcelProperty(index = 8, value = "总分")
    private BigDecimal total;
}
