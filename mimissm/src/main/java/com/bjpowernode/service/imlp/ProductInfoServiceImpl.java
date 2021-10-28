package com.bjpowernode.service.imlp;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.ProductInfoExample;
import com.bjpowernode.pojo.vo.ProductInfoVo;
import com.bjpowernode.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2021/10/26 13:13
 * @purpose null
 * @ModifiedRecords null
 */
@Service()
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoMapper productInfoMapper;
    @Override  // 获取所有商品
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    /*
    select * from product_info limit 10,5
     */
    @Override  // 实现分页功能
    public PageInfo splitPage(int pageNum, int pageSize) {
        // 使用分页插件完成该工具类的分页设置
        PageHelper.startPage(pageNum, pageSize);
        ProductInfoExample example = new ProductInfoExample();
        // 设置主键 按降序排序
        example.setOrderByClause("p_id desc");
        // 排序完成后取集合  进行分页设置
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        PageInfo<ProductInfo> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    @Override
    public ProductInfo getByID(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int delete(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }

    @Override
    public List<ProductInfo> selectCondition(ProductInfoVo productInfoVo) {
        return productInfoMapper.selectCondition(productInfoVo);
    }

    @Override
    public PageInfo<ProductInfo> splitPageVo(ProductInfoVo vo, int pageSize) {
        PageHelper.startPage(vo.getPage(), pageSize);
        List<ProductInfo> list = productInfoMapper.selectCondition(vo);
        return new PageInfo(list);
    }
}
