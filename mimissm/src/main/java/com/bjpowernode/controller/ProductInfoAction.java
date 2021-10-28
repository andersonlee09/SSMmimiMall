package com.bjpowernode.controller;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.vo.ProductInfoVo;
import com.bjpowernode.service.ProductInfoService;
import com.bjpowernode.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author andersonLee
 * @version 1.0
 * @date 2021/10/26 13:16
 * @purpose null
 * @ModifiedRecords null
 */
@Controller
@RequestMapping("/prod")
public class ProductInfoAction {

    public static final int PAGE_SIZE = 5;
    // 异步上传图片名称
    private String saveFileName = "";
    @Autowired
    ProductInfoService productInfoService;

    // 显示全部商品不分页
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request) {
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list", list);
        return "product";
    }

    // 显示1页的5条数据
    @RequestMapping("/split")
    public String split(HttpServletRequest request) {
        PageInfo pageInfo = productInfoService.splitPage(1, PAGE_SIZE);
        request.setAttribute("info", pageInfo);
        return "product";
    }

    // ajax 分页翻页处理
    @ResponseBody
    @RequestMapping("/ajaxSplit")
    public void ajaxSplit(ProductInfoVo vo, HttpSession session) {
        // 获取当前page参数
        PageInfo info = productInfoService.splitPageVo(vo, PAGE_SIZE);
        session.setAttribute("info", info);
    }

    // 异步Ajax文件上传
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request) {  // MultipartFile为springmvc专门用于处理文件的包
        // 提取生成文件名
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        // 得到项目的图片路径
        String path = request.getServletContext().getRealPath("/image_big");
        // 转存  D:\Users\Code\SSM\Spring\mimissm\123.jpg
        try {
            pimage.transferTo(new File(path + File.separator + saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject object = new JSONObject();
        object.put("imgurl", saveFileName);
        return object.toString();
    }

    //
    @RequestMapping("/save")
    public String save(ProductInfo info, HttpServletRequest request) {
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        // info对象有表单上传的5个数据  有异步Ajax上传的图片等
        int num = -1;
        try {
            num = productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0) {
            request.setAttribute("msg", "增加成功！");
        } else {
            request.setAttribute("msg", "增加失败！");
        }
        saveFileName = "";
        // 增加成功后应该重新访问数据库  跳转到分页显示的action
        return "forward:/prod/split.action";
    }

    @RequestMapping("/one")
    public String one(int pid, Model model) {
        ProductInfo info = productInfoService.getByID(pid);
        model.addAttribute("prod", info);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo info, HttpServletRequest request) {
        if (!saveFileName.equals("")) {
            info.setpImage(saveFileName);
        }
        int num = -1;
        try {
            num = productInfoService.update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0) {
            request.setAttribute("msg", "增加成功！");
        } else {
            request.setAttribute("msg", "增加失败！");
        }
        saveFileName = "";
        return "redirect:/prod/split.action";
    }

    @RequestMapping("/delete")
    public String delete(int pid, HttpServletRequest request) {
        int num = -1;
        System.out.println("produce!!");
        try {
            num = productInfoService.delete(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0) {
            request.setAttribute("msg", "删除成功！");
        } else {
            request.setAttribute("msg", "删除失败！");
        }
        return "forward:/prod/deleteAjaxSplit.action";
    }
    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit", produces = "text/html;charset=UTF-8")
    public Object deleteAjaxSplit(HttpServletRequest request) {
        PageInfo info = productInfoService.splitPage(1,PAGE_SIZE);
        request.getSession().setAttribute("info", info);
        return request.getAttribute("msg");
    }

    @RequestMapping("/deleteBatch")
    public String deleteBatch(String pids, HttpServletRequest request) {
        String []ps = pids.split(",");
        int num = productInfoService.deleteBatch(ps);
        try {
            if (num > 0) {
                request.setAttribute("msg", "删除成功！");
            } else {
                request.setAttribute("msg", "删除失败！");
            }
        } catch (Exception e) {
            request.setAttribute("msg", "当前商品不可删除！");
        }
    return "forward:/prod/deleteAjaxSplit.action";
    }
    @ResponseBody
    @RequestMapping("/condition")
    public void condition(ProductInfoVo vo, HttpSession session) {
        List<ProductInfo> list = productInfoService.selectCondition(vo);
        session.setAttribute("list", list);

    }
}
