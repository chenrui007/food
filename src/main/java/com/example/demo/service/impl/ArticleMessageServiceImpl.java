package com.example.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.ArticleMessage;
import com.example.demo.mapper.ArticleMessageMapper;
import com.example.demo.service.ArticleMessageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户关注其他用户表 服务实现类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Service
public class ArticleMessageServiceImpl extends ServiceImpl<ArticleMessageMapper, ArticleMessage> implements ArticleMessageService {

    @Override
    public boolean saveArticleMessage(ArticleMessage articleMessage) {
        return this.save(articleMessage);
    }

    @Override
    public boolean deleteArticleMessage(Long id) {
        return this.removeById(id);
    }

    @Override
    public IPage<ArticleMessage> pageArticleMessage(Page<ArticleMessage> page, Long articleId, Long userId) {
        return this.page(page, new LambdaQueryWrapper<ArticleMessage>()
                .eq(ObjectUtil.isNotNull(articleId), ArticleMessage::getArticleId, articleId)
                .eq(ObjectUtil.isNotNull(userId), ArticleMessage::getUserId, userId));
    }
}
