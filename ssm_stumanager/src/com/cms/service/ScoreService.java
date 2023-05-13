// 
// 
// 

package com.cms.service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.cms.dto.ScoreVo;
import com.cms.entity.Course;
import com.cms.utils.page.Pagination;
import java.util.List;
import com.cms.entity.Score;

public interface ScoreService
{
    int choiceCourse(Score p0);
    
    int updateScore(Score p0);
    
    int deleteScore(Score p0);
    
    int updateScore(List<Score> p0);
    
    int getTotalItemsCount(String p0, Integer p1);
    
    List<Course> getCourseList(Pagination<Course> p0, String p1, Integer p2);
    
    int getTotalItemsCount(ScoreVo p0);
    
    List<ScoreVo> getScoreList(Pagination<ScoreVo> p0, ScoreVo p1);
    
    XSSFWorkbook exportExcelInfo(ScoreVo p0) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, IllegalAccessException;
}
