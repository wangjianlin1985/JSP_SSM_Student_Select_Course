// 
// 
// 

package com.cms.controller;

import java.io.InputStream;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.cms.entity.BaseCourse;
import com.cms.utils.page.Pagination;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.service.BaseCourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/basecourse" })
public class BaseCourseController
{
    @Autowired
    private BaseCourseService baseCourseService;
    
    @ResponseBody
    @RequestMapping({ "/list" })
    public String getBaseCourseList(@RequestParam(defaultValue = "0") final int curr, @RequestParam(defaultValue = "10") final int nums, @RequestParam(defaultValue = "") final String searchKey) {
        final Pagination<BaseCourse> page = new Pagination<BaseCourse>();
        page.setTotalItemsCount(this.baseCourseService.getTotalItemsCount(searchKey));
        page.setPageSize(nums);
        page.setPageNum(curr);
        final List<BaseCourse> list = this.baseCourseService.getBaseCourse(page, searchKey);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + page.getTotalItemsCount() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        System.out.println(jsonStr);
        return jsonStr;
    }
    
    @ResponseBody
    @RequestMapping({ "/listForSelect" })
    public String getBaseCourseListForSelect(@RequestParam(defaultValue = "") final String searchKey) {
        final List<BaseCourse> list = this.baseCourseService.getBaseCourseForSelect(searchKey);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + list.size() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        return jsonStr;
    }
    
    @RequestMapping({ "/addPage" })
    public ModelAndView toAddPage() {
        return new ModelAndView("/baseCourseAdd");
    }
    
    @ResponseBody
    @RequestMapping({ "/add" })
    public String addBaseCourse(final BaseCourse baseCourse) {
        int res = 0;
        if (baseCourse.getId() != null) {
            if (!baseCourse.getId().equals("")) {
                res = this.baseCourseService.updateBaseCourse(baseCourse);
                if (res > 0) {
                    return "true";
                }
                return "\u4fee\u6539\u5931\u8d25\uff01";
            }
        }
        try {
            res = this.baseCourseService.addBaseCourse(baseCourse);
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
    
    @ResponseBody
    @RequestMapping({ "/delete" })
    public String deleteStudnet(final BaseCourse t) {
        if (this.baseCourseService.deleteBaseCourse(t) > 0) {
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
            if (this.baseCourseService.deleteBaseCourse(list) > 0) {
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
            final int res = this.baseCourseService.importExcelInfo(in, file);
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
