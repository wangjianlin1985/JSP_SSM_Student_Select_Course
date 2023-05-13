// 
// 
// 

package com.cms.controller;

import com.cms.entity.Score;
import com.cms.entity.Course;
import com.cms.entity.Student;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import java.lang.reflect.InvocationTargetException;
import org.springframework.expression.ParseException;
import java.beans.IntrospectionException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.cms.utils.page.Pagination;
import com.cms.dto.ScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.service.ScoreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/score" })
public class ScoreController
{
    @Autowired
    private ScoreService scoreService;
    
    @ResponseBody
    @RequestMapping({ "/list" })
    public String getScoreList(final Integer curr, final Integer nums, final ScoreVo scoreVo) {
        System.out.println(scoreVo);
        final Pagination<ScoreVo> page = new Pagination<ScoreVo>();
        page.setTotalItemsCount(this.scoreService.getTotalItemsCount(scoreVo));
        page.setPageSize(nums);
        page.setPageNum(curr);
        final List<ScoreVo> list = this.scoreService.getScoreList(page, scoreVo);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + page.getTotalItemsCount() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        System.out.println(jsonStr);
        return jsonStr;
    }
    
    @ResponseBody
    @RequestMapping({ "/export" })
    public void export(final HttpServletRequest request, final HttpServletResponse response, final ScoreVo scoreVo) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        XSSFWorkbook workbook = null;
        workbook = this.scoreService.exportExcelInfo(scoreVo);
        try {
            final OutputStream output = (OutputStream)response.getOutputStream();
            final BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write((OutputStream)bufferedOutPut);
            bufferedOutPut.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @ResponseBody
    @RequestMapping({ "/stuScore" })
    public String getScoreList(@RequestParam(defaultValue = "0") final int curr, @RequestParam(defaultValue = "20") final int nums, final HttpSession session, final Integer result) {
        final Student stu = (Student)session.getAttribute("user");
        final Pagination<Course> page = new Pagination<Course>();
        page.setTotalItemsCount(this.scoreService.getTotalItemsCount(stu.getId(), result));
        page.setPageSize(nums);
        page.setPageNum(curr);
        final List<Course> list = this.scoreService.getCourseList(page, stu.getId(), result);
        final String jsonStr = "{\"code\":0,\"msg\":\"\",\"count\":" + page.getTotalItemsCount() + ",\"data\":" + JSON.toJSONString((Object)list) + "}";
        System.out.println(jsonStr);
        return jsonStr;
    }
    
    @ResponseBody
    @RequestMapping({ "/choiceCourse" })
    public String choiceCourse(final HttpSession session, @RequestParam(defaultValue = "") final Integer id) {
        if (id == null) {
            return "\u53c2\u6570\u9519\u8bef\uff01";
        }
        final Student s = (Student)session.getAttribute("user");
        final Score score = new Score();
        score.setsId(s.getId());
        score.setcId(id);
        final int res = this.scoreService.choiceCourse(score);
        if (res > 0) {
            return "true";
        }
        return "false";
    }
    
    @ResponseBody
    @RequestMapping({ "/delete" })
    public String deleteCourse(@RequestParam(defaultValue = "") final Integer id, final HttpSession session) {
        final Student stu = (Student)session.getAttribute("user");
        final Score s = new Score();
        s.setcId(id);
        s.setsId(stu.getId());
        if (id == null) {
            return "\u53c2\u6570\u9519\u8bef\uff01";
        }
        final int res = this.scoreService.deleteScore(s);
        if (res > 0) {
            return "true";
        }
        return "false";
    }
    
    @ResponseBody
    @RequestMapping({ "/update" })
    public String updateScore(final Score score) {
        final int res = this.scoreService.updateScore(score);
        if (res > 0) {
            return "true";
        }
        return "false";
    }
    
    @ResponseBody
    @RequestMapping({ "/updateList" })
    public String updateScoreList(final String scoreListStr) {
        final List<Score> scoreList = (List<Score>)JSON.parseArray(scoreListStr, (Class)Score.class);
        System.out.println(scoreList);
        final int res = this.scoreService.updateScore(scoreList);
        if (res > 0) {
            return "true";
        }
        return "false";
    }
}
