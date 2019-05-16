package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 用户收藏表
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public class UserCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 文章ID
     */
    private Long articleId;
    /**
     * 创建时间
     */
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserCollection{" +
        "id=" + id +
        ", userId=" + userId +
        ", articleId=" + articleId +
        ", createTime=" + createTime +
        "}";
    }
}
