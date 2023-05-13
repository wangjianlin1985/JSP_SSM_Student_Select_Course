// 
// 
// 

package com.cms.entity;

public class Student extends User
{
    private String id;
    private String password;
    private String name;
    private String sex;
    private String admissionDate;
    private String major;
    private String grade;
    private String education;
    private Integer score;
    private String result;
    private String courseName;
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String id) {
        this.id = ((id == null) ? null : id.trim());
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = ((password == null) ? null : password.trim());
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = ((name == null) ? null : name.trim());
    }
    
    public String getSex() {
        return this.sex;
    }
    
    public void setSex(final String sex) {
        this.sex = ((sex == null) ? null : sex.trim());
    }
    
    public String getAdmissionDate() {
        return this.admissionDate;
    }
    
    public void setAdmissionDate(final String admissionDate) {
        this.admissionDate = admissionDate;
    }
    
    public String getMajor() {
        return this.major;
    }
    
    public void setMajor(final String major) {
        this.major = ((major == null) ? null : major.trim());
    }
    
    public String getGrade() {
        return this.grade;
    }
    
    public void setGrade(final String grade) {
        this.grade = ((grade == null) ? null : grade.trim());
    }
    
    public String getEducation() {
        return this.education;
    }
    
    public void setEducation(final String education) {
        this.education = ((education == null) ? null : education.trim());
    }
    
    @Override
    public String toString() {
        return "Student [id=" + this.id + ", password=" + this.password + ", name=" + this.name + ", sex=" + this.sex + ", admissionDate=" + this.admissionDate + ", major=" + this.major + ", grade=" + this.grade + ", education=" + this.education + "]";
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
    
    public String getCourseName() {
        return this.courseName;
    }
    
    public void setCourseName(final String courseName) {
        this.courseName = courseName;
    }
}
