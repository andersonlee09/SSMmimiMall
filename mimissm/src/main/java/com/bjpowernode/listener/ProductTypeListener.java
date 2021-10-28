package com.bjpowernode.listener;

import com.bjpowernode.pojo.ProductType;
import com.bjpowernode.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2021/10/26 19:25
 * @purpose 这是一个全局的监听器  // 当有关页面打开时会自动有typeList
 * @ModifiedRecords null
 */
@WebListener
public class ProductTypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 手工从spring容器中取出ProductTypeService对象
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_*");
        ProductTypeService productTypeService = (ProductTypeService) context.getBean("ProductTypeServiceImpl");  // ProductTypeServiceImpl类的service注解名注入的名字
        List<ProductType> typeList = productTypeService.getAll();
        // 放入全局应用作用域中，供新增页面，修改页面前台的查询功能提供类型集合
        servletContextEvent.getServletContext().setAttribute("typeList", typeList);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
