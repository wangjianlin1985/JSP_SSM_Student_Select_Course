// 
// 
// 

package com.cms.dao;

import com.cms.utils.page.Pagination;
import java.util.List;
import com.cms.entity.Teacher;

public interface TeacherDao
{
    int deleteByPrimaryKey(String p0);
    
    int insert(Teacher p0);
    
    int insertSelective(Teacher p0);
    
    Teacher selectByPrimaryKey(String p0);
    
    int updateByPrimaryKeySelective(Teacher p0);
    
    int updateByPrimaryKey(Teacher p0);
    
    Teacher selectTeacher(Teacher p0);
    
    int getTotalItemsCount(String p0);
    
    int deleteInList(List<String> p0);
    
    int insertBatch(List<Teacher> p0);
    
    List<Teacher> getTeacher(Pagination<Teacher> p0, String p1);
    
    List<Teacher> getTeacherForSelect(String p0);
}
