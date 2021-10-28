package com.bjpowernode.service;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.vo.ProductInfoVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2021/10/26 13:11
 * @purpose 用于开发商品展示
 * @ModifiedRecords null
 */
public interface ProductInfoService {
    // 显示全部商品 不分页
    List<ProductInfo> getAll();
    /*
    select * from product_info limit 10,5
     */
    // 实现分页功能
    PageInfo splitPage(int pageNum, int pageSize);

    // 实现商品提交
    int save(ProductInfo info);
    // 按主键id查询商品
    ProductInfo getByID(int pid);
    // 更新单个
    public int update(ProductInfo info);
    // 单个商品删除
    int delete(int pid);
    // 批量删除商品功能
    int deleteBatch(String []ids);

    // 多条件商品查询
    List<ProductInfo> selectCondition(ProductInfoVo productInfoVo);

    // 多条件查询分页
    PageInfo<ProductInfo> splitPageVo(ProductInfoVo vo, int pageSize);
}
