package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.ArticleInfo;

/**
 * <p>
 * 文章信息表 服务类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public interface ArticleInfoService extends IService<ArticleInfo> {
    /**
     * 添加文章信息
     *
     * @param articleInfo
     * @return
     */
    boolean saveArticleInfo(ArticleInfo articleInfo);

    /**
     * 通过文章ID查询
     * @param articleId
     * @return
     */
    ArticleInfo getByArticleId(Long articleId);

    /**
     * 修改
     * @param articleInfo
     * @return
     */
    boolean updateArticleInfo(ArticleInfo articleInfo);
}
