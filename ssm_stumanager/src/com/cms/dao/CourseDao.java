// 
// 
// 

package com.cms.dao;

import java.util.List;
import com.cms.utils.page.Pagination;
import com.cms.entity.Course;

public interface CourseDao
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(Course p0);
    
    int insertSelective(Course p0);
    
    Course selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(Course p0);
    
    List<Course> getCourseList(Pagination<Course> p0, String p1);
    
    int getTotalItemsCount(String p0);
    
    int deleteInList(List<Integer> p0);
    
    int getTotalItemsCountByTid(String p0);
    
    List<Course> getCourseListByTid(Pagination<Course> p0, String p1);
    
    int getTotalItemsCountBySid(int p0, String p1, String p2);
    
    List<Course> getCourseListBySid(Pagination<Course> p0, int p1, String p2, String p3);
}
