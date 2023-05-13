// 
// 
// 

package com.cms.service.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import com.cms.utils.ExcelUtil;
import java.util.LinkedHashMap;
import com.cms.dto.ExcelBean;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.cms.dto.ScoreVo;
import com.cms.utils.page.Pagination;
import java.util.List;
import com.cms.entity.Course;
import com.cms.entity.Score;
import com.cms.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.dao.ScoreDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cms.service.ScoreService;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService
{
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    CourseDao courseDao;
    
    @Override
    public int choiceCourse(final Score score) {
        final Course c = this.courseDao.selectByPrimaryKey(score.getcId());
        if (c.getStudentNum() == c.getChoiceNum()) {
            return 0;
        }
        System.out.println(c.toString());
        c.setChoiceNum(c.getChoiceNum() + 1);
        this.courseDao.updateByPrimaryKeySelective(c);
        return this.scoreDao.insertSelective(score);
    }
    
    @Override
    public int deleteScore(final Score score) {
        final Course c = this.courseDao.selectByPrimaryKey(score.getcId());
        c.setChoiceNum(c.getChoiceNum() - 1);
        this.courseDao.updateByPrimaryKeySelective(c);
        return this.scoreDao.delete(score);
    }
    
    @Override
    public int updateScore(final Score score) {
        return this.scoreDao.updateByPrimaryKeySelective(score);
    }
    
    @Override
    public int updateScore(final List<Score> scoreList) {
        return this.scoreDao.update(scoreList);
    }
    
    @Override
    public int getTotalItemsCount(final String id, final Integer result) {
        return this.scoreDao.getTotalItemsCount(id, result);
    }
    
    @Override
    public List<Course> getCourseList(final Pagination<Course> page, final String id, final Integer result) {
        return this.scoreDao.getCourseList(page, id, result);
    }
    
    @Override
    public int getTotalItemsCount(final ScoreVo scoreVo) {
        return this.scoreDao.getTotalItemsCountForExport(scoreVo);
    }
    
    @Override
    public List<ScoreVo> getScoreList(final Pagination<ScoreVo> page, final ScoreVo scoreVo) {
        return this.scoreDao.getScoreListForExport(page, scoreVo);
    }
    
    @Override
    public XSSFWorkbook exportExcelInfo(final ScoreVo scoreVo) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException {
        final List<ScoreVo> list = this.scoreDao.getScoreListForExport(null, scoreVo);
        final List<ExcelBean> excel = new ArrayList<ExcelBean>();
        final Map<Integer, List<ExcelBean>> map = new LinkedHashMap<Integer, List<ExcelBean>>();
        XSSFWorkbook xssfWorkbook = null;
        excel.add(new ExcelBean("\u65f6\u95f4", "startDate", 0));
        excel.add(new ExcelBean("\u5b66\u53f7", "stuId", 0));
        excel.add(new ExcelBean("\u59d3\u540d", "stuName", 0));
        excel.add(new ExcelBean("\u4e13\u4e1a", "major", 0));
        excel.add(new ExcelBean("\u73ed\u7ea7", "grade", 0));
        excel.add(new ExcelBean("\u8bfe\u7a0b\u540d", "courseName", 0));
        excel.add(new ExcelBean("\u4efb\u8bfe\u6559\u5e08", "teacherName", 0));
        excel.add(new ExcelBean("\u8003\u6838\u65b9\u5f0f", "testMode", 0));
        excel.add(new ExcelBean("\u6210\u7ee9", "score", 0));
        excel.add(new ExcelBean("\u7ed3\u679c", "result", 0));
        map.put(0, excel);
        xssfWorkbook = ExcelUtil.createExcelFile(ScoreVo.class, list, map, "sheet1");
        return xssfWorkbook;
    }
}
