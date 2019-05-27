package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.UserAttention;
import com.example.demo.mapper.UserAttentionMapper;
import com.example.demo.service.UserAttentionService;
import java.util.List;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 用户关注其他用户表 服务实现类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Service
public class UserAttentionServiceImpl extends ServiceImpl<UserAttentionMapper, UserAttention> implements UserAttentionService {

    @Override
    public boolean addAttention(Long currentUserId, Long userId) {
        UserAttention userAttention = new UserAttention();
        userAttention.setId(IdWorker.getId());
        userAttention.setUserId(currentUserId);
        userAttention.setAttentionUserId(userId);
        userAttention.setCreateTime(new Date());
        return this.save(userAttention);
    }

    @Override
    public boolean removeAttention(Long currentUserId, Long userId) {
        return this.remove(new LambdaQueryWrapper<UserAttention>()
                .eq(UserAttention::getUserId, currentUserId)
                .eq(UserAttention::getAttentionUserId, userId));
    }

    @Override
    public UserAttention getUserAttention(Long userId, Long attentionUserId) {
        return this.getOne(new LambdaQueryWrapper<UserAttention>()
            .eq(UserAttention::getUserId,userId)
            .eq(UserAttention::getAttentionUserId,attentionUserId));
    }

    @Override
    public List<UserAttention> listUserAttention(Long userId) {
        return this.list(new LambdaQueryWrapper<UserAttention>().eq(UserAttention::getUserId,userId));
    }
}
