package com.bjpowernode.service.imlp;

import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2021/10/25 23:04
 * @purpose null
 * @ModifiedRecords null
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {
        // 根据传入的用户或DB中查询相应用户对象
        AdminExample example = new AdminExample();
        // 查到后进行密码比对
        /*
          数据库查询语句
          select * from admins where a_name = 'admin'
         */
        // 这里是对数据库语句进行一个封装
        example.createCriteria().andANameEqualTo(name);
        List<Admin> list = adminMapper.selectByExample(example);
        if (list.size() > 0) {
            Admin admin = list.get(0);
            if (admin.getaPass().equals(pwd)) {
                return admin;
            }
        }
        return null;


    }
}
