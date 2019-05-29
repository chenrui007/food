package com.example.demo.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.MyConstants;
import com.example.demo.common.MyConstants.UserStatus;
import com.example.demo.entity.Article;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> 文章表 服务实现类 </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements
    ArticleService {

  @Override
  public IPage<Article> pageArticle(Page<Article> page, String articleName, Long categoryId,
      Integer canRead, List<Long> articleIds, Long authorId) {
    return this.page(page, new LambdaQueryWrapper<Article>()
        .like(StringUtils.isNotEmpty(articleName), Article::getArticleName, articleName)
        .eq(ObjectUtil.isNotNull(categoryId), Article::getCategoryId, categoryId)
        .in(CollectionUtil.isNotEmpty(articleIds), Article::getId, articleIds)
        .eq(Article::getStatus, MyConstants.UserStatus.nomal)
        .eq(ObjectUtil.isNotNull(canRead), Article::getCanRead, canRead)
        .eq(ObjectUtil.isNotNull(authorId), Article::getAuthor, authorId)
        .orderByDesc(Article::getCreateTime));
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
    return this.save(article);
  }

  @Override
  public boolean updateArticle(Article article) {
    return this.updateById(article);
  }

  @Override
  public List<Article> listArticle() {
    return this.list(
        new LambdaQueryWrapper<Article>().eq(Article::getStatus, MyConstants.UserStatus.nomal));
  }

  @Override
  public int articleNumByUserId(Long userId) {
    return this.count(new LambdaQueryWrapper<Article>()
        .eq(Article::getAuthor, userId)
        .eq(Article::getStatus, UserStatus.nomal));
  }
}
