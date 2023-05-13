// 
// 
// 

package com.cms.dao;

import com.cms.entity.Admin;

public interface AdminDao
{
    Admin select(Admin p0);
    
    int updateByPrimaryKeySelective(Admin p0);
}
