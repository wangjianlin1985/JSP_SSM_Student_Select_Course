// 
// 
// 

package com.cms.dao;

import com.cms.entity.Student;
import com.cms.utils.page.Pagination;
import java.util.List;
import com.cms.entity.Notice;

public interface NoticeDao
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(Notice p0);
    
    int insertSelective(Notice p0);
    
    Notice selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(Notice p0);
    
    int updateByPrimaryKey(Notice p0);
    
    int deleteBatch(List<Integer> p0);
    
    List<Notice> getNotice(Pagination<Student> p0, Integer p1, String p2);
    
    int getTotalItemsCount(Integer p0, String p1);
}
