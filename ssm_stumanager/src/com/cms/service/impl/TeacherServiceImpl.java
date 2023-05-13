// 
// 
// 

package com.cms.service.impl;

import com.cms.entity.User;
import com.cms.utils.page.Pagination;
import java.util.Iterator;
import com.cms.utils.MD5Util;
import java.util.ArrayList;
import com.cms.utils.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;
import com.cms.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.dao.TeacherDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cms.service.Login;
import com.cms.service.TeacherService;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService, Login
{
    @Autowired
    private TeacherDao teacherDao;
    
    @Override
    public Teacher selectTeacher(final Teacher teacher) {
        return this.teacherDao.selectTeacher(teacher);
    }
    
    @Override
    public int getTotalItemsCount(final String searchKey) {
        return this.teacherDao.getTotalItemsCount(searchKey);
    }
    
    @Override
    public int addTeacher(final Teacher t) {
        return this.teacherDao.insert(t);
    }
    
    @Override
    public int updateTeacher(final Teacher t) {
        return this.teacherDao.updateByPrimaryKeySelective(t);
    }
    
    @Override
    public int deleteTeacher(final Teacher t) {
        return this.teacherDao.deleteByPrimaryKey(t.getId());
    }
    
    @Override
    public int deleteTeacher(final List<String> list) {
        return this.teacherDao.deleteInList(list);
    }
    
    @Override
    public int importExcelInfo(final InputStream in, final MultipartFile file) throws Exception {
        final List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, file.getOriginalFilename());
        final List<Teacher> list = new ArrayList<Teacher>();
        for (final List<Object> ob : listob) {
            final Teacher t = new Teacher();
            t.setId(String.valueOf(ob.get(0)));
            t.setPassword(MD5Util.MD5((String)ob.get(1)));
            t.setName(String.valueOf(ob.get(2)));
            t.setSynopsis(String.valueOf(ob.get(3)));
            list.add(t);
        }
        return this.teacherDao.insertBatch(list);
    }
    
    @Override
    public List<Teacher> getTeacher(final Pagination<Teacher> page, final String searchKey) {
        return this.teacherDao.getTeacher(page, searchKey);
    }
    
    @Override
    public List<Teacher> getTeacherForSelect(final String searchKey) {
        return this.teacherDao.getTeacherForSelect(searchKey);
    }
    
    @Override
    public User loginValidate(final String username, final String password) {
        Teacher teacher = new Teacher();
        teacher.setId(username);
        teacher.setPassword(password);
        teacher = this.selectTeacher(teacher);
        if (teacher != null) {
            teacher.setUserType("teacher");
        }
        return teacher;
    }
}
