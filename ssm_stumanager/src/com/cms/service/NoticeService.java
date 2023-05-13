// 
// 
// 

package com.cms.service;

import com.cms.entity.Notice;
import java.util.List;
import com.cms.entity.Student;
import com.cms.utils.page.Pagination;

public interface NoticeService
{
    int getTotalItemsCount(Integer p0, String p1);
    
    List<Notice> getNoticeList(Pagination<Student> p0, Integer p1, String p2);
    
    int deleteNotice(Notice p0);
    
    int deleteNotice(List<Integer> p0);
    
    int addNotice(Notice p0);
    
    int updateNotice(Notice p0);
    
    Notice getNotice(Integer p0);
}
