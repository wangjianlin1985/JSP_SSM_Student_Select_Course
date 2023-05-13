// 
// 
// 

package com.cms.controller;

import java.util.List;
import com.cms.entity.Course;
import com.cms.utils.page.Pagination;
import com.cms.entity.Student;
import com.cms.entity.Teacher;
import com.cms.entity.Admin;
import com.cms.entity.User;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.service.CourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/main" })
public class MainController
{
    @Autowired
    CourseService courseService;
    
    @RequestMapping({ "/index" })
    public ModelAndView toIndexPage(final HttpSession session) {
        User user = (User)session.getAttribute("user");
        if (user.getUserType().equals("admin")) {
            user = user;
        }
        else if (user.getUserType().equals("teacher")) {
            user = user;
        }
        else if (user.getUserType().equals("student")) {
            user = user;
        }
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject((Object)"user");
        return mav;
    }
    
    @RequestMapping({ "/student" })
    public ModelAndView toStudentListPage(final HttpSession session) {
        return new ModelAndView("studentList");
    }
    
    @RequestMapping({ "/teacher" })
    public ModelAndView toTeacherListPage() {
        return new ModelAndView("teacherList");
    }
    
    @RequestMapping({ "/course" })
    public ModelAndView toCourseListPage(final HttpSession session, ModelAndView mav) {
        final String userType = ((User)session.getAttribute("user")).getUserType();
        if (userType.equals("admin")) {
            mav = new ModelAndView("courseList");
        }
        else if (userType.equals("teacher")) {
            mav = new ModelAndView("teacher/courseList");
        }
        else {
            mav = new ModelAndView("student/courseList");
        }
        return mav;
    }
    
    @RequestMapping({ "/score" })
    public ModelAndView toScoreListPage(final HttpSession session, ModelAndView mav) {
        final User user = (User)session.getAttribute("user");
        final String userType = user.getUserType();
        if (userType.equals("admin")) {
            mav = new ModelAndView("scoreList");
        }
        else if (userType.equals("teacher")) {
            mav = new ModelAndView("teacher/studentScoreList");
            final List<Course> list = this.courseService.getCourseListByTid(null, ((Teacher)user).getId());
            mav.addObject("courseList", (Object)list);
        }
        else {
            mav = new ModelAndView("student/scoreList");
        }
        return mav;
    }
    
    @RequestMapping({ "/notice" })
    public ModelAndView toNoticeListPage(ModelAndView mav) {
        mav = new ModelAndView("noticeList");
        return mav;
    }
    
    @RequestMapping({ "/system" })
    public ModelAndView toSystemListPage(ModelAndView mav) {
        mav = new ModelAndView("systemAuth");
        return mav;
    }
}
