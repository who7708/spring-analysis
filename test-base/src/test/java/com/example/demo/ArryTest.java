package com.example.demo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ArryTest {
    private static String xls2003 = "C:\\students.xls";

    private static String xlsx2007 = "C:\\students.xlsx";

    private static List<Student> readFromXLS2003(String filePath) {
        File excelFile = null;// Excel文件对象
        InputStream is = null;// 输入流对象
        String cellStr = null;// 单元格，最终按字符串处理
        List<Student> studentList = new ArrayList<Student>();// 返回封装数据的List
        Student student = null;// 每一个学生信息对象
        try {
            excelFile = new File(filePath);
            is = new FileInputStream(excelFile);// 获取文件输入流
            HSSFWorkbook workbook2003 = new HSSFWorkbook(is);// 创建Excel2003文件对象
            HSSFSheet sheet = workbook2003.getSheetAt(0);// 取出第一个工作表，索引是0
            // 开始循环遍历行，表头不处理，从1开始
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                student = new Student();// 实例化Student对象
                HSSFRow row = sheet.getRow(i);// 获取行对象
                if (row == null) {// 如果为空，不处理
                    continue;
                }
                // 循环遍历单元格
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    HSSFCell cell = row.getCell(j);// 获取单元格对象
                    if (cell == null) {// 单元格为空设置cellStr为空串
                        cellStr = "";
                    } else if (cell.getCellType() == CellType.BOOLEAN) {// 对布尔值的处理
                        cellStr = String.valueOf(cell.getBooleanCellValue());
                    } else if (cell.getCellType() == CellType.NUMERIC) {// 对数字值的处理
                        cellStr = cell.getNumericCellValue() + "";
                    } else {// 其余按照字符串处理
                        cellStr = cell.getStringCellValue();
                    }
                    // 下面按照数据出现位置封装到bean中
                    if (j == 0) {
                        student.setName(cellStr);
                    } else if (j == 1) {
                        student.setGender(cellStr);
                    } else if (j == 2) {
                        student.setAge(new Double(cellStr).intValue());
                    } else if (j == 3) {
                        student.setSclass(cellStr);
                    } else {
                        student.setScore(new Double(cellStr).intValue());
                    }
                }
                studentList.add(student);// 数据装入List
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// 关闭文件流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return studentList;
    }

    private static void readFromXLSX2007(String filePath) {
        File excelFile = null;// Excel文件对象
        InputStream is = null;// 输入流对象
        String cellStr = null, rowStr = "";// 单元格，最终按字符串处理
        try {
            excelFile = new File(filePath);
            is = new FileInputStream(excelFile);// 获取文件输入流
            XSSFWorkbook workbook2007 = new XSSFWorkbook(is);// 创建Excel2003文件对象
            XSSFSheet sheet = workbook2007.getSheetAt(0);// 取出第一个工作表，索引是0
            // 开始循环遍历行，表头不处理，从1开始
            System.out.println("总行数--" + sheet.getLastRowNum());
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);// 获取行对象
                if (row == null) {// 如果为空，不处理
                    System.out.printf("些表为空");
                    continue;
                }
                Object inputVal = null;
                // 循环遍历单元格
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    XSSFCell cell = row.getCell(j);// 获取单元格对象
                    if (cell == null) {// 单元格为空设置cellStr为空串
                        cellStr = "+";
                    } else if (cell.getCellType() == CellType.BOOLEAN) {// 对布尔值的处理
                        cellStr = String.valueOf(cell.getBooleanCellValue());
                    } else if (cell.getCellType() == CellType.NUMERIC) {// 对数字值的处理
                        long longval = Math.round(cell.getNumericCellValue());
                        cellStr = longval + "Num";
                    } else {// 其余按照字符串处理
                        cellStr = cell.getStringCellValue();
                    }
                    rowStr += cellStr + ";";
                }
                // System.out.println(rowStr);
                rowStr = "";
                //
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// 关闭文件流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    // -Xms200m -Xmx200m -XX:+HeapDumpOnOutOfMemoryError
    public static void main(String[] args) {
        // Student[] ss = new Student[]{
        //         new Student(103, "zhangsan"),
        //         new Student(102, "lishi"),
        //         new Student(101, "wangwu")
        // };
        // for (Student student : ss) {
        //     System.out.println(student);
        // }
        // Arrays.sort(ss);
        // for (Student s : ss) {
        //     System.out.println(s);
        // }

        final int mb = 1024 * 1024;
        final Runtime tr = Runtime.getRuntime();
        System.out.println(tr.totalMemory() / mb);
        System.out.println(tr.freeMemory() / mb);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tr.freeMemory() / mb);
                }
            }
        }).start();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 7; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readFromXLSX2007("C:\\Users\\chmy\\Desktop\\aaa.xlsx");
                }
            }).start();
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) + " ms done!");

    }

}

class Student implements Comparable {
    int num;

    String name;

    private String gender;

    private int age;

    private String sclass;

    private int score;

    public Student() {
    }

    public Student(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {

        return "number=" + num + ", name=" + name;
    }

    public int compareTo(Object o) {
        Student s = (Student) o;
        return Integer.compare(num, s.num);

    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getSclass() {
        return sclass;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}