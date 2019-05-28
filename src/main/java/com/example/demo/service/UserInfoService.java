package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.UserInfo;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @return
     */
    UserInfo login(String loginName, String password);

    /**
     * 获取用户信息
     *
     * @param loginName
     * @return
     */
    UserInfo getUserInfo(String loginName, Long userId);

    /**
     * 添加用户信息
     *
     * @param userInfo
     * @return
     */
    boolean saveUserInfo(UserInfo userInfo);

    /**
     * 修改用户信息
     *
     * @param userId     用户ID
     * @param userStatus 用户状态
     * @param password   密码
     * @return
     */
    boolean updateUserInfo(Long userId, Integer userStatus, String password);

    /**
     * 分页查看
     *
     * @param page
     * @param userName
     * @return
     */
    IPage<UserInfo> pageUserInfo(Page<UserInfo> page, String userName);

    /**
     * 用户列表
     * @return
     */
    List<UserInfo> listUserInfo();
}
