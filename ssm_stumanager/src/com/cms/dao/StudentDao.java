// 
// 
// 

package com.cms.dao;

import java.util.List;
import com.cms.utils.page.Pagination;
import com.cms.entity.Student;

public interface StudentDao
{
    int deleteByPrimaryKey(String p0);
    
    int insert(Student p0) throws Exception;
    
    int insertSelective(Student p0);
    
    Student selectByPrimaryKey(String p0);
    
    int updateByPrimaryKeySelective(Student p0);
    
    int updateByPrimaryKey(Student p0);
    
    List<Student> selectBySearchKey(Pagination<Student> p0, String p1);
    
    int getTotalItemsCount(String p0);
    
    Student select(Student p0);
    
    int deleteInList(List<String> p0);
    
    int insertBatch(List<Student> p0);
    
    int getTotalItemsCountByTid(String p0, Integer p1);
    
    List<Student> getStudentListByTid(Pagination<Student> p0, String p1, Integer p2);
}
