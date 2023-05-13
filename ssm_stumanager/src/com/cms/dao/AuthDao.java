// 
// 
// 

package com.cms.dao;

import com.cms.utils.page.Pagination;
import com.cms.entity.Auth;
import java.util.List;

public interface AuthDao
{
    List<Auth> selectUrl(String p0);
    
    List<Auth> selectMenu(String p0);
    
    int deleteByPrimaryKey(Integer p0);
    
    int insert(Auth p0);
    
    int insertSelective(Auth p0);
    
    int updateByPrimaryKeySelective(Auth p0);
    
    int updateByPrimaryKey(Auth p0);
    
    int getTotalItemsCount(String p0);
    
    List<Auth> getAuthList(Pagination<Auth> p0, String p1);
}
