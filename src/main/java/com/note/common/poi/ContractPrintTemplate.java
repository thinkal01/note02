package com.note.common.poi;

import com.note.util.DownloadUtil;
import com.note.util.UtilFuns;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 根据模板文件打印，复制sheet，然后写单元格值；最终删除模板sheet，不留痕迹；这样样式工作可视化，无需代码，同时也加快展现效率；
 */
public class ContractPrintTemplate {

    public void print(ContractVO contract, String path, HttpServletResponse response) throws ParseException, IOException {
        /*
         * 1.获取数据
         * 2.封装每页数据
         * 3.打印
         */
        UtilFuns utilFuns = new UtilFuns();
        List<Map> pageList = new ArrayList<>();
        Map<String, String> pageMap = null;

        String _stars = "";
        for (int i = 0; i < contract.getImportNum(); i++) {
            _stars += "★";
        }

        List<ContractProductVO> cpList = contract.getContractProducts();
        ContractProductVO cp = null;
        for (int i = 0; i < cpList.size(); i++) {
            pageMap = new HashMap<>();
            cp = cpList.get(i);

            pageMap.put("Offeror", "收 购 方：" + contract.getOfferor());
            pageMap.put("ContractNo", "合 同 号：" + contract.getContractNo());
            pageMap.put("SigningDate", "签单日期：" + UtilFuns.formatDateTimeCN(UtilFuns.dateTimeFormat(contract.getSigningDate())));

            pageMap.put("Factory", "生产工厂：" + UtilFuns.convertNull(cp.getFactory().getFullName()));
            pageMap.put("Contacts", "联 系 人：" + cp.getFactory().getContacts());
            pageMap.put("Phone", "电    话：" + cp.getFactory().getPhone());

            pageMap.put("InputBy", "制单：" + contract.getInputBy());
            pageMap.put("CheckBy", "审单：" + utilFuns.fixSpaceStr(contract.getCheckBy(), 26) + "验货员：" + contract.getInspector());

            pageMap.put("Crequest", contract.getCrequest());


            pageMap.put("ProductDescTitle", _stars + " 货物描述");

            pageMap.put("ProductImage", cp.getProductImage());
            pageMap.put("ProductDesc", cp.getProductDesc());
            pageMap.put("Cnumber", cp.getCnumber().toString());
            if (cp.getPackingUnit().equals("PCS")) {
                pageMap.put("PackingUnit", "只");
            } else if (cp.getPackingUnit().equals("SETS")) {
                pageMap.put("PackingUnit", "套");
            }
            pageMap.put("Price", cp.getPrice().toString());
            pageMap.put("ProductNo", cp.getProductNo());

            String fullName = cp.getFactory().getFullName();
            if (contract.getPrintStyle().equals("2")) {        //按两款货物打印，才做
                //处理第二款货物
                i++;
                if (i < cpList.size()) {            //判断第二款货物是否有
                    cp = cpList.get(i);
                    if (cp.getFactory().getFullName().equals(fullName)) {
                        pageMap.put("ProductImage2", cp.getProductImage());
                        pageMap.put("ProductDesc2", cp.getProductDesc());
                        pageMap.put("Cnumber2", cp.getCnumber().toString());
                        if (cp.getPackingUnit().equals("PCS")) {
                            pageMap.put("PackingUnit2", "只");
                        } else if (cp.getPackingUnit().equals("SETS")) {
                            pageMap.put("PackingUnit2", "套");
                        }
                        pageMap.put("Price2", cp.getPrice().toString());
                        pageMap.put("ProductNo2", cp.getProductNo());
                    }
                } else {
                    i--;                        //如果第二款货物厂家不同，则必须新起一页
                }
            }

            pageList.add(pageMap);                //存储一页数据
        }

        /*
         * 打开模板，复制sheet，另存
         */
        Workbook wb = new HSSFWorkbook(new FileInputStream(new File(path + "make/xlsprint/tCONTRACTVO.xls")));
        for (int i = 0; i < pageList.size(); i++) {
            wb.cloneSheet(0);                                //复制工作簿
            wb.setSheetName(i + 1, "C" + (i + 1) + "");        //设置工作簿名称
        }


        //设置相同内容
        for (int i = 0; i < pageList.size(); i++) {
            int rowNo = 6;
            int colNo = 0;
            Row nRow = null;
            Cell nCell = null;
            Map<String, String> printMap = pageList.get(i);

            Sheet sheet = wb.getSheetAt(i + 1);                        //定位到当前工作表
            sheet.setForceFormulaRecalculation(true);                //强制公式自动计算，利用模板时，模板中的公式不会因值发生变化而自动计算。

            nRow = sheet.getRow(rowNo++);
            nCell = nRow.getCell(1);
            nCell.setCellValue(printMap.get("Offeror"));
            nCell = nRow.getCell(5);
            nCell.setCellValue(printMap.get("Factory"));

            nRow = sheet.getRow(rowNo++);
            nCell = nRow.getCell(1);
            nCell.setCellValue(printMap.get("ContractNo"));
            nCell = nRow.getCell(5);
            nCell.setCellValue(printMap.get("Contacts"));

            nRow = sheet.getRow(rowNo++);
            nCell = nRow.getCell(1);
            nCell.setCellValue(printMap.get("SigningDate"));
            nCell = nRow.getCell(5);
            nCell.setCellValue(printMap.get("Phone"));

            nRow = sheet.getRow(rowNo++);
            nCell = nRow.getCell(4);
            nCell.setCellValue(printMap.get("ProductDescTitle"));

            nRow = sheet.getRow(rowNo++);
            nCell = nRow.getCell(1);
            nCell.setCellValue(printMap.get("ProductImage"));
            if (UtilFuns.isNotEmpty(printMap.get("ProductImage"))) {
                this.setPicture(path + "/ufiles/jquery/" + printMap.get("ProductImage"), sheet, rowNo - 1, 1, rowNo, 3);        //插入产品图片
            }

            nCell = nRow.getCell(4);
            nCell.setCellValue(printMap.get("ProductDesc"));
            nCell = nRow.getCell(5);
            nCell.setCellValue(Integer.parseInt(printMap.get("Cnumber")));
            nCell = nRow.getCell(6);
            nCell.setCellValue(printMap.get("PackingUnit"));
            nCell = nRow.getCell(7);
            nCell.setCellValue(Double.parseDouble(printMap.get("Price")));

            nRow = sheet.getRow(rowNo++);
            nCell = nRow.getCell(1);
            nCell.setCellValue(printMap.get("ProductNo"));

            if (printMap.get("ProductNo2") != null) {            //第二款货不存在
                nRow = sheet.getRow(rowNo++);
                nCell = nRow.getCell(1);
                nCell.setCellValue(printMap.get("ProductImage2"));
                if (UtilFuns.isNotEmpty(printMap.get("ProductImage2"))) {
                    this.setPicture(path + "/ufiles/jquery/" + printMap.get("ProductImage2"), sheet, rowNo - 1, 1, rowNo, 3);        //插入产品图片
                }

                nCell = nRow.getCell(4);
                nCell.setCellValue(printMap.get("ProductDesc2"));
                nCell = nRow.getCell(5);
                nCell.setCellValue(printMap.get("Cnumber2"));
                nCell = nRow.getCell(6);
                nCell.setCellValue(printMap.get("PackingUnit2"));
                nCell = nRow.getCell(7);
                nCell.setCellValue(printMap.get("Price2"));

                nRow = sheet.getRow(rowNo++);
                nCell = nRow.getCell(1);
                nCell.setCellValue(printMap.get("ProductNo2"));

            } else {                //没有第二款货物时空着
                rowNo++;
                rowNo++;
            }

            nRow = sheet.getRow(rowNo++);
            nCell = nRow.getCell(1);
            nCell.setCellValue(printMap.get("InputBy"));
            nCell = nRow.getCell(4);
            nCell.setCellValue(printMap.get("CheckBy"));

            rowNo++;
            nRow = sheet.getRow(rowNo++);
            nCell = nRow.getCell(1);
            nCell.setCellValue("  " + printMap.get("Crequest"));
        }

        wb.removeSheetAt(0);                    //删除模板sheet

        DownloadUtil du = new DownloadUtil();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        wb.write(bos);
        bos.close();
        du.download(bos, response, "购销合同.xls");
        wb.setFirstVisibleTab(1);
    }

    //处理图片，excel中图片是单独对象存放
    public void setPicture(String pic, Sheet sheet, int startRow, int startCol, int stopRow, int stopCol) throws IOException {
        File imageFile = new File(pic);
        if (imageFile.exists()) {
            InputStream is = new FileInputStream(new File(pic));
            byte[] bytes = IOUtils.toByteArray(is);
            int pictureIdx = sheet.getWorkbook().addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);        //扩展名可为.jpg/.jpeg/.png
            is.close();

            Drawing drawing = sheet.createDrawingPatriarch();    // Create the drawing patriarch.  This is the top level container for all shapes.
            //前面四个参数是图片偏移量
            ClientAnchor anchor = new HSSFClientAnchor(20, 1, 1020, 0, (short) startCol, startRow, (short) stopCol, stopRow);    //add a picture shape
            anchor.setRow1(startRow);                            //set position corner of the picture
            anchor.setCol1(startCol);
            anchor.setRow2(stopRow);
            anchor.setCol2(stopCol);

            drawing.createPicture(anchor, pictureIdx);
        }
    }


    public void print() throws ParseException, FileNotFoundException, IOException {

        /*
         * 打开模板，复制sheet，另存
         */
        Workbook wb = new HSSFWorkbook(new FileInputStream(new File("c:\\tCONTRACTVO.xls")));
        for (int i = 0; i < 2; i++) {
            wb.cloneSheet(0);                                //复制工作簿
            wb.setSheetName(i + 1, "Sheet(" + (i + 1) + ")");        //设置工作簿名称
        }

        //设置相同内容
        for (int i = 1; i < wb.getNumberOfSheets(); i++) {
            Row nRow = null;
            Cell nCell = null;

            Sheet sheet = wb.getSheetAt(i);                        //定位到当前工作表
            System.out.println(sheet.getLastRowNum());

            nRow = sheet.getRow(6);
            nCell = nRow.getCell(1);
            nCell.setCellValue("Offeror");

            System.out.println("==================================" + i);
        }

        wb.write(new FileOutputStream("c:\\y.xls"));
    }
}

class Contract {
    private String id;

    private String cpnum;
    private String extnum;

    private String offeror;
    private String contractNo;
    private java.util.Date signingDate;
    private String inputBy;
    private String checkBy;
    private String inspector;
    private Double totalAmount;
    private Integer importNum;
    private String crequest;
    private String customName;
    private java.util.Date deliveryPeriod;
    private java.util.Date shipTime;
    private String tradeTerms;
    private String remark;
    private String printStyle;
    private Integer oldState;
    private Integer state;
    private Integer outState;

    private String createBy;
    private String createDept;
    private java.util.Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferor() {
        return offeror;
    }

    public void setOfferor(String offeror) {
        this.offeror = offeror;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public java.util.Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(java.util.Date signingDate) {
        this.signingDate = signingDate;
    }

    public String getInputBy() {
        return inputBy;
    }

    public void setInputBy(String inputBy) {
        this.inputBy = inputBy;
    }

    public String getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getImportNum() {
        return importNum;
    }

    public void setImportNum(Integer importNum) {
        this.importNum = importNum;
    }

    public String getCrequest() {
        return crequest;
    }

    public void setCrequest(String crequest) {
        this.crequest = crequest;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public java.util.Date getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(java.util.Date deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public java.util.Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(java.util.Date shipTime) {
        this.shipTime = shipTime;
    }

    public String getTradeTerms() {
        return tradeTerms;
    }

    public void setTradeTerms(String tradeTerms) {
        this.tradeTerms = tradeTerms;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(String printStyle) {
        this.printStyle = printStyle;
    }

    public Integer getOldState() {
        return oldState;
    }

    public void setOldState(Integer oldState) {
        this.oldState = oldState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOutState() {
        return outState;
    }

    public void setOutState(Integer outState) {
        this.outState = outState;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getCpnum() {
        return cpnum;
    }

    public void setCpnum(String cpnum) {
        this.cpnum = cpnum;
    }

    public String getExtnum() {
        return extnum;
    }

    public void setExtnum(String extnum) {
        this.extnum = extnum;
    }
}

class ContractVO {
    private String id;
    private List<ContractProductVO> contractProducts;        //货物的集合

    private String cpnum;
    private String extnum;

    private String offeror;
    private String contractNo;
    private java.util.Date signingDate;
    private String inputBy;
    private String checkBy;
    private String inspector;
    private Double totalAmount;
    private Integer importNum;
    private String crequest;
    private String customName;
    private java.util.Date deliveryPeriod;
    private java.util.Date shipTime;
    private String tradeTerms;
    private String remark;
    private String printStyle;
    private Integer oldState;
    private Integer state;
    private Integer outState;

    private String createBy;
    private String createDept;
    private java.util.Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferor() {
        return offeror;
    }

    public void setOfferor(String offeror) {
        this.offeror = offeror;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public java.util.Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(java.util.Date signingDate) {
        this.signingDate = signingDate;
    }

    public String getInputBy() {
        return inputBy;
    }

    public void setInputBy(String inputBy) {
        this.inputBy = inputBy;
    }

    public String getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getImportNum() {
        return importNum;
    }

    public void setImportNum(Integer importNum) {
        this.importNum = importNum;
    }

    public String getCrequest() {
        return crequest;
    }

    public void setCrequest(String crequest) {
        this.crequest = crequest;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public java.util.Date getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public void setDeliveryPeriod(java.util.Date deliveryPeriod) {
        this.deliveryPeriod = deliveryPeriod;
    }

    public java.util.Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(java.util.Date shipTime) {
        this.shipTime = shipTime;
    }

    public String getTradeTerms() {
        return tradeTerms;
    }

    public void setTradeTerms(String tradeTerms) {
        this.tradeTerms = tradeTerms;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(String printStyle) {
        this.printStyle = printStyle;
    }

    public Integer getOldState() {
        return oldState;
    }

    public void setOldState(Integer oldState) {
        this.oldState = oldState;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOutState() {
        return outState;
    }

    public void setOutState(Integer outState) {
        this.outState = outState;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getCpnum() {
        return cpnum;
    }

    public void setCpnum(String cpnum) {
        this.cpnum = cpnum;
    }

    public String getExtnum() {
        return extnum;
    }

    public void setExtnum(String extnum) {
        this.extnum = extnum;
    }

    public List<ContractProductVO> getContractProducts() {
        return contractProducts;
    }

    public void setContractProducts(List<ContractProductVO> contractProducts) {
        this.contractProducts = contractProducts;
    }

}

class ContractProductVO {
    private String id;

    private Contract contract;        //将复杂的关联关系变成单表操作
    private List<ExtCproductVO> extCproducts;        //和附件一对多
    private Factory factory;        //和生产厂家多对一

    private String productNo;
    private String productImage;
    private String productDesc;
    private Integer cnumber;
    private Integer outNumber;
    private String loadingRate;            //装率
    private Integer boxNum;                //箱数
    private String packingUnit;            //包装单位
    private Double price;
    private Double amount;
    private Integer finished;
    private String exts;
    private Integer orderNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getCnumber() {
        return cnumber;
    }

    public void setCnumber(Integer cnumber) {
        this.cnumber = cnumber;
    }

    public Integer getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(Integer outNumber) {
        this.outNumber = outNumber;
    }

    public String getLoadingRate() {
        return loadingRate;
    }

    public void setLoadingRate(String loadingRate) {
        this.loadingRate = loadingRate;
    }

    public Integer getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Integer boxNum) {
        this.boxNum = boxNum;
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public String getExts() {
        return exts;
    }

    public void setExts(String exts) {
        this.exts = exts;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<ExtCproductVO> getExtCproducts() {
        return extCproducts;
    }

    public void setExtCproducts(List<ExtCproductVO> extCproducts) {
        this.extCproducts = extCproducts;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

}

class Factory {
    private String id;    //将主键都映射成id
    private String fullName;
    private String factoryName;
    private String contacts;
    private String phone;
    private String mobile;
    private String fax;
    private String cnote;
    private String inspector;
    private Integer orderNo;
    private String state;

    private String createBy;
    private String createDept;
    private java.util.Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCnote() {
        return cnote;
    }

    public void setCnote(String cnote) {
        this.cnote = cnote;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

class ExtCproductVO {
    private String id;

    private ContractProduct contractProduct;
    private Factory factory;

    private String ctype;
    private String productNo;
    private String productImage;
    private String productDesc;
    private Integer cnumber;
    private String packingUnit;            //包装单位
    private Double price;
    private Double amount;
    private String productRequest;
    private Integer orderNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getCnumber() {
        return cnumber;
    }

    public void setCnumber(Integer cnumber) {
        this.cnumber = cnumber;
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProductRequest() {
        return productRequest;
    }

    public void setProductRequest(String productRequest) {
        this.productRequest = productRequest;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public ContractProduct getContractProduct() {
        return contractProduct;
    }

    public void setContractProduct(ContractProduct contractProduct) {
        this.contractProduct = contractProduct;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}

class ContractProduct {
    private String id;

    //private Contract contract;		//将复杂的关联关系变成单表操作
    private String contractId;            //关联关系的表（外键），都成为普通字段
    private String factoryId;

    private String factoryName;
    private String productNo;
    private String productImage;
    private String productDesc;
    private Integer cnumber;
    private Integer outNumber;
    private String loadingRate;            //装率
    private Integer boxNum;                //箱数
    private String packingUnit;            //包装单位
    private Double price;
    private Double amount;
    private Integer finished;
    private String exts;
    private Integer orderNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getCnumber() {
        return cnumber;
    }

    public void setCnumber(Integer cnumber) {
        this.cnumber = cnumber;
    }

    public Integer getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(Integer outNumber) {
        this.outNumber = outNumber;
    }

    public String getLoadingRate() {
        return loadingRate;
    }

    public void setLoadingRate(String loadingRate) {
        this.loadingRate = loadingRate;
    }

    public Integer getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Integer boxNum) {
        this.boxNum = boxNum;
    }

    public String getPackingUnit() {
        return packingUnit;
    }

    public void setPackingUnit(String packingUnit) {
        this.packingUnit = packingUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public String getExts() {
        return exts;
    }

    public void setExts(String exts) {
        this.exts = exts;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

}

