// 
// 
// 

package com.cms.controller;

import java.util.ArrayList;
import org.springframework.web.servlet.ModelAndView;
import com.cms.entity.Student;
import com.cms.entity.Teacher;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.cms.entity.Course;
import com.cms.utils.page.Pagination;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.service.CourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/course" })
public class CourseController
{
    @Autowired
    CourseService courseService;
    
    @ResponseBody
    @RequestMapping({ "/list" })
    public String getCourseList(@RequestParam(defaultValue = "0") final int curr, @RequestParam(defaultValue = "20") final int nums, @RequestParam(defaultValue = "") final String searchKey) {
        final Pagination<Course> page = new Pagination<Course>();
        page.setTotalItemsCount(this.courseService.getTotalItemsCount(searchKey));
        page.setPageSize(nums);
        page.setPageNum(curr);
        final List<Course> list = this.courseService.getCourseList(page, searchKey);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + page.getTotalItemsCount() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        System.out.println(jsonStr);
        return jsonStr;
    }
    
    @ResponseBody
    @RequestMapping({ "/getMyCourse" })
    public String getMyCourse(@RequestParam(defaultValue = "0") final int curr, @RequestParam(defaultValue = "10") final int nums, final HttpSession session) {
        final Pagination<Course> page = new Pagination<Course>();
        final Teacher t = (Teacher)session.getAttribute("user");
        page.setTotalItemsCount(this.courseService.getTotalItemsCountByTid(t.getId()));
        page.setPageSize(nums);
        page.setPageNum(curr);
        final List<Course> list = this.courseService.getCourseListByTid(page, t.getId());
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + page.getTotalItemsCount() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        System.out.println(jsonStr);
        return jsonStr;
    }
    
    @ResponseBody
    @RequestMapping({ "/choiceList" })
    public String getCourseChoiceList(@RequestParam(defaultValue = "0") final int curr, @RequestParam(defaultValue = "30") final int nums, @RequestParam(defaultValue = "1") final int isAll, @RequestParam(defaultValue = "") final String searchKey, final HttpSession session) {
        final Pagination<Course> page = new Pagination<Course>();
        final String sId = ((Student)session.getAttribute("user")).getId();
        page.setTotalItemsCount(this.courseService.getTotalItemsCountBySid(isAll, searchKey, sId));
        page.setPageSize(nums);
        page.setPageNum(curr);
        final List<Course> list = this.courseService.getCourseListBySid(page, isAll, searchKey, sId);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + page.getTotalItemsCount() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        System.out.println(String.valueOf(isAll) + "sid" + sId);
        System.out.println(jsonStr);
        return jsonStr;
    }
    
    @RequestMapping({ "/addPage" })
    public ModelAndView toAddPage() {
        return new ModelAndView("courseAdd");
    }
    
    @ResponseBody
    @RequestMapping({ "/add" })
    public String addCourse(final Course course) {
        int res = 0;
        if (course.getId() != null) {
            if (!course.getId().equals("")) {
                res = this.courseService.updateCourse(course);
                if (res > 0) {
                    return "true";
                }
                return "\u4fee\u6539\u5931\u8d25\uff01";
            }
        }
        try {
            res = this.courseService.addCourse(course);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("\u6dfb\u52a0\u5931\u8d25\uff01");
            return "\u6dfb\u52a0\u5931\u8d25\uff01";
        }
        if (res > 0) {
            return "true";
        }
        return "\u6dfb\u52a0\u5931\u8d25";
    }
    
    @ResponseBody
    @RequestMapping({ "/complete" })
    public String complete(final Course course) {
        final int res = this.courseService.completeCourse(course);
        if (res > 0) {
            return "true";
        }
        return "\u64cd\u4f5c\u5931\u8d25\uff01";
    }
    
    @ResponseBody
    @RequestMapping({ "/delete" })
    public String deleteStudnet(final Course c) {
        if (this.courseService.deleteCourse(c) > 0) {
            return "true";
        }
        return "\u5220\u9664\u5931\u8d25\uff01";
    }
    
    @ResponseBody
    @RequestMapping({ "/deleteList" })
    public String deleteStudnetList(final String cIds) {
        final List<Integer> list = new ArrayList<Integer>();
        try {
            final String[] ids = cIds.split(",");
            String[] array;
            for (int length = (array = ids).length, i = 0; i < length; ++i) {
                final String id = array[i];
                list.add(Integer.parseInt(id));
            }
            if (this.courseService.deleteCourse(list) > 0) {
                return "true";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "\u5220\u9664\u5931\u8d25\uff01\u53c2\u6570\u51fa\u9519\uff01";
        }
        return "\u5220\u9664\u5931\u8d25\uff01";
    }
}
