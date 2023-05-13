// 
// 
// 

package com.cms.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;
import com.cms.entity.Student;
import com.cms.utils.page.Pagination;

public interface StudentService
{
    int getTotalItemsCount(String p0);
    
    List<Student> getStudentList(Pagination<Student> p0, String p1);
    
    Student selectStudent(Student p0);
    
    int addStudent(Student p0) throws Exception;
    
    int updateStudent(Student p0);
    
    int deleteStudent(Student p0);
    
    int deleteStudent(List<String> p0);
    
    int importExcelInfo(InputStream p0, MultipartFile p1) throws Exception;
    
    int getTotalItemsCountByTid(String p0, Integer p1);
    
    List<Student> getStudentListByTid(Pagination<Student> p0, String p1, Integer p2);
}
