// 
// 
// 

package com.cms.service;

import com.cms.utils.page.Pagination;
import com.cms.entity.Auth;
import java.util.List;

public interface AuthService
{
    List<Auth> getMenuList(String p0);
    
    List<Auth> getUrlList(String p0);
    
    int getTotalItemsCount(String p0);
    
    List<Auth> getAuthList(Pagination<Auth> p0, String p1);
    
    int update(Auth p0);
}
