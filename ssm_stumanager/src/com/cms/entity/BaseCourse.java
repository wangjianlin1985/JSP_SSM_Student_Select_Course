// 
// 
// 

package com.cms.entity;

public class BaseCourse
{
    private Integer id;
    private String name;
    private String synopsis;
    
    public String getSynopsis() {
        return this.synopsis;
    }
    
    public void setSynopsis(final String synopsis) {
        this.synopsis = synopsis;
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
}
