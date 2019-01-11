package com.note.common.poi;

import com.note.util.DateUtil;
import com.note.util.PropertyUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description: poi实现输出信息到excel文件
 */
public class HFFS01 {
    @Test
    public void testHSSF_base() throws IOException {
        /*
         * 开发步骤：
         * 1、创建一个工作簿
         * 2、创建一个工作表
         * 3、创建一个行对象
         * 4、创建一个单元格对象，指定它的列
         * 5、给单元格设置内容
         * 6、样式进行修饰（跳过）
         * 7、保存，写文件
         * 8、关闭对象
         */
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row nRow = sheet.createRow(7);            //第八行
        Cell nCell = nRow.createCell(4);        //第五列

        nCell.setCellValue("传智播客万年长！");

        OutputStream os = new FileOutputStream("c:\\testpoi.xls");    //excel 2003
        wb.write(os);

        os.flush();
        os.close();
    }

    //带样式
    @Test
    public void testHSSFStyle() throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row nRow = sheet.createRow(7); //第八行
        Cell nCell = nRow.createCell(4); //第五列

        nCell.setCellValue("传智播客万年长！");

        //设置样式
        CellStyle titleStyle = wb.createCellStyle(); //创建单元格样式
        Font titleFont = wb.createFont(); //创建一个字体对象

        titleFont.setFontName("华文隶书"); //设置字体名称
        titleFont.setFontHeightInPoints((short) 24); //设置字体大小

        titleStyle.setFont(titleFont); //绑定字体对象


        nCell.setCellStyle(titleStyle); //设置单元格样式

        Row xRow = sheet.createRow(8);
        Cell xCell = xRow.createCell(6);

        CellStyle textSytle = wb.createCellStyle();
        Font textFont = wb.createFont();

        textFont.setFontName("Times New Roman");
        textFont.setFontHeightInPoints((short) 14);

        textSytle.setFont(textFont);

        xCell.setCellValue("java.itcast.cn");
        xCell.setCellStyle(textSytle);

        OutputStream os = new FileOutputStream("c:\\testpoi.xls");    //excel 2003
        wb.write(os);

        os.flush();
        os.close();
    }

    @Test
    public void rule() throws IOException {
        String xlsFile = "c:/poiHFFS.xls";
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(xlsFile));

        System.out.println("共创建多少样式\t" + wb.getNumCellStyles());
        System.out.println("共创建多少字体\t" + wb.getNumberOfFonts());

        HSSFSheet sheet = wb.getSheetAt(0);

        System.out.println("共多少合并单元格\t" + sheet.getNumMergedRegions());
        System.out.println("起始行数\t" + sheet.getFirstRowNum());
        System.out.println("结束行数\t" + sheet.getLastRowNum() + 1);
    }

    @Test
    public void print() throws Exception {
        String xlsFile = "c:/poiHFFS.xls";

        //STEP 1:打开excel文件
        HSSFWorkbook wb = new HSSFWorkbook(); //创建excel文件
        //HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(xlsFile)); //打开已存在的excel文件

        //STEP 2:打开当前工作簿
        HSSFSheet sheet = wb.createSheet("我的第一个工作簿"); //建立新的sheet对象
        //HSSFSheet sheet = wb.getSheetAt(0); //选择第一个工作簿
        //wb.setSheetName(0, "我的第一个工作簿"); //设置工作簿的名称

        HSSFRow nRow = null;
        HSSFCell nCell = null;

        //STEP 3:创建行对象
        nRow = sheet.createRow((short) 1); //第2行

        //STEP 4:指定列 创建单元格对象
        nCell = nRow.createCell((short) (2)); //第3列

        //STEP 5:指定列 创建单元格对象
        nCell.setCellValue("我是单元格");

        //STEP 6:设置样式
        nCell.setCellStyle(leftStyle(wb));

        //STEP 7:关闭保存excel文件
        FileOutputStream fOut = new FileOutputStream(xlsFile);
        wb.write(fOut);
        fOut.flush();
        fOut.close();

        System.out.println("finish.");
    }


    //设置单元格样式
    private HSSFCellStyle leftStyle(HSSFWorkbook wb) {
        HSSFCellStyle curStyle = wb.createCellStyle();
        HSSFFont curFont = wb.createFont(); //设置字体
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

    private void genDeliveryListFile(DeliveryEntity deliveryEntity) throws IOException {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("/excel/delivery-list-template.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(resourceAsStream);
        HSSFSheet sheet = workbook.getSheetAt(0);
        workbook.setSheetName(0, "发货单");

        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFFont textFont = workbook.createFont(); // 设置字体
        textFont.setFontName("宋体");
        textFont.setCharSet(HSSFFont.DEFAULT_CHARSET); // 设置中文字体，那必须还要再对单元格进行编码设置
        textFont.setFontHeightInPoints((short) 11);
        textStyle.setFont(textFont);

        // 客户名称
        HSSFRow row = sheet.getRow(1);
        HSSFCell cell = row.getCell(1);
        cell.setCellValue(deliveryEntity.getConsumerName());
        HSSFCellStyle cellStyle = cell.getCellStyle();
        cellStyle.setFont(textFont);

        // 联系方式
        cell = row.getCell(3);
        cell.setCellValue(deliveryEntity.getConsumerPhone());
        cellStyle = cell.getCellStyle();
        cellStyle.setFont(textFont);

        // 发货批次号
        cell = row.getCell(5);
        cell.setCellValue(deliveryEntity.getBatchNo());
        cellStyle = cell.getCellStyle();
        cellStyle.setFont(textFont);

        // 地址
        row = sheet.getRow(3);
        cell = row.getCell(1);
        cell.setCellValue(deliveryEntity.getAddress());
        cellStyle = cell.getCellStyle();
        cellStyle.setFont(textFont);

        // 日期
        cell = row.getCell(5);
        cell.setCellValue(DateUtil.date2Str(deliveryEntity.getConsignDate(), "yyyy/MM/dd"));
        cellStyle = cell.getCellStyle();
        cellStyle.setFont(textFont);

        row = sheet.getRow(8);
        cell = row.getCell(0);
        cellStyle = cell.getCellStyle();

        int rowIndex = 8;
        // 从当前至最后行 往后移动size-1行
        sheet.shiftRows(rowIndex + 1, sheet.getLastRowNum(), deliveryEntity.getSnNoList().size() - 1);
        for (int i = 0; i < deliveryEntity.getSnNoList().size(); ++i) {
            row = sheet.createRow(rowIndex++);
            // 序号
            cell = row.createCell(0);
            cell.setCellValue(i + 1);
            cell.setCellStyle(cellStyle);

            // SN编号
            cell = row.createCell(1);
            cell.setCellValue(deliveryEntity.getSnNoList().get(i));
            cell.setCellStyle(cellStyle);

            cell = row.createCell(2);
            cell.setCellValue(deliveryEntity.getBrandName() + "套装");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(3);
            cell.setCellValue("套");
            cell.setCellStyle(cellStyle);

            cell = row.createCell(4);
            cell.setCellValue(1);
            cell.setCellStyle(cellStyle);

            cell = row.createCell(5);
            // cell.setCellValue("");
            cell.setCellStyle(cellStyle);
        }

        row = sheet.getRow(rowIndex++);
        cell = row.getCell(3);
        cell.setCellValue("套");

        cell = row.getCell(4);
        cell.setCellValue(deliveryEntity.getPosNum());

        ++rowIndex;
        row = sheet.getRow(rowIndex++);
        cell = row.getCell(0);
        String cellValue = cell.getStringCellValue();
        cell.setCellValue(MessageFormat.format(cellValue, deliveryEntity.getBrandName()));

        rowIndex += 2;
        row = sheet.getRow(rowIndex++);
        cell = row.getCell(1);
        cell.setCellValue(deliveryEntity.getBrandName() + "机器");

        // 机器数量
        cell = row.getCell(3);
        cell.setCellValue(deliveryEntity.getPosNum());

        row = sheet.getRow(rowIndex++);
        cell = row.getCell(1);
        cell.setCellValue(deliveryEntity.getBrandName() + "电池");

        cell = row.getCell(3);
        cell.setCellValue(deliveryEntity.getBatteryNum());

        row = sheet.getRow(rowIndex++);
        cell = row.getCell(3);
        cell.setCellValue(deliveryEntity.getDataCardNum());

        row = sheet.getRow(rowIndex++);
        cell = row.getCell(3);
        cell.setCellValue(deliveryEntity.getPosNum() + deliveryEntity.getBatteryNum() + deliveryEntity.getDataCardNum());

        cell = row.getCell(4);
        cell.setCellValue(deliveryEntity.getRemark());

        File thisYearPath = getFilePath();
        File outFile = new File(thisYearPath, "发货单" + deliveryEntity.getBatchNo() + ".xls");
        workbook.write(outFile);
    }

    private File getFilePath() {
        // 按年存放文件
        Calendar instance = Calendar.getInstance();
        String year = instance.get(Calendar.YEAR) + "/";
        File thisYearPath = new File(PropertyUtil.getProperty("root") + PropertyUtil.getProperty("deliveryFiles"), year);
        if (!thisYearPath.exists()) {
            thisYearPath.mkdirs();
        }

        return thisYearPath;
    }

}

/**
 * 发货表
 */
class DeliveryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 品牌
     */
    private Long brand;
    /**
     * 机器数量
     */
    private Integer posNum;
    /**
     * 电池数量
     */
    private Integer batteryNum;
    /**
     * 流量卡数量
     */
    private Integer dataCardNum;
    /**
     * 客户名称
     */
    private String consumerName;
    /**
     * 客户联系方式
     */
    private String consumerPhone;

    // 发货地址
    private String address;
    /**
     * 发货日期
     */
    private Date consignDate;
    /**
     * 发货申请人
     */
    private String consignApplicant;
    /**
     * 发货人
     */
    private String consigner;
    /**
     * 备注
     */
    private String remark;

    private List<String> snNoList;

    private String brandName;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：批次号
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * 获取：批次号
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * 设置：品牌
     */
    public void setBrand(Long brand) {
        this.brand = brand;
    }

    /**
     * 获取：品牌
     */
    public Long getBrand() {
        return brand;
    }

    /**
     * 设置：机器数量
     */
    public void setPosNum(Integer posNum) {
        this.posNum = posNum;
    }

    /**
     * 获取：机器数量
     */
    public Integer getPosNum() {
        return posNum;
    }

    /**
     * 设置：电池数量
     */
    public void setBatteryNum(Integer batteryNum) {
        this.batteryNum = batteryNum;
    }

    /**
     * 获取：电池数量
     */
    public Integer getBatteryNum() {
        return batteryNum;
    }

    /**
     * 设置：流量卡数量
     */
    public void setDataCardNum(Integer dataCardNum) {
        this.dataCardNum = dataCardNum;
    }

    /**
     * 获取：流量卡数量
     */
    public Integer getDataCardNum() {
        return dataCardNum;
    }

    /**
     * 设置：客户名称
     */
    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    /**
     * 获取：客户名称
     */
    public String getConsumerName() {
        return consumerName;
    }

    /**
     * 设置：客户联系方式
     */
    public void setConsumerPhone(String consumerPhone) {
        this.consumerPhone = consumerPhone;
    }

    /**
     * 获取：客户联系方式
     */
    public String getConsumerPhone() {
        return consumerPhone;
    }

    /**
     * 设置：发货日期
     */
    public void setConsignDate(Date consignDate) {
        this.consignDate = consignDate;
    }

    /**
     * 获取：发货日期
     */
    public Date getConsignDate() {
        return consignDate;
    }

    /**
     * 设置：发货申请人
     */
    public void setConsignApplicant(String consignApplicant) {
        this.consignApplicant = consignApplicant;
    }

    /**
     * 获取：发货申请人
     */
    public String getConsignApplicant() {
        return consignApplicant;
    }

    /**
     * 设置：发货人
     */
    public void setConsigner(String consigner) {
        this.consigner = consigner;
    }

    /**
     * 获取：发货人
     */
    public String getConsigner() {
        return consigner;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }

    public List<String> getSnNoList() {
        return snNoList;
    }

    public void setSnNoList(List<String> snNoList) {
        this.snNoList = snNoList;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
