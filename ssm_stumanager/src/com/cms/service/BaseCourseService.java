// 
// 
// 

package com.cms.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;
import com.cms.entity.BaseCourse;
import com.cms.utils.page.Pagination;

public interface BaseCourseService
{
    int getTotalItemsCount(String p0);
    
    List<BaseCourse> getBaseCourse(Pagination<BaseCourse> p0, String p1);
    
    List<BaseCourse> getBaseCourseForSelect(String p0);
    
    int addBaseCourse(BaseCourse p0);
    
    int updateBaseCourse(BaseCourse p0);
    
    int deleteBaseCourse(List<Integer> p0);
    
    int importExcelInfo(InputStream p0, MultipartFile p1) throws Exception;
    
    int deleteBaseCourse(BaseCourse p0);
}
