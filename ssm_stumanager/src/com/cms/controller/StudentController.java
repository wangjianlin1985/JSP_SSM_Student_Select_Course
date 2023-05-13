// 
// 
// 

package com.cms.controller;

import java.io.InputStream;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import com.cms.utils.MD5Util;
import org.springframework.web.servlet.ModelAndView;
import com.cms.entity.Teacher;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.cms.entity.Student;
import com.cms.utils.page.Pagination;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/student" })
public class StudentController
{
    @Autowired
    private StudentService studentService;
    
    @ResponseBody
    @RequestMapping({ "/list" })
    public String getStudentList(@RequestParam(defaultValue = "0") final int curr, @RequestParam(defaultValue = "20") final int nums, @RequestParam(defaultValue = "") final String searchKey) {
        final Pagination<Student> page = new Pagination<Student>();
        page.setTotalItemsCount(this.studentService.getTotalItemsCount(searchKey));
        page.setPageSize(nums);
        page.setPageNum(curr);
        final List<Student> list = this.studentService.getStudentList(page, searchKey);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + page.getTotalItemsCount() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        return jsonStr;
    }
    
    @ResponseBody
    @RequestMapping({ "/stulist" })
    public String getMyStudentList(@RequestParam(defaultValue = "0") final int curr, @RequestParam(defaultValue = "20") final int nums, @RequestParam(required = false) final Integer cId, final HttpSession session) {
        final Teacher t = (Teacher)session.getAttribute("user");
        final Pagination<Student> page = new Pagination<Student>();
        page.setTotalItemsCount(this.studentService.getTotalItemsCountByTid(t.getId(), cId));
        page.setPageSize(nums);
        page.setPageNum(curr);
        final List<Student> list = this.studentService.getStudentListByTid(page, t.getId(), cId);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + page.getTotalItemsCount() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        System.out.println(jsonStr);
        return jsonStr;
    }
    
    @RequestMapping({ "/addPage" })
    public ModelAndView toAddPage() {
        return new ModelAndView("/studentAdd");
    }
    
    @ResponseBody
    @RequestMapping({ "/add" })
    public String addStudent(@RequestParam(defaultValue = "2") final int opType, final Student stu) {
        int res = 0;
        if (opType == 0) {
            try {
                stu.setPassword(stu.getPassword().toUpperCase());
                res = this.studentService.addStudent(stu);
            }
            catch (Exception e) {
                System.out.println("\u6dfb\u52a0\u5931\u8d25\uff01\u5b66\u53f7\u91cd\u590d\uff01");
                return "\u6dfb\u52a0\u5931\u8d25\uff01\u5b66\u53f7\u91cd\u590d\uff01";
            }
            if (res > 0) {
                return "true";
            }
            return "\u6dfb\u52a0\u5931\u8d25";
        }
        else {
            if (opType != 1) {
                return "error";
            }
            stu.setPassword(null);
            res = this.studentService.updateStudent(stu);
            if (res > 0) {
                return "true";
            }
            return "\u4fee\u6539\u5931\u8d25\uff01";
        }
    }
    
    @ResponseBody
    @RequestMapping({ "/resetPswd" })
    public String resetPasswrd(final String id) {
        final Student stu = new Student();
        stu.setId(id);
        stu.setPassword(MD5Util.MD5("123456"));
        if (this.studentService.updateStudent(stu) > 0) {
            return "true";
        }
        return "\u4fee\u6539\u5931\u8d25\uff01";
    }
    
    @ResponseBody
    @RequestMapping({ "/delete" })
    public String deleteStudnet(final Student stu) {
        if (this.studentService.deleteStudent(stu) > 0) {
            return "true";
        }
        return "\u5220\u9664\u5931\u8d25\uff01";
    }
    
    @ResponseBody
    @RequestMapping({ "/deleteList" })
    public String deleteStudnetList(final String stuIds) {
        final List<String> list = new ArrayList<String>();
        try {
            final String[] ids = stuIds.split(",");
            String[] array;
            for (int length = (array = ids).length, i = 0; i < length; ++i) {
                final String id = array[i];
                list.add(id);
            }
            if (this.studentService.deleteStudent(list) > 0) {
                return "true";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "\u5220\u9664\u5931\u8d25\uff01\u53c2\u6570\u51fa\u9519\uff01";
        }
        return "\u5220\u9664\u5931\u8d25\uff01";
    }
    
    @ResponseBody
    @RequestMapping({ "/import" })
    public String impotr(final HttpServletRequest request, final MultipartFile file) {
        InputStream in = null;
        try {
            in = file.getInputStream();
            final int res = this.studentService.importExcelInfo(in, file);
            if (res > 0) {
                return "{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":true}";
            }
            return "{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":false}";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":error}";
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
    
    @RequestMapping({ "/courses" })
    public ModelAndView toChoiceCoursePage() {
        return new ModelAndView("choiceCourse");
    }
}
