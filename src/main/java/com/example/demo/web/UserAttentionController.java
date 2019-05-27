package com.example.demo.web;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.MyConstants;
import com.example.demo.entity.UserAttention;
import com.example.demo.entity.UserCollection;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserAttentionService;
import com.example.demo.service.UserInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 用户关注其他用户表 前端控制器 </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@RestController
@RequestMapping("/templates/userAttention")
public class UserAttentionController {

  @Autowired
  private UserAttentionService userAttentionService;
  @Autowired
  private ArticleService articleService;
  @Autowired
  private UserInfoService userInfoService;

  /**
   * 添加或移除关注
   */
  @PostMapping("/addOrRemoveAttention")
  public R addOrRemoveAttention(HttpSession session, Long userId, Integer type) {
    Long currentUserId = Long.valueOf(session.getAttribute("userId").toString());
    if (ObjectUtil.equal(type, MyConstants.Operation.add)) {
      return userAttentionService.addAttention(currentUserId, userId) ? R.ok("添加成功")
          : R.failed("添加失败");
    } else if (ObjectUtil.equal(type, MyConstants.Operation.remove)) {
      return userAttentionService.removeAttention(currentUserId, userId) ? R.ok("移除成功")
          : R.failed("移除失败");
    } else {
      return R.failed("操作类型异常");
    }
  }

  /**
   * 查看用户关注状态
   */
  @GetMapping("/selAttention")
  public R selAttention(HttpSession session, Long attentionUserId) {
    Long userId = Long.valueOf(session.getAttribute("userId").toString());
    UserAttention userAttention = userAttentionService.getUserAttention(userId, attentionUserId);
    return R.ok(userAttention);
  }

  /**
   * 我的关注
   */
  @GetMapping("/myAttention")
  public R myAttention(HttpSession session) {
    Long userId = Long.valueOf(session.getAttribute("userId").toString());
    List<UserAttention> userAttentionList = userAttentionService.listUserAttention(userId);
    userAttentionList.stream().forEach(it->{
      int articleNum = articleService.articleNumByUserId(it.getAttentionUserId());
      UserInfo userInfo = userInfoService.getById(it.getAttentionUserId());
      it.setUserName(userInfo.getUserName());
      it.setAttentionUserArticleNum(articleNum);
    });
    return R.ok(userAttentionList);
  }
}

