package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.UserCollection;
import com.example.demo.mapper.UserCollectionMapper;
import com.example.demo.service.UserCollectionService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 用户收藏表 服务实现类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection> implements UserCollectionService {

    @Override
    public boolean addCollection(Long userId, Long articleId) {
        UserCollection userCollection = new UserCollection();
        userCollection.setId(IdWorker.getId());
        userCollection.setUserId(userId);
        userCollection.setArticleId(articleId);
        userCollection.setCreateTime(new Date());
        return this.save(userCollection);
    }

    @Override
    public boolean removeCollection(Long userId, Long articleId) {
        return this.remove(new LambdaQueryWrapper<UserCollection>()
                .eq(UserCollection::getUserId, userId)
                .eq(UserCollection::getArticleId, articleId));
    }

    @Override
    public UserCollection getUserCollection(Long userId, Long articleId) {
        return this.getOne(new LambdaQueryWrapper<UserCollection>().eq(UserCollection::getArticleId, articleId).eq(UserCollection::getUserId, userId));
    }
}
