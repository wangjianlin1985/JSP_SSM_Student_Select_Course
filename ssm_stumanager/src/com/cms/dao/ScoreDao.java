// 
// 
// 

package com.cms.dao;

import com.cms.dto.ScoreVo;
import com.cms.entity.Course;
import com.cms.utils.page.Pagination;
import java.util.List;
import com.cms.entity.Score;

public interface ScoreDao
{
    int deleteByPrimaryKey(Integer p0);
    
    int insert(Score p0);
    
    int insertSelective(Score p0);
    
    Score selectByPrimaryKey(Integer p0);
    
    int updateByPrimaryKeySelective(Score p0);
    
    int updateByPrimaryKey(Score p0);
    
    int delete(Score p0);
    
    int update(List<Score> p0);
    
    List<Course> getCourseList(Pagination<Course> p0, String p1, Integer p2);
    
    int getTotalItemsCount(String p0, Integer p1);
    
    int getTotalItemsCountForExport(ScoreVo p0);
    
    List<ScoreVo> getScoreListForExport(Pagination<ScoreVo> p0, ScoreVo p1);
}
