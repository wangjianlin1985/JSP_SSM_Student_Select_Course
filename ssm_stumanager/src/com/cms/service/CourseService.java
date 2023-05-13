// 
// 
// 

package com.cms.service;

import java.util.List;
import com.cms.entity.Course;
import com.cms.utils.page.Pagination;

public interface CourseService
{
    int getTotalItemsCount(String p0);
    
    List<Course> getCourseList(Pagination<Course> p0, String p1);
    
    int addCourse(Course p0);
    
    int updateCourse(Course p0);
    
    int deleteCourse(Course p0);
    
    int deleteCourse(List<Integer> p0);
    
    int getTotalItemsCountByTid(String p0);
    
    List<Course> getCourseListByTid(Pagination<Course> p0, String p1);
    
    int getTotalItemsCountBySid(int p0, String p1, String p2);
    
    List<Course> getCourseListBySid(Pagination<Course> p0, int p1, String p2, String p3);
    
    int completeCourse(Course p0);
}
