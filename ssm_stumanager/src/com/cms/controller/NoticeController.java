// 
// 
// 

package com.cms.controller;

import java.io.IOException;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.cms.entity.User;
import com.cms.entity.Notice;
import java.util.ArrayList;
import com.cms.entity.Student;
import com.cms.utils.page.Pagination;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.service.NoticeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "notice" })
public class NoticeController
{
    private static final Integer AUTH_A;
    private static final Integer AUTH_T;
    private static final Integer AUTH_S;
    @Autowired
    private NoticeService noticeService;
    
    static {
        AUTH_A = 3;
        AUTH_T = 2;
        AUTH_S = 1;
    }
    
    @ResponseBody
    @RequestMapping({ "/list" })
    public String getNoticeList(@RequestParam(defaultValue = "0") final int curr, @RequestParam(defaultValue = "20") final int nums, @RequestParam(defaultValue = "") final String searchKey, final HttpSession session) {
        final Pagination<Student> page = new Pagination<Student>();
        page.setPageSize(nums);
        page.setPageNum(curr);
        List<Notice> list = new ArrayList<Notice>();
        final User user = (User)session.getAttribute("user");
        Integer auth = null;
        if (user.getUserType().equals("admin")) {
            auth = NoticeController.AUTH_A;
        }
        else if (user.getUserType().equals("teacher")) {
            auth = NoticeController.AUTH_T;
        }
        else if (user.getUserType().equals("student")) {
            auth = NoticeController.AUTH_S;
        }
        page.setTotalItemsCount(this.noticeService.getTotalItemsCount(auth, searchKey));
        list = this.noticeService.getNoticeList(page, auth, searchKey);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + page.getTotalItemsCount() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        return jsonStr;
    }
    
    @RequestMapping({ "/info" })
    public ModelAndView showNoticeInfo(final HttpSession session, final Integer nId, ModelAndView mav) {
        final User user = (User)session.getAttribute("user");
        Integer auth = null;
        if (user.getUserType().equals("admin")) {
            auth = NoticeController.AUTH_A;
        }
        else if (user.getUserType().equals("teacher")) {
            auth = NoticeController.AUTH_T;
        }
        else if (user.getUserType().equals("student")) {
            auth = NoticeController.AUTH_S;
        }
        final Notice notice = this.noticeService.getNotice(nId);
        if (auth < notice.getAuth()) {
            return new ModelAndView("404");
        }
        mav = new ModelAndView("notice");
        mav.addObject("notice", (Object)notice);
        return mav;
    }
    
    @RequestMapping({ "/look" })
    public ModelAndView showNotice() {
        return new ModelAndView("notice");
    }
    
    @RequestMapping({ "/addPage" })
    public ModelAndView toAddPage() {
        return new ModelAndView("noticeAdd");
    }
    
    @ResponseBody
    @RequestMapping({ "/add" })
    public String addNotice(@RequestParam(defaultValue = "2") final Integer opType, final Notice notice) {
        int res = 0;
        if (opType == 0) {
            try {
                res = this.noticeService.addNotice(notice);
            }
            catch (Exception e) {
                System.out.println("\u6dfb\u52a0\u5931\u8d25\uff01");
                return "\u6dfb\u52a0\u5931\u8d25\uff01";
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
            res = this.noticeService.updateNotice(notice);
            if (res > 0) {
                return "true";
            }
            return "\u4fee\u6539\u5931\u8d25\uff01";
        }
    }
    
    @ResponseBody
    @RequestMapping({ "/delete" })
    public String deleteNotice(final Notice notice) {
        if (this.noticeService.deleteNotice(notice) > 0) {
            return "true";
        }
        return "\u5220\u9664\u5931\u8d25\uff01";
    }
    
    @ResponseBody
    @RequestMapping({ "/deleteList" })
    public String deleteNoticeList(final String nIds) {
        final List<Integer> list = new ArrayList<Integer>();
        try {
            final String[] ids = nIds.split(",");
            String[] array;
            for (int length = (array = ids).length, i = 0; i < length; ++i) {
                final String id = array[i];
                list.add(Integer.parseInt(id));
            }
            if (this.noticeService.deleteNotice(list) > 0) {
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
    @RequestMapping({ "/uploadImg" })
    public String uploadImg(final MultipartFile file, final HttpServletRequest request) throws IOException {
        System.out.println("comming!");
        final String path = request.getSession().getServletContext().getRealPath("/images");
        System.out.println("path>>" + path);
        String fileName = file.getOriginalFilename();
        System.out.println("fileName>>" + fileName);
        fileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        fileName = String.valueOf(fileName) + System.currentTimeMillis();
        System.out.println("fileName>>" + fileName);
        final File dir = new File(path, fileName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(dir);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + (Object)null + ",\"data\":" + "{\"src\":\"" + "/ssm_stumanager/images/" + fileName + "\"}" + "}";
        return jsonStr;
    }
}
