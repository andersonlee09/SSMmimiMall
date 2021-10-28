package com.bjpowernode.test;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.vo.ProductInfoVo;
import com.bjpowernode.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2021/10/25 23:00
 * @purpose null
 * @ModifiedRecords null
 */
@RunWith(SpringJUnit4ClassRunner.class)  // 集成测试
@ContextConfiguration(locations = {"classpath:applicationContext_dao.xml", "classpath:applicationContext_service.xml"})  // 配置文件
public class MyTest {
    @Autowired
    ProductInfoMapper mapper;

    @Test
    public void testSelectCondition() {
        ProductInfoVo vo = new ProductInfoVo();
        List<ProductInfo> list = mapper.selectCondition(vo);
        list.forEach(System.out::println);

    }
}
