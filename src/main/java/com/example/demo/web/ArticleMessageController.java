package com.example.demo.web;


import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.service.ArticleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户关注其他用户表 前端控制器
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Controller
@RequestMapping("/articleMessage")
public class ArticleMessageController {
    @Autowired
    private ArticleMessageService articleMessageService;

    @PostMapping("/saveArticleMessage")
    public R saveArticleMessage(Long articleId, String message, HttpSession session) {
        Long userId = Long.valueOf(session.getAttribute("userId").toString());


    }
}

