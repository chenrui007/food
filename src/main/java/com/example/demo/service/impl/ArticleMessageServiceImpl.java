package com.example.demo.service.impl;

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

}
