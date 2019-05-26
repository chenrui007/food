package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.ArticleInfo;
import com.example.demo.mapper.ArticleInfoMapper;
import com.example.demo.service.ArticleInfoService;
import org.springframework.stereotype.Service;

/**
 * <p> 文章信息表 服务实现类 </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Service
public class ArticleInfoServiceImpl extends ServiceImpl<ArticleInfoMapper, ArticleInfo> implements
    ArticleInfoService {

  @Override
  public boolean saveArticleInfo(ArticleInfo articleInfo) {
    return this.save(articleInfo);
  }

  @Override
  public ArticleInfo getByArticleId(Long articleId) {
    return this.getOne(new LambdaQueryWrapper<ArticleInfo>().eq(ArticleInfo::getArticleId, articleId));
  }

  @Override
  public boolean updateArticleInfo(ArticleInfo articleInfo) {
    return this.updateById(articleInfo);
  }
}
