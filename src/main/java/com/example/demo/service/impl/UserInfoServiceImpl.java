package com.example.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.MyConstants;
import com.example.demo.common.MyConstants.Role;
import com.example.demo.common.MyConstants.UserStatus;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p> 服务实现类 </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements
    UserInfoService {

  @Override
  public UserInfo login(String loginName, String password) {
    return this.getOne(new LambdaQueryWrapper<UserInfo>()
        .eq(UserInfo::getLoginName, loginName)
        .eq(UserInfo::getPassword, password));
  }

  @Override
  public UserInfo getUserInfo(String loginName, Long userId) {
    return this.getOne(new LambdaQueryWrapper<UserInfo>()
        .eq(StringUtils.isNotEmpty(loginName), UserInfo::getLoginName, loginName)
        .eq(ObjectUtil.isNotNull(userId), UserInfo::getId, userId)
        .ne(UserInfo::getStatus, MyConstants.UserStatus.delete));
  }

  @Override
  public boolean saveUserInfo(UserInfo userInfo) {
    return this.save(userInfo);
  }

  @Override
  public boolean updateUserInfo(Long userId, Integer userStatus, String password) {
    UserInfo userInfo = this.getById(userId);
    if (ObjectUtil.isNotNull(userStatus)) {
      userInfo.setStatus(userStatus);
    }
    if (StringUtils.isNotEmpty(password)) {
      userInfo.setPassword(password);
    }
    return this.updateById(userInfo);
  }

  @Override
  public IPage<UserInfo> pageUserInfo(Page<UserInfo> page, String userName) {
    return this.page(page, new LambdaQueryWrapper<UserInfo>()
        .eq(StringUtils.isNotEmpty(userName), UserInfo::getUserName, userName)
        .ne(UserInfo::getStatus, MyConstants.UserStatus.delete));
  }

  @Override
  public List<UserInfo> listUserInfo() {
    return this.list(new LambdaQueryWrapper<UserInfo>()
        .eq(UserInfo::getStatus, UserStatus.nomal)
        .eq(UserInfo::getRole, Role.user));
  }
}
