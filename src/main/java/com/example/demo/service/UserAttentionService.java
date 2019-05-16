package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.UserAttention;

/**
 * <p>
 * 用户关注其他用户表 服务类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public interface UserAttentionService extends IService<UserAttention> {
    /**
     * 添加关注
     *
     * @param currentUserId
     * @param userId
     * @return
     */
    boolean addAttention(Long currentUserId, Long userId);

    /**
     * 移除关注
     *
     * @param currentUserId
     * @param userId
     * @return
     */
    boolean removeAttention(Long currentUserId, Long userId);
}
