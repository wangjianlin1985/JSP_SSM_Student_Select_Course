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
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.cms.entity.Teacher;
import com.cms.utils.page.Pagination;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.service.TeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/teacher" })
public class TeacherController
{
    @Autowired
    private TeacherService teacherService;
    
    @ResponseBody
    @RequestMapping({ "/list" })
    public String getTeacherList(@RequestParam(defaultValue = "0") final int curr, @RequestParam(defaultValue = "10") final int nums, @RequestParam(defaultValue = "") final String searchKey) {
        final Pagination<Teacher> page = new Pagination<Teacher>();
        page.setTotalItemsCount(this.teacherService.getTotalItemsCount(searchKey));
        page.setPageSize(nums);
        page.setPageNum(curr);
        final List<Teacher> list = this.teacherService.getTeacher(page, searchKey);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + page.getTotalItemsCount() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        return jsonStr;
    }
    
    @ResponseBody
    @RequestMapping({ "/listForSelect" })
    public String getTeacherListForSelect(@RequestParam(defaultValue = "") final String searchKey) {
        final List<Teacher> list = this.teacherService.getTeacherForSelect(searchKey);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + list.size() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        return jsonStr;
    }
    
    @RequestMapping({ "/addPage" })
    public ModelAndView toAddPage() {
        return new ModelAndView("/teacherAdd");
    }
    
    @ResponseBody
    @RequestMapping({ "/add" })
    public String addTeacher(@RequestParam(defaultValue = "2") final int opType, final Teacher teacher) {
        int res = 0;
        if (opType == 0) {
            try {
                teacher.setPassword(teacher.getPassword().toUpperCase());
                res = this.teacherService.addTeacher(teacher);
            }
            catch (Exception e) {
                System.out.println("\u6dfb\u52a0\u5931\u8d25\uff01\u5b66\u53f7\u91cd\u590d\uff01");
                return "\u6dfb\u52a0\u5931\u8d25\uff01\u5de5\u53f7\u91cd\u590d\uff01";
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
            teacher.setPassword(null);
            res = this.teacherService.updateTeacher(teacher);
            if (res > 0) {
                return "true";
            }
            return "\u4fee\u6539\u5931\u8d25\uff01";
        }
    }
    
    @ResponseBody
    @RequestMapping({ "/resetPswd" })
    public String resetPasswrd(final String id) {
        final Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setPassword(MD5Util.MD5("123456"));
        if (this.teacherService.updateTeacher(teacher) > 0) {
            return "true";
        }
        return "\u4fee\u6539\u5931\u8d25\uff01";
    }
    
    @ResponseBody
    @RequestMapping({ "/delete" })
    public String deleteStudnet(final Teacher t) {
        if (this.teacherService.deleteTeacher(t) > 0) {
            return "true";
        }
        return "\u5220\u9664\u5931\u8d25\uff01";
    }
    
    @ResponseBody
    @RequestMapping({ "/deleteList" })
    public String deleteStudnetList(final String tIds) {
        final List<String> list = new ArrayList<String>();
        try {
            final String[] ids = tIds.split(",");
            String[] array;
            for (int length = (array = ids).length, i = 0; i < length; ++i) {
                final String id = array[i];
                list.add(id);
            }
            if (this.teacherService.deleteTeacher(list) > 0) {
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
            final int res = this.teacherService.importExcelInfo(in, file);
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
}
