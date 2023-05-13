// 
// 
// 

package com.cms.service.impl;

import com.cms.entity.User;
import com.cms.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import com.cms.dao.AdminDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cms.service.Login;
import com.cms.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService, Login
{
    @Autowired
    private AdminDao adminDao;
    
    @Override
    public Admin selectAdmin(final Admin admin) {
        return this.adminDao.select(admin);
    }
    
    @Override
    public User loginValidate(final String username, final String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin = this.selectAdmin(admin);
        if (admin != null) {
            admin.setUserType("admin");
        }
        return admin;
    }
    
    @Override
    public int update(final Admin admin) {
        return this.adminDao.updateByPrimaryKeySelective(admin);
    }
}
