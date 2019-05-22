package com.example.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.MyConstants;
import com.example.demo.entity.Article;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Override
    public IPage<Article> pageArticle(Page<Article> page, String articleName, Long categoryId, Integer canRead) {
        return this.page(page, new LambdaQueryWrapper<Article>()
                .eq(Article::getArticleName, articleName)
                .eq(Article::getCategoryId, categoryId)
                .eq(Article::getStatus, MyConstants.UserStatus.nomal)
                .eq(ObjectUtil.isNotNull(canRead), Article::getCanRead, canRead));
    }

    @Override
    public boolean deleteArticle(Long articleId) {
        Article article = this.getById(articleId);
        article.setStatus(MyConstants.UserStatus.delete);
        return this.updateById(article);
    }

    @Override
    public boolean changeArticle(Long articleId, Integer canRead) {
        Article article = this.getById(articleId);
        article.setCanRead(canRead);
        return this.updateById(article);
    }

    @Override
    public boolean saveArticle(Article article) {
        return false;
    }

    @Override
    public boolean updateArticle() {
        return false;
    }

    @Override
    public List<Article> listArticle() {
        return this.list(new LambdaQueryWrapper<Article>().eq(Article::getStatus, MyConstants.UserStatus.nomal));
    }
}
