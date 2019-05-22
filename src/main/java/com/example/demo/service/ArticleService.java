package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Article;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public interface ArticleService extends IService<Article> {
    /**
     * 分页查看文章
     *
     * @param page
     * @param articleName
     * @return
     */
    IPage<Article> pageArticle(Page<Article> page, String articleName, Long categoryId, Integer canRead);

    /**
     * 删除文章
     *
     * @param articleId
     * @return
     */
    boolean deleteArticle(Long articleId);

    /**
     * 修改文章阅读状态
     * @param articleId
     * @param canRead
     * @return
     */
    boolean changeArticle(Long articleId,Integer canRead);

    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    boolean saveArticle(Article article);

    /**
     * 修改文章
     *
     * @return
     */
    boolean updateArticle();

    /**
     * 获取文章列表
     * @return
     */
    List<Article> listArticle();
}
