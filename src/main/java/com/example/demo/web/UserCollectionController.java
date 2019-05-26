package com.example.demo.web;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.MyConstants;
import com.example.demo.entity.Article;
import com.example.demo.entity.UserCollection;
import com.example.demo.service.ArticleInfoService;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserCollectionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p> 用户收藏表 前端控制器 </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@RestController
@RequestMapping("/templates/userCollection")
public class UserCollectionController {

  @Autowired
  private UserCollectionService userCollectionService;
  @Autowired
  private ArticleService articleService;
  @Autowired
  private ArticleInfoService articleInfoService;

  /**
   * 添加或移除收藏
   *
   * @param articleId 文章ID
   */
  @PostMapping("/addOrRemoveCollection")
  public R addOrRemoveCollection(HttpSession session, Long articleId, Integer type) {
    Long userId = Long.valueOf(session.getAttribute("userId").toString());
    if (ObjectUtil.equal(type, MyConstants.Operation.add)) {
      return userCollectionService.addCollection(userId, articleId) ? R.ok("添加成功")
          : R.failed("添加失败");
    } else if (ObjectUtil.equal(type, MyConstants.Operation.remove)) {
      return userCollectionService.removeCollection(userId, articleId) ? R.ok("移除成功")
          : R.failed("移除失败");
    } else {
      return R.failed("操作类型异常");
    }
  }

  /**
   * 查看用户收藏状态
   */
  @GetMapping("/selCollection")
  public R selCollection(HttpSession session, Long articleId) {
    Long userId = Long.valueOf(session.getAttribute("userId").toString());
    UserCollection userCollection = userCollectionService.getUserCollection(userId, articleId);
    return R.ok(userCollection);
  }

  /**
   * 用户收藏
   */
  @GetMapping("/listCollection")
  public R listCollection(Page<Article> page, HttpSession session) {
    Long userId = Long.valueOf(session.getAttribute("userId").toString());

    List<UserCollection> userCollectionList = userCollectionService.listUserCollection(userId);
    List<Long> articleIds = userCollectionList.stream().map(it -> it.getArticleId()).collect(
        Collectors.toList());
    IPage<Article> ret = new Page<>();
    if (CollectionUtil.isNotEmpty(articleIds)) {
      ret = articleService
          .pageArticle(page, null, null, MyConstants.UserStatus.nomal, articleIds, null);
      ret.getRecords().stream().forEach(it -> {
        it.setReadNum(articleInfoService.getByArticleId(it.getId()).getReadingVolume());
      });
    }
    return R.ok(ret);
  }
}

