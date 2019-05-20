package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Category;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public interface CategoryService extends IService<Category> {
    /**
     * 查询分类列表
     *
     * @param categoryName
     * @return
     */
    List<Category> listCategory(String categoryName);

    /**
     * 添加分类
     *
     * @param category
     * @return
     */
    boolean addCategory(Category category);

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    boolean deleteCategory(Long id);
}
