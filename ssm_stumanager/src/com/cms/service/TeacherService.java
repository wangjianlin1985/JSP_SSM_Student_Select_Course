// 
// 
// 

package com.cms.service;

import com.cms.utils.page.Pagination;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;
import com.cms.entity.Teacher;

public interface TeacherService
{
    Teacher selectTeacher(Teacher p0);
    
    int getTotalItemsCount(String p0);
    
    int addTeacher(Teacher p0);
    
    int updateTeacher(Teacher p0);
    
    int deleteTeacher(Teacher p0);
    
    int deleteTeacher(List<String> p0);
    
    int importExcelInfo(InputStream p0, MultipartFile p1) throws Exception;
    
    List<Teacher> getTeacher(Pagination<Teacher> p0, String p1);
    
    List<Teacher> getTeacherForSelect(String p0);
}
