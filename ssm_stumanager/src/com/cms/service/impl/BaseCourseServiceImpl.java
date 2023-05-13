// 
// 
// 

package com.cms.service.impl;

import java.util.Iterator;
import java.util.ArrayList;
import com.cms.utils.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;
import com.cms.entity.BaseCourse;
import com.cms.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.dao.BaseCourseDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cms.service.BaseCourseService;

@Service
@Transactional
public class BaseCourseServiceImpl implements BaseCourseService
{
    @Autowired
    BaseCourseDao baseCourseDao;
    
    @Override
    public int getTotalItemsCount(final String searchKey) {
        return this.baseCourseDao.getTotalItemsCount(searchKey);
    }
    
    @Override
    public List<BaseCourse> getBaseCourse(final Pagination<BaseCourse> page, final String searchKey) {
        return this.baseCourseDao.getBaseCourse(page, searchKey);
    }
    
    @Override
    public List<BaseCourse> getBaseCourseForSelect(final String searchKey) {
        return this.baseCourseDao.getBaseCourseForSelect(searchKey);
    }
    
    @Override
    public int addBaseCourse(final BaseCourse baseCourse) {
        return this.baseCourseDao.insertSelective(baseCourse);
    }
    
    @Override
    public int updateBaseCourse(final BaseCourse baseCourse) {
        return this.baseCourseDao.updateByPrimaryKeySelective(baseCourse);
    }
    
    @Override
    public int deleteBaseCourse(final BaseCourse t) {
        return this.baseCourseDao.deleteByPrimaryKey(t.getId());
    }
    
    @Override
    public int deleteBaseCourse(final List<Integer> list) {
        return this.baseCourseDao.deleteInList(list);
    }
    
    @Override
    public int importExcelInfo(final InputStream in, final MultipartFile file) throws Exception {
        final List<List<Object>> listob = ExcelUtil.getBankListByExcel(in, file.getOriginalFilename());
        final List<BaseCourse> list = new ArrayList<BaseCourse>();
        for (final List<Object> ob : listob) {
            final BaseCourse course = new BaseCourse();
            course.setName(String.valueOf(ob.get(0)));
            course.setSynopsis(String.valueOf(ob.get(1)));
            list.add(course);
        }
        return this.baseCourseDao.insertBatch(list);
    }
}
