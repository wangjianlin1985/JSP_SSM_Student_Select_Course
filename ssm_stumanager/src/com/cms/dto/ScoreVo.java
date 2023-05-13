// 
// 
// 

package com.cms.dto;

import com.cms.entity.Course;

public class ScoreVo extends Course
{
    private String stuId;
    private String stuName;
    private String searchKey;
    private Integer isPass;
    private String major;
    private String grade;
    
    public String getStuId() {
        return this.stuId;
    }
    
    public void setStuId(final String stuId) {
        this.stuId = stuId;
    }
    
    public String getStuName() {
        return this.stuName;
    }
    
    public void setStuName(final String stuName) {
        this.stuName = stuName;
    }
    
    public String getSearchKey() {
        return this.searchKey;
    }
    
    public void setSearchKey(final String searchKey) {
        this.searchKey = searchKey;
    }
    
    public Integer getIsPass() {
        return this.isPass;
    }
    
    public void setIsPass(final Integer isPass) {
        this.isPass = isPass;
    }
    
    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(final String grade) {
        this.grade = grade;
    }
    
    public String getMajor() {
        return this.major;
    }
    
    public void setMajor(final String major) {
        this.major = major;
    }
    
    @Override
    public String toString() {
        return "ScoreVo [stuId=" + this.stuId + ", stuName=" + this.stuName + ", searchKey=" + this.searchKey + ", isPass=" + this.isPass + ", major=" + this.major + ", grade=" + this.grade + "]";
    }
}
