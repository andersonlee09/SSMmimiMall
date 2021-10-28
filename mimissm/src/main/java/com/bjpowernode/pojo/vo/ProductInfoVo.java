package com.bjpowernode.pojo.vo;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2021/10/27 13:07
 * @purpose 为了多条件查询而做的类
 * @ModifiedRecords null
 */
public class ProductInfoVo {
    private String pname;  // 商品名
    private String typeid;  // 种类id
    private Integer lprice;  // 最低价格
    private Integer hprice;  // 最高价格
    private Integer page=1; // 页码赋初值为1

    public ProductInfoVo() {
    }

    public ProductInfoVo(String pname, String typeid, Integer lprice, Integer hprice, Integer page) {
        this.pname = pname;
        this.typeid = typeid;
        this.lprice = lprice;
        this.hprice = hprice;
        this.page = page;
    }

    @Override
    public String toString() {
        return "ProductInfoVo{" +
                "pname='" + pname + '\'' +
                ", typeid='" + typeid + '\'' +
                ", lprice=" + lprice +
                ", hprice=" + hprice +
                ", page=" + page +
                '}';
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public Integer getLprice() {
        return lprice;
    }

    public void setLprice(Integer lprice) {
        this.lprice = lprice;
    }

    public Integer getHprice() {
        return hprice;
    }

    public void setHprice(Integer hprice) {
        this.hprice = hprice;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
