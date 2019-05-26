package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Article;

import java.util.List;

/**
 * <p> 文章表 服务类 </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public interface ArticleService extends IService<Article> {

  /**
   * 分页查看文章
   */
  IPage<Article> pageArticle(Page<Article> page, String articleName, Long categoryId,
      Integer canRead, List<Long> articleIds, Long authorId);

  /**
   * 删除文章
   */
  boolean deleteArticle(Long articleId);

  /**
   * 修改文章阅读状态
   */
  boolean changeArticle(Long articleId, Integer canRead);

  /**
   * 添加文章
   */
  boolean saveArticle(Article article);

  /**
   * 修改文章
   */
  boolean updateArticle();

  /**
   * 获取文章列表
   */
  List<Article> listArticle();
}
