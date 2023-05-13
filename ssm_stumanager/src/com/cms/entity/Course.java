// 
// 
// 

package com.cms.entity;

public class Course
{
    private Integer id;
    private String startDate;
    private String endDate;
    private Short classHour;
    private String testMode;
    private Integer studentNum;
    private Integer choiceNum;
    private Integer complete;
    private String tId;
    private Integer baseCourseId;
    private String teacherName;
    private String courseName;
    private String result;
    private Integer score;
    
    public String getTeacherName() {
        return this.teacherName;
    }
    
    public void setTeacherName(final String teacherName) {
        this.teacherName = teacherName;
    }
    
    public String getCourseName() {
        return this.courseName;
    }
    
    public void setCourseName(final String courseName) {
        this.courseName = courseName;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public String getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }
    
    public Short getClassHour() {
        return this.classHour;
    }
    
    public void setClassHour(final Short classHour) {
        this.classHour = classHour;
    }
    
    public String getTestMode() {
        return this.testMode;
    }
    
    public void setTestMode(final String testMode) {
        this.testMode = ((testMode == null) ? null : testMode.trim());
    }
    
    public String gettId() {
        return this.tId;
    }
    
    public void settId(final String tId) {
        this.tId = ((tId == null) ? null : tId.trim());
    }
    
    public Integer getBaseCourseId() {
        return this.baseCourseId;
    }
    
    public void setBaseCourseId(final Integer baseCourseId) {
        this.baseCourseId = baseCourseId;
    }
    
    public Integer getStudentNum() {
        return this.studentNum;
    }
    
    public void setStudentNum(final Integer studentNum) {
        this.studentNum = studentNum;
    }
    
    public Integer getChoiceNum() {
        return this.choiceNum;
    }
    
    public void setChoiceNum(final Integer choiceNum) {
        this.choiceNum = choiceNum;
    }
    
    @Override
    public String toString() {
        return "Course [id=" + this.id + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", classHour=" + this.classHour + ", testMode=" + this.testMode + ", studentNum=" + this.studentNum + ", choiceNum=" + this.choiceNum + ", tId=" + this.tId + ", baseCourseId=" + this.baseCourseId + ", teacherName=" + this.teacherName + ", courseName=" + this.courseName + "]";
    }
    
    public Integer getComplete() {
        return this.complete;
    }
    
    public void setComplete(final Integer complete) {
        this.complete = complete;
    }
    
    public Integer getScore() {
        return this.score;
    }
    
    public void setScore(final Integer score) {
        this.score = score;
    }
    
    public String getResult() {
        return this.result;
    }
    
    public void setResult(final String result) {
        this.result = result;
    }
}
