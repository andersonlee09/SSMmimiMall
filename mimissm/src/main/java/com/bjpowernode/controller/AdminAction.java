package com.bjpowernode.controller;

import com.bjpowernode.pojo.Admin;
import com.bjpowernode.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2021/10/25 23:30
 * @purpose null
 * @ModifiedRecords null
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {
    // 所有界面层一定又业务逻辑层的对象
    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest request) {
        Admin admin = adminService.login(name, pwd);
        if (admin != null) {
            request.setAttribute("admin", admin);
            return "main";  // 登录成功返回main页面
        }else {
            request.setAttribute("errmsg", "用户名或密码不正确");
            return "login";   // 登陆失败返回login页面
        }

    }
}
