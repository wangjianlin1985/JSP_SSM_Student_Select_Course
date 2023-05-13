// 
// 
// 

package com.cms.entity;

public class Notice
{
    private Integer id;
    private String title;
    private String author;
    private String content;
    private Integer auth;
    private String date;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = ((title == null) ? null : title.trim());
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(final String author) {
        this.author = ((author == null) ? null : author.trim());
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(final String content) {
        this.content = ((content == null) ? null : content.trim());
    }
    
    public Integer getAuth() {
        return this.auth;
    }
    
    public void setAuth(final Integer auth) {
        this.auth = auth;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public void setDate(final String date) {
        this.date = date;
    }
}
