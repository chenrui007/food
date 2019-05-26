package com.example.demo.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.ArticleMessage;
import com.example.demo.service.ArticleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 * 用户关注其他用户表 前端控制器
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@RestController
@RequestMapping("/templates/articleMessage")
public class ArticleMessageController {
    @Autowired
    private ArticleMessageService articleMessageService;

    /**
     * 添加留言
     *
     * @param articleId
     * @param message
     * @param session
     * @return
     */
    @PostMapping("/saveArticleMessage")
    public R saveArticleMessage(Long articleId, String message, HttpSession session) {
        Long userId = Long.valueOf(session.getAttribute("userId").toString());
        ArticleMessage articleMessage = new ArticleMessage();
        articleMessage.setId(IdWorker.getId());
        articleMessage.setArticleId(articleId);
        articleMessage.setMessage(message);
        articleMessage.setUserId(userId);
        articleMessage.setCreateTime(new Date());
        return articleMessageService.saveArticleMessage(articleMessage) ? R.ok("添加留言成功") : R.failed("添加留言失败");
    }

    /**
     * 删除留言
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteArticleMessage")
    public R deleteArticleMessage(Long id) {
        return articleMessageService.deleteArticleMessage(id) ? R.ok("删除留言成功") : R.failed("删除留言失败");
    }

    /**
     * 分页查看留言
     *
     * @param page
     * @param articleId
     * @return
     */
    @GetMapping("/pageArticleMessage")
    public R pageArticleMessage(Page<ArticleMessage> page, Long articleId) {
        IPage<ArticleMessage> ret = articleMessageService.pageArticleMessage(page, articleId, null);
        return R.ok(ret);
    }
}

