// 
// 
// 

package com.cms.service.impl;

import com.cms.entity.Notice;
import java.util.List;
import com.cms.entity.Student;
import com.cms.utils.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.dao.NoticeDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cms.service.NoticeService;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService
{
    @Autowired
    private NoticeDao noticeDao;
    
    @Override
    public int getTotalItemsCount(final Integer authA, final String searchKey) {
        return this.noticeDao.getTotalItemsCount(authA, searchKey);
    }
    
    @Override
    public List<Notice> getNoticeList(final Pagination<Student> page, final Integer auth, final String searchKey) {
        return this.noticeDao.getNotice(page, auth, searchKey);
    }
    
    @Override
    public int deleteNotice(final Notice notice) {
        return this.noticeDao.deleteByPrimaryKey(notice.getId());
    }
    
    @Override
    public int deleteNotice(final List<Integer> list) {
        return this.noticeDao.deleteBatch(list);
    }
    
    @Override
    public int addNotice(final Notice notice) {
        return this.noticeDao.insert(notice);
    }
    
    @Override
    public int updateNotice(final Notice notice) {
        return this.noticeDao.updateByPrimaryKeySelective(notice);
    }
    
    @Override
    public Notice getNotice(final Integer nId) {
        return this.noticeDao.selectByPrimaryKey(nId);
    }
}
