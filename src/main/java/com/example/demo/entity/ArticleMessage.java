package com.example.demo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户关注其他用户表
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public class ArticleMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 文章ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;
    /**
     * 留言内容
     */
    private String message;
    /**
     * 用户ID
     */
    private Long userId;
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

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ArticleMessage{" +
        "id=" + id +
        ", articleId=" + articleId +
        ", message=" + message +
        ", userId=" + userId +
        ", createTime=" + createTime +
        "}";
    }
}
