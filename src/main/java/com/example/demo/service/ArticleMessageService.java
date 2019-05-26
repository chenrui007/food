package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.ArticleMessage;

import java.util.List;

/**
 * <p>
 * 文章留言 服务类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public interface ArticleMessageService extends IService<ArticleMessage> {
    /**
     * 添加文章留言
     * @param articleMessage
     * @return
     */
    boolean saveArticleMessage(ArticleMessage articleMessage);

    /**
     * 删除留言
     *
     * @param id
     * @return
     */
    boolean deleteArticleMessage(Long id);

    /**
     * 分页获取留言
     *
     * @param articleId
     * @param userId
     * @return
     */
    IPage<ArticleMessage> pageArticleMessage(Page<ArticleMessage> page, Long articleId, Long userId);


}
