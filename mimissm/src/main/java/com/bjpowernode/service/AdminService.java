package com.bjpowernode.service;

import com.bjpowernode.pojo.Admin;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2021/10/25 23:03
 * @purpose null
 * @ModifiedRecords null
 */
public interface AdminService {
    // 完成登录判断
    Admin login(String name, String pwd);
}
