// 
// 
// 

package com.cms.service.impl;

import java.util.List;
import com.cms.entity.Course;
import com.cms.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.dao.CourseDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cms.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService
{
    @Autowired
    private CourseDao courseDao;
    
    @Override
    public int getTotalItemsCount(final String searchKey) {
        return this.courseDao.getTotalItemsCount(searchKey);
    }
    
    @Override
    public List<Course> getCourseList(final Pagination<Course> page, final String searchKey) {
        return this.courseDao.getCourseList(page, searchKey);
    }
    
    @Override
    public int addCourse(final Course course) {
        if (course.getStartDate() != null && course.getStartDate().equals("")) {
            course.setStartDate(null);
        }
        if (course.getEndDate() != null && course.getEndDate().equals("")) {
            course.setEndDate(null);
        }
        return this.courseDao.insertSelective(course);
    }
    
    @Override
    public int updateCourse(final Course course) {
        return this.courseDao.updateByPrimaryKeySelective(course);
    }
    
    @Override
    public int deleteCourse(final Course c) {
        return this.courseDao.deleteByPrimaryKey(c.getId());
    }
    
    @Override
    public int deleteCourse(final List<Integer> list) {
        return this.courseDao.deleteInList(list);
    }
    
    @Override
    public int getTotalItemsCountByTid(final String id) {
        return this.courseDao.getTotalItemsCountByTid(id);
    }
    
    @Override
    public List<Course> getCourseListByTid(final Pagination<Course> page, final String id) {
        return this.courseDao.getCourseListByTid(page, id);
    }
    
    @Override
    public int getTotalItemsCountBySid(final int isAll, final String searchKey, final String id) {
        return this.courseDao.getTotalItemsCountBySid(isAll, searchKey, id);
    }
    
    @Override
    public List<Course> getCourseListBySid(final Pagination<Course> page, final int isAll, final String searchKey, final String id) {
        return this.courseDao.getCourseListBySid(page, isAll, searchKey, id);
    }
    
    @Override
    public int completeCourse(final Course course) {
        final Course c = new Course();
        c.setId(course.getId());
        c.setComplete(1);
        return this.courseDao.updateByPrimaryKeySelective(c);
    }
}
