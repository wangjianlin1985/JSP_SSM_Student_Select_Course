// 
// 
// 

package com.cms.dto;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import java.io.Serializable;

public class ExcelBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String headTextName;
    private String propertyName;
    private Integer cols;
    private XSSFCellStyle cellStyle;
    
    public ExcelBean() {
    }
    
    public ExcelBean(final String headTextName, final String propertyName) {
        this.headTextName = headTextName;
        this.propertyName = propertyName;
    }
    
    public ExcelBean(final String headTextName, final String propertyName, final Integer cols) {
        this.headTextName = headTextName;
        this.propertyName = propertyName;
        this.cols = cols;
    }
    
    public String getHeadTextName() {
        return this.headTextName;
    }
    
    public void setHeadTextName(final String headTextName) {
        this.headTextName = headTextName;
    }
    
    public String getPropertyName() {
        return this.propertyName;
    }
    
    public void setPropertyName(final String propertyName) {
        this.propertyName = propertyName;
    }
    
    public Integer getCols() {
        return this.cols;
    }
    
    public void setCols(final Integer cols) {
        this.cols = cols;
    }
    
    public XSSFCellStyle getCellStyle() {
        return this.cellStyle;
    }
    
    public void setCellStyle(final XSSFCellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }
    
    public static long getSerialversionuid() {
        return 1L;
    }
}
