// 
// 
// 

package com.cms.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.cms.entity.Auth;
import com.cms.utils.page.Pagination;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.service.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/auth" })
public class AuthController
{
    @Autowired
    AuthService authService;
    
    @ResponseBody
    @RequestMapping({ "/list" })
    public String getAuthList(@RequestParam(defaultValue = "0") final int curr, @RequestParam(defaultValue = "10") final int nums, @RequestParam(defaultValue = "") final String searchKey) {
        final Pagination<Auth> page = new Pagination<Auth>();
        page.setTotalItemsCount(this.authService.getTotalItemsCount(searchKey));
        page.setPageSize(nums);
        page.setPageNum(curr);
        final List<Auth> list = this.authService.getAuthList(page, searchKey);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + page.getTotalItemsCount() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        return jsonStr;
    }
    
    @ResponseBody
    @RequestMapping({ "/setting" })
    public String setting(final Auth auth, final String type, final Byte val) {
        if ("teacherAuth".equals(type)) {
            auth.setTeacherAuth(val);
        }
        else {
            auth.setStudentAuth(val);
        }
        System.out.println(auth.toString());
        if (this.authService.update(auth) > 0) {
            return "true";
        }
        return "\u64cd\u4f5c\u5931\u8d25\uff01";
    }
}
