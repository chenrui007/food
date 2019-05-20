package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.MyConstants;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> listCategory(String categoryName) {
        return this.list(new LambdaQueryWrapper<Category>()
                .eq(StringUtils.isNotEmpty(categoryName), Category::getCategoryName, categoryName)
                .eq(Category::getStatus, MyConstants.UserStatus.nomal));
    }

    @Override
    public boolean addCategory(Category category) {
        return this.save(category);
    }

    @Override
    public boolean deleteCategory(Long id) {
        Category category = this.getById(id);
        category.setStatus(MyConstants.UserStatus.delete);
        return this.updateById(category);
    }
}
