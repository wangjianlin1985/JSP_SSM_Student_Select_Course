// 
// 
// 

package com.cms.utils;

import java.text.NumberFormat;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.beans.PropertyDescriptor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.ss.usermodel.Font;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import com.cms.dto.ExcelBean;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class ExcelUtil
{
    private static final String excel2003L = ".xls";
    private static final String excel2007U = ".xlsx";
    private static XSSFCellStyle fontStyle;
    private static XSSFCellStyle fontStyle2;
    
    public static List<List<Object>> getBankListByExcel(final InputStream in, final String fileName) throws Exception {
        List<List<Object>> list = null;
        final Workbook work = getWorkbook(in, fileName);
        if (work == null) {
            throw new Exception("\u521b\u5efaExcel\u5de5\u4f5c\u8584\u4e3a\u7a7a\uff01");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        for (int i = 0; i < work.getNumberOfSheets(); ++i) {
            sheet = work.getSheetAt(i);
            if (sheet != null) {
                for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); ++j) {
                    boolean isAdd = true;
                    row = sheet.getRow(j);
                    if (row != null) {
                        if (row.getFirstCellNum() != j) {
                            final List<Object> li = new ArrayList<Object>();
                            for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); ++y) {
                                cell = row.getCell(y);
                                if (cell != null && cell.getCellType() != 3) {
                                    li.add(getCellValue(cell));
                                }
                                else if (y == 0) {
                                    isAdd = false;
                                    break;
                                }
                            }
                            if (isAdd) {
                                list.add(li);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
    
    public static Workbook getWorkbook(final InputStream inStr, final String fileName) throws Exception {
        Workbook wb = null;
        final String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            wb = (Workbook)new HSSFWorkbook(inStr);
        }
        else {
            if (!".xlsx".equals(fileType)) {
                throw new Exception("\u89e3\u6790\u7684\u6587\u4ef6\u683c\u5f0f\u6709\u8bef\uff01");
            }
            wb = (Workbook)new XSSFWorkbook(inStr);
        }
        return wb;
    }
    
    public static Object getCellValue(final Cell cell) {
        Object value = null;
        final DecimalFormat df = new DecimalFormat("0");
        final SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        final DecimalFormat df2 = new DecimalFormat("0.00");
        switch (cell.getCellType()) {
            case 1: {
                value = cell.getRichStringCellValue().getString();
                break;
            }
            case 0: {
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                    break;
                }
                if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                    break;
                }
                value = df2.format(cell.getNumericCellValue());
                break;
            }
            case 4: {
                value = cell.getBooleanCellValue();
                break;
            }
            case 3: {
                value = "";
                break;
            }
        }
        return value;
    }
    
    public static XSSFWorkbook createExcelFile(final Class clazz, final List objs, final Map<Integer, List<ExcelBean>> map, final String sheetName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, IntrospectionException {
        final XSSFWorkbook workbook = new XSSFWorkbook();
        final XSSFSheet sheet = workbook.createSheet(sheetName);
        createFont(workbook);
        createTableHeader(sheet, map);
        createTableRows(sheet, map, objs, clazz);
        return workbook;
    }
    
    public static void createFont(final XSSFWorkbook workbook) {
        ExcelUtil.fontStyle = workbook.createCellStyle();
        final XSSFFont font1 = workbook.createFont();
        font1.setFontName("\u9ed1\u4f53");
        font1.setFontHeightInPoints((short)14);
        ExcelUtil.fontStyle.setFont((Font)font1);
        ExcelUtil.fontStyle2 = workbook.createCellStyle();
        final XSSFFont font2 = workbook.createFont();
        font2.setFontName("\u5b8b\u4f53");
        font2.setFontHeightInPoints((short)10);
        ExcelUtil.fontStyle2.setFont((Font)font2);
    }
    
    public static final void createTableHeader(final XSSFSheet sheet, final Map<Integer, List<ExcelBean>> map) {
        int startIndex = 0;
        int endIndex = 0;
        for (final Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            final XSSFRow row = sheet.createRow((int)entry.getKey());
            final List<ExcelBean> excels = entry.getValue();
            for (int x = 0; x < excels.size(); ++x) {
                if (excels.get(x).getCols() > 1) {
                    if (x == 0) {
                        endIndex += excels.get(x).getCols() - 1;
                        final CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                        startIndex += excels.get(x).getCols();
                    }
                    else {
                        endIndex += excels.get(x).getCols();
                        final CellRangeAddress range = new CellRangeAddress(0, 0, startIndex, endIndex);
                        sheet.addMergedRegion(range);
                        startIndex += excels.get(x).getCols();
                    }
                    final XSSFCell cell = row.createCell(startIndex - excels.get(x).getCols());
                    cell.setCellValue(excels.get(x).getHeadTextName());
                    if (excels.get(x).getCellStyle() != null) {
                        cell.setCellStyle((CellStyle)excels.get(x).getCellStyle());
                    }
                    cell.setCellStyle((CellStyle)ExcelUtil.fontStyle);
                }
                else {
                    final XSSFCell cell = row.createCell(x);
                    cell.setCellValue(excels.get(x).getHeadTextName());
                    if (excels.get(x).getCellStyle() != null) {
                        cell.setCellStyle((CellStyle)excels.get(x).getCellStyle());
                    }
                    cell.setCellStyle((CellStyle)ExcelUtil.fontStyle);
                }
            }
        }
    }
    
    public static void createTableRows(final XSSFSheet sheet, final Map<Integer, List<ExcelBean>> map, final List objs, final Class clazz) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IntrospectionException, ClassNotFoundException {
        int rowindex = map.size();
        int maxKey = 0;
        List<ExcelBean> ems = new ArrayList<ExcelBean>();
        for (final Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            if (entry.getKey() > maxKey) {
                maxKey = entry.getKey();
            }
        }
        ems = map.get(maxKey);
        final List<Integer> widths = new ArrayList<Integer>(ems.size());
        for (final Object obj : objs) {
            final XSSFRow row = sheet.createRow(rowindex);
            for (int i = 0; i < ems.size(); ++i) {
                final ExcelBean em = ems.get(i);
                final PropertyDescriptor pd = new PropertyDescriptor(em.getPropertyName(), clazz);
                final Method getMethod = pd.getReadMethod();
                final Object rtn = getMethod.invoke(obj, new Object[0]);
                String value = "";
                if (rtn != null) {
                    if (rtn instanceof Date) {
                        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        value = sdf.format(rtn);
                    }
                    else if (rtn instanceof BigDecimal) {
                        final NumberFormat nf = new DecimalFormat("#,##0.00");
                        value = nf.format(rtn).toString();
                    }
                    else if (rtn instanceof Integer && Integer.valueOf(rtn.toString()) < 0) {
                        value = "--";
                    }
                    else {
                        value = rtn.toString();
                    }
                }
                final XSSFCell cell = row.createCell(i);
                cell.setCellValue(value);
                cell.setCellType(1);
                cell.setCellStyle((CellStyle)ExcelUtil.fontStyle2);
                final int width = value.getBytes().length * 300;
                if (widths.size() <= i) {
                    widths.add(width);
                }
                else if (width > widths.get(i)) {
                    widths.set(i, width);
                }
            }
            ++rowindex;
        }
        for (int index = 0; index < widths.size(); ++index) {
            Integer width2 = widths.get(index);
            width2 = ((width2 < 2500) ? 2500 : (width2 + 300));
            width2 = ((width2 > 10000) ? 10300 : (width2 + 300));
            sheet.setColumnWidth(index, (int)width2);
        }
    }
}
