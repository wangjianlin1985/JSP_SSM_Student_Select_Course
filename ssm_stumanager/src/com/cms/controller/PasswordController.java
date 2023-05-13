// 
// 
// 

package com.cms.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import com.cms.entity.Student;
import com.cms.entity.Teacher;
import com.cms.entity.Admin;
import com.cms.entity.User;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import com.cms.service.StudentService;
import com.cms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/pswd" })
public class PasswordController
{
    @Autowired
    AdminService adminService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    
    @RequestMapping({ "/page" })
    public ModelAndView toPswdPage(ModelAndView mav) {
        mav = new ModelAndView("changePwd");
        return mav;
    }
    
    @ResponseBody
    @RequestMapping({ "/setting" })
    public String setting(final HttpSession session, String oldPswd, String newPswd) {
        oldPswd = oldPswd.toUpperCase();
        newPswd = newPswd.toUpperCase();
        final User user = (User)session.getAttribute("user");
        int res = 0;
        if ("admin".equals(user.getUserType())) {
            final Admin admin = (Admin)user;
            if (!admin.getPassword().equals(oldPswd)) {
                return "\u5bc6\u7801\u9519\u8bef\uff01";
            }
            admin.setPassword(newPswd);
            session.setAttribute("user", (Object)admin);
            res = this.adminService.update(admin);
            if (res > 0) {
                return "true";
            }
        }
        else if ("teacher".equals(user.getUserType())) {
            final Teacher t = (Teacher)user;
            if (!t.getPassword().equals(oldPswd)) {
                return "\u5bc6\u7801\u9519\u8bef\uff01";
            }
            t.setPassword(newPswd);
            res = this.teacherService.updateTeacher(t);
            if (res > 0) {
                return "true";
            }
        }
        else {
            final Student stu = (Student)user;
            if (!stu.getPassword().equals(oldPswd)) {
                return "\u5bc6\u7801\u9519\u8bef\uff01";
            }
            stu.setPassword(newPswd);
            res = this.studentService.updateStudent(stu);
            if (res > 0) {
                return "true";
            }
        }
        return "\u64cd\u4f5c\u5931\u8d25\uff01";
    }
}
