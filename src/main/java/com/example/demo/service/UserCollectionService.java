package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.UserCollection;
import java.util.List;

/**
 * <p>
 * 用户收藏表 服务类
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public interface UserCollectionService extends IService<UserCollection> {
    /**
     * 添加收藏
     *
     * @param userId    用户ID
     * @param articleId 文章ID
     * @return
     */
    boolean addCollection(Long userId, Long articleId);

    /**
     * 移除收藏
     *
     * @param userId    用户ID
     * @param articleId 文章ID
     * @return
     */
    boolean removeCollection(Long userId, Long articleId);

    UserCollection getUserCollection(Long userId, Long articleId);

    List<UserCollection> listUserCollection(Long userId);
}
