// 
// 
// 

package com.cms.entity;

import java.util.List;

public class User
{
    private String userType;
    private List<Auth> menuList;
    private List<Auth> urlList;
    
    public String getUserType() {
        return this.userType;
    }
    
    public void setUserType(final String userType) {
        this.userType = userType;
    }
    
    public List<Auth> getMenuList() {
        return this.menuList;
    }
    
    public void setMenuList(final List<Auth> menuList) {
        this.menuList = menuList;
    }
    
    public List<Auth> getUrlList() {
        return this.urlList;
    }
    
    public void setUrlList(final List<Auth> urlList) {
        this.urlList = urlList;
    }
}
