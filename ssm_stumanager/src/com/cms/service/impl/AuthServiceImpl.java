// 
// 
// 

package com.cms.service.impl;

import com.cms.utils.page.Pagination;
import com.cms.entity.Auth;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.dao.AuthDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cms.service.AuthService;

@Service
@Transactional
public class AuthServiceImpl implements AuthService
{
    @Autowired
    private AuthDao authDao;
    
    @Override
    public List<Auth> getMenuList(final String userType) {
        return this.authDao.selectMenu(userType);
    }
    
    @Override
    public List<Auth> getUrlList(final String userType) {
        return this.authDao.selectUrl(userType);
    }
    
    @Override
    public int getTotalItemsCount(final String searchKey) {
        return this.authDao.getTotalItemsCount(searchKey);
    }
    
    @Override
    public List<Auth> getAuthList(final Pagination<Auth> page, final String searchKey) {
        return this.authDao.getAuthList(page, searchKey);
    }
    
    @Override
    public int update(final Auth auth) {
        return this.authDao.updateByPrimaryKeySelective(auth);
    }
}
