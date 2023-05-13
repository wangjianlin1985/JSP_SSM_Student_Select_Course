// 
// 
// 

package com.cms.entity;

public class Admin extends User
{
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }
    
    @Override
    public String toString() {
        return "Admin [id=" + this.id + ", username=" + this.username + ", password=" + this.password + ", nickname=" + this.nickname + "]";
    }
}
