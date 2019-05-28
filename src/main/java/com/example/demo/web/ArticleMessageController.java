package com.example.demo.web;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.ArticleMessage;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.ArticleMessageService;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.ArticleMessageVO;
import com.example.demo.vo.ArticleVO;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p> 用户关注其他用户表 前端控制器 </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@RestController
@RequestMapping("/templates/articleMessage")
public class ArticleMessageController {

  @Autowired
  private ArticleMessageService articleMessageService;
  @Autowired
  private UserInfoService userInfoService;

  /**
   * 添加留言
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
    return articleMessageService.saveArticleMessage(articleMessage) ? R.ok("添加留言成功")
        : R.failed("添加留言失败");
  }

  /**
   * 删除留言
   */
  @PostMapping("/deleteArticleMessage")
  public R deleteArticleMessage(Long id) {
    return articleMessageService.deleteArticleMessage(id) ? R.ok("删除留言成功") : R.failed("删除留言失败");
  }

  /**
   * 分页查看留言
   */
  @GetMapping("/pageArticleMessage")
  public R pageArticleMessage(Page<ArticleMessage> page, Long articleId) {
    IPage<ArticleMessage> ret = articleMessageService.pageArticleMessage(page, articleId, null);
    return R.ok(ret);
  }

  /**
   * 最新留言
   */
  @GetMapping("/newMessage")
  public R newMessage() {
    List<ArticleMessage> articleMessageList = articleMessageService.list();
    List<ArticleMessageVO> articleMessageVOList = new ArrayList<>();
    articleMessageList.stream().forEach(it -> {
      ArticleMessageVO articleMessageVO = new ArticleMessageVO();
      BeanUtils.copyProperties(it, articleMessageVO);
      UserInfo userInfo = userInfoService.getById(it.getUserId());
      articleMessageVO.setMessageFrom(userInfo.getUserName());
      articleMessageVO.setAvatar(userInfo.getAvatar());
      articleMessageVO.setCreateTimeStr(DateUtil.format(it.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
      articleMessageVOList.add(articleMessageVO);
    });
    articleMessageVOList.sort(Comparator.comparing(ArticleMessageVO::getCreateTime).reversed());
    return R.ok(articleMessageVOList);
  }
}

