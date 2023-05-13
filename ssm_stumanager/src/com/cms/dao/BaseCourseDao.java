// 
// 
// 

package com.cms.dao;

import java.util.List;
import com.cms.utils.page.Pagination;
import com.cms.entity.BaseCourse;

public interface BaseCourseDao
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(BaseCourse p0);
    
    int insertSelective(BaseCourse p0);
    
    BaseCourse selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(BaseCourse p0);
    
    int updateByPrimaryKey(BaseCourse p0);
    
    int getTotalItemsCount(String p0);
    
    List<BaseCourse> getBaseCourse(Pagination<BaseCourse> p0, String p1);
    
    List<BaseCourse> getBaseCourseForSelect(String p0);
    
    int deleteInList(List<Integer> p0);
    
    int insertBatch(List<BaseCourse> p0);
}
