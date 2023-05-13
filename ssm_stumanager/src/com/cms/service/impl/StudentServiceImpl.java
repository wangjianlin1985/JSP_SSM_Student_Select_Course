// 
// 
// 

package com.cms.service.impl;

import com.cms.entity.User;
import java.util.Iterator;
import com.cms.utils.MD5Util;
import java.util.ArrayList;
import com.cms.utils.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;
import com.cms.entity.Student;
import com.cms.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.dao.StudentDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cms.service.Login;
import com.cms.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService, Login
{
    @Autowired
    private StudentDao studentDao;
    
    @Override
    public int getTotalItemsCount(final String searchKey) {
        return this.studentDao.getTotalItemsCount(searchKey);
    }
    
    @Override
    public List<Student> getStudentList(final Pagination<Student> page, final String searchKey) {
        return this.studentDao.selectBySearchKey(page, searchKey);
    }
    
    @Override
    public Student selectStudent(final Student stu) {
        return this.studentDao.select(stu);
    }
    
    @Override
    public int addStudent(final Student stu) throws Exception {
        return this.studentDao.insert(stu);
    }
    
    @Override
    public int updateStudent(final Student stu) {
        return this.studentDao.updateByPrimaryKeySelective(stu);
    }
    
    @Override
    public int deleteStudent(final Student stu) {
        return this.studentDao.deleteByPrimaryKey(stu.getId());
    }
    
    @Override
    public int deleteStudent(final List<String> list) {
        return this.studentDao.deleteInList(list);
    }
    
    @Override
    public int importExcelInfo(final InputStream in, final MultipartFile file) throws Exception {
        final List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, file.getOriginalFilename());
        final List<Student> list = new ArrayList<Student>();
        for (final List<Object> ob : listob) {
            final Student Student = new Student();
            Student.setId(String.valueOf(ob.get(0)));
            Student.setPassword(MD5Util.MD5((String)ob.get(1)));
            Student.setName(String.valueOf(ob.get(2)));
            Student.setSex(String.valueOf(ob.get(3)));
            Student.setAdmissionDate(String.valueOf(ob.get(4)));
            Student.setMajor(String.valueOf(ob.get(5)));
            Student.setGrade(String.valueOf(ob.get(6)));
            Student.setEducation(String.valueOf(ob.get(7)));
            list.add(Student);
        }
        return this.studentDao.insertBatch(list);
    }
    
    @Override
    public User loginValidate(final String username, final String password) {
        Student student = new Student();
        student.setId(username);
        student.setPassword(password);
        student = this.selectStudent(student);
        if (student != null) {
            student.setUserType("student");
        }
        return student;
    }
    
    @Override
    public int getTotalItemsCountByTid(final String id, final Integer cId) {
        return this.studentDao.getTotalItemsCountByTid(id, cId);
    }
    
    @Override
    public List<Student> getStudentListByTid(final Pagination<Student> page, final String id, final Integer cId) {
        return this.studentDao.getStudentListByTid(page, id, cId);
    }
}
