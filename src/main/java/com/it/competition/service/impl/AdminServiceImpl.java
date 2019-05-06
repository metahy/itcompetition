package com.it.competition.service.impl;

import com.it.competition.dao.AdminMapper;
import com.it.competition.dao.StudentMapper;
import com.it.competition.domain.Admin;
import com.it.competition.domain.AdminExample;
import com.it.competition.domain.Student;
import com.it.competition.domain.StudentExample;
import com.it.competition.service.AdminService;
import com.it.competition.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(Admin admin) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(admin.getUserName()).andPasswordEqualTo(admin.getPassword());
        List<Admin> admins = adminMapper.selectByExample(example);
        return admins != null && admins.size() > 0 ? admins.get(0) : null;
    }
}
