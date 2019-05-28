package com.example.demo.web;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.MyConstants;
import com.example.demo.entity.Article;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserInfoService;
import com.example.demo.vo.UserVO;
import java.util.ArrayList;
import java.util.Comparator;
import org.apache.velocity.util.ArrayListWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p> 前端控制器 </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@RestController
@RequestMapping("/templates/userInfo")
public class UserInfoController {

  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private ArticleService articleService;

  /**
   * 登录
   */
  @PostMapping("/login")
  public R login(HttpSession session, String loginName, String password) {
    UserInfo userInfo = userInfoService.login(loginName, SecureUtil.md5(password));
    if (ObjectUtil.isNull(userInfo)) {
      return R.failed("用户账户或密码错误");
    } else {
      if (ObjectUtil.equal(MyConstants.UserStatus.stop, userInfo.getStatus())) {
        return R.failed("当前账户已被禁用，请联系管理员");
      } else if (ObjectUtil.equal(MyConstants.UserStatus.delete, userInfo.getStatus())) {
        return R.failed("当前账户不存在");
      } else if (ObjectUtil.equal(MyConstants.UserStatus.nomal, userInfo.getStatus())) {
        session.setAttribute("role", userInfo.getRole());
        session.setAttribute("loginName", userInfo.getLoginName());
        session.setAttribute("userName", userInfo.getUserName());
        session.setAttribute("userId", userInfo.getId());
        return R.ok(userInfo);
      } else {
        return R.failed("用户状态异常");
      }
    }
  }


  /**
   * 注册
   *
   * @param file 文件
   * @param loginName 登录名
   * @param userName 用户名
   * @param password 密码
   * @param sex 性别
   * @param age 年龄
   */
  @PostMapping("/register")
  public R register(@RequestParam(value = "file") MultipartFile file, String loginName,
      String userName, String password, Integer sex, Integer age) throws FileNotFoundException {
    UserInfo userInfo = userInfoService.getUserInfo(loginName, null);
    if (ObjectUtil.isNotNull(userInfo)) {
      return R.failed("当前账户已存在");
    } else {
      userInfo = new UserInfo();
      if (file.isEmpty()) {
        userInfo.setAvatar("images/t1.jpg");
      } else {
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = ResourceUtils
            .getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "templates/avatar").getPath();
        try {
          FileUtil.writeBytes(file.getBytes(), filePath + "/" + fileName);
        } catch (IOException e) {
          return R.failed("上传照片失败");
        }
        userInfo.setAvatar("avatar/" + fileName);
      }
      userInfo.setId(IdWorker.getId());
      userInfo.setLoginName(loginName);
      userInfo.setUserName(userName);
      userInfo.setPassword(SecureUtil.md5(password));
      userInfo.setSex(sex);
      userInfo.setAge(age);
      userInfo.setStatus(MyConstants.UserStatus.nomal);
      userInfo.setCreateTime(new Date());
      userInfo.setRole(MyConstants.Role.user);
      return userInfoService.saveUserInfo(userInfo) ? R.ok("注册成功") : R.failed("注册失败");
    }
  }

  /**
   * 修改用户状态
   *
   * @param userId 用户ID
   * @param userStatus 用户状态
   */
  @PostMapping("/updateUserStatus")
  public R updateUserStatus(Long userId, Integer userStatus) {
    return userInfoService.updateUserInfo(userId, userStatus, null) ? R.ok("")
        : R.failed("修改用户状态失败");
  }

  /**
   * 修改密码
   *
   */
  @PostMapping("/updateUser")
  public R updateUser(HttpSession session, String password) {
    Long userId = Long.valueOf(session.getAttribute("userId").toString());
    if (StringUtils.isEmpty(password)) {
      return R.failed("请输入密码");
    } else {
      UserInfo userInfo = userInfoService.getUserInfo(null, userId);
      if (ObjectUtil.equal(userInfo.getPassword(), password)) {
        return R.failed("不能与原密码相同");
      } else {
        return userInfoService.updateUserInfo(userId, null, password) ? R.ok("修改成功")
            : R.failed("修改失败");
      }
    }
  }

  /**
   * 分页查看用户列表
   *
   * @param userName 用户名
   * @param page 分页
   */
  @PostMapping("/pageUser")
  public R pageUser(Page<UserInfo> page, String userName) {
    IPage<UserInfo> retPage = userInfoService.pageUserInfo(page, userName);
    return R.ok(retPage);
  }

  /**
   * 删除用户
   *
   * @param userId 用户ID
   */
  @PostMapping("/deleteUser")
  public R deleteUser(Long userId) {
    return userInfoService.updateUserInfo(userId, MyConstants.UserStatus.delete, null) ? R
        .ok("删除成功") : R.failed("删除失败");
  }

  /**
   * 活跃用户排行
   */
  @GetMapping("/activeUserRanking")
  public R activeUserRanking() {
    List<UserInfo> userInfoList = userInfoService.listUserInfo();
    List<UserVO> userVOS = new ArrayList<>();
    userInfoList.stream().forEach(it->{
      UserVO userVO = new UserVO();
      BeanUtils.copyProperties(it,userVO);
      int articleNum = articleService.articleNumByUserId(it.getId());
      userVO.setArticleNum(articleNum);
      userVOS.add(userVO);
    });
    userVOS.sort(Comparator.comparing(UserVO::getArticleNum).reversed());
    return R.ok(userVOS);
  }

  /**
   * 获取用户信息
   */
  @GetMapping("/getUserInfo")
  public R getUserInfo(Long userId) {
    UserInfo userInfo = userInfoService.getById(userId);
    return R.ok(userInfo);
  }
}

