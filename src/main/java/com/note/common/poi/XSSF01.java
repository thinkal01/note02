package com.note.common.poi;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description: poi实现输出信息到excel文件
 */
public class XSSF01 {
    @Test
    public void rule() throws IOException {
        String xlsFile = "c:/poiXFFS.xlsx";
        Workbook wb = new XSSFWorkbook(new FileInputStream(xlsFile));

        System.out.println("共创建多少样式\t" + wb.getNumCellStyles());
        System.out.println("共创建多少字体\t" + wb.getNumberOfFonts());

        Sheet sheet = wb.getSheetAt(0);

        System.out.println("共多少合并单元格\t" + sheet.getNumMergedRegions());
        System.out.println("起始行数\t" + sheet.getFirstRowNum());
        System.out.println("结束行数\t" + sheet.getLastRowNum() + 1);
    }

    /*
     * dom4j-1.6.1.jar
     * poi-3.9-20121203.jar
     * poi-ooxml-3.9-20121203.jar
     * poi-ooxml-schemas-3.9-20121203.jar
     * stax-api-1.0.1.jar
     * xmlbeans-2.3.0.jar
     */
    @Test
    public void print() throws Exception {
        String xlsFile = "c:/clroleprice.xlsx";

        //STEP 1:打开excel文件
        Workbook wb = new XSSFWorkbook();                                    //创建excel文件
        //Workbook wb = new XSSFWorkbook(new FileInputStream(xlsFile));		//打开已存在的excel文件

        //STEP 2:打开当前工作簿
        Sheet sheet = wb.createSheet("我的第一个工作簿");        //建立新的sheet对象
        //Sheet sheet = wb.getSheetAt(0);						//选择第一个工作簿
        //wb.setSheetName(0, "我的第一个工作簿");					//设置工作簿的名称

        Row nRow = null;
        Cell nCell = null;

        //STEP 3:创建行对象
        nRow = sheet.createRow((short) 1);                        //第2行

        //STEP 4:指定列 创建单元格对象
        nCell = nRow.createCell((short) (2));                    //第3列

        //STEP 5:指定列 创建单元格对象
        nCell.setCellValue("我是单元格传智播客");

        //STEP 6:设置样式
        nCell.setCellStyle(leftStyle(wb));

        //STEP 7:关闭保存excel文件
        FileOutputStream fOut = new FileOutputStream(xlsFile);
        wb.write(fOut);
        fOut.flush();
        fOut.close();
    }

    @Test
    public void testprint() throws Exception {
        String xlsFile = "c:/clroleprice.xlsx";

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("我的第一个工作簿");

        Row nRow = null;
        Cell nCell = null;

        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
            nRow = sheet.createRow(i);

            for (int j = 0; j < 20; j++) {
                nCell = nRow.createCell(j);
                nCell.setCellValue("我是单元格传智播客");
            }
        }

        //STEP 6:设置样式
        nCell.setCellStyle(leftStyle(wb));

        //STEP 7:关闭保存excel文件
        FileOutputStream fOut = new FileOutputStream(xlsFile);
        wb.write(fOut);
        fOut.flush();
        fOut.close();
    }

    //设置单元格样式
    private CellStyle leftStyle(Workbook wb) {
        CellStyle curStyle = wb.createCellStyle();
        Font curFont = wb.createFont(); //设置字体
        //curFont.setFontName("Times New Roman"); //设置英文字体
        curFont.setFontName("微软雅黑"); //设置英文字体
        curFont.setCharSet(HSSFFont.DEFAULT_CHARSET); //设置中文字体，那必须还要再对单元格进行编码设置
        curFont.setFontHeightInPoints((short) 10); //字体大小
        curStyle.setFont(curFont);

        curStyle.setBorderTop(BorderStyle.THICK); //粗实线
        curStyle.setBorderBottom(BorderStyle.THIN); //实线
        curStyle.setBorderLeft(BorderStyle.MEDIUM); //比较粗实线
        curStyle.setBorderRight(BorderStyle.THIN); //实线

        curStyle.setWrapText(true); //换行
        curStyle.setAlignment(HorizontalAlignment.RIGHT); //横向具右对齐
        curStyle.setVerticalAlignment(VerticalAlignment.CENTER); //单元格垂直居中

        return curStyle;
    }

}