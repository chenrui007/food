package com.example.demo.web;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.MyConstants;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Controller
@RequestMapping("/templates/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 分类列表
     *
     * @param categoryName 分类名称
     * @return
     */
    @GetMapping("/listCategory")
    public R listCategory(String categoryName) {
        List<Category> categoryList = categoryService.listCategory(categoryName);
        return R.ok(categoryList);
    }

    /**
     * 添加分类
     *
     * @param categoryName
     * @return
     */
    @PostMapping("/addCategory")
    public R addCategory(String categoryName) {
        List<Category> categoryList = categoryService.listCategory(categoryName);
        if (CollectionUtil.isNotEmpty(categoryList)) {
            return R.failed("当前分类已存在");
        } else {
            Category category = new Category();
            category.setId(IdWorker.getId());
            category.setCategoryName(categoryName);
            category.setCreateTime(new Date());
            category.setStatus(MyConstants.UserStatus.nomal);
            return categoryService.addCategory(category) ? R.ok("添加成功") : R.failed("添加失败");
        }
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteCategory")
    public R deleteCategory(Long id) {
        return categoryService.deleteCategory(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}

