// 
// 
// 

package com.cms.entity;

public class Auth
{
    private Integer id;
    private String name;
    private String url;
    private Byte adminAuth;
    private Byte teacherAuth;
    private Byte studentAuth;
    
    @Override
    public String toString() {
        return "Auth [id=" + this.id + ", name=" + this.name + ", url=" + this.url + ", adminAuth=" + this.adminAuth + ", teacherAuth=" + this.teacherAuth + ", studentAuth=" + this.studentAuth + "]";
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = ((name == null) ? null : name.trim());
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(final String url) {
        this.url = ((url == null) ? null : url.trim());
    }
    
    public Byte getAdminAuth() {
        return this.adminAuth;
    }
    
    public void setAdminAuth(final Byte adminAuth) {
        this.adminAuth = adminAuth;
    }
    
    public Byte getTeacherAuth() {
        return this.teacherAuth;
    }
    
    public void setTeacherAuth(final Byte teacherAuth) {
        this.teacherAuth = teacherAuth;
    }
    
    public Byte getStudentAuth() {
        return this.studentAuth;
    }
    
    public void setStudentAuth(final Byte studentAuth) {
        this.studentAuth = studentAuth;
    }
}
