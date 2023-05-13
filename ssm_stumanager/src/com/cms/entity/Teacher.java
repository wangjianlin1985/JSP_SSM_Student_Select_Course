// 
// 
// 

package com.cms.entity;

public class Teacher extends User
{
    private String id;
    private String password;
    private String name;
    private String synopsis;
    
    public String getSynopsis() {
        return this.synopsis;
    }
    
    public void setSynopsis(final String synopsis) {
        this.synopsis = synopsis;
    }
    
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
}
