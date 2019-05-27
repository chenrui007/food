package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import java.io.Serializable;

/**
 * <p> 用户关注其他用户表 </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public class UserAttention implements Serializable {

  private static final long serialVersionUID = 1L;
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
  /**
   * 用户ID
   */
  @JsonSerialize(using = ToStringSerializer.class)
  private Long userId;
  @TableField(exist = false)
  private String userName;
  /**
   * 关注用户ID
   */
  @JsonSerialize(using = ToStringSerializer.class)
  private Long attentionUserId;
  /**
   * 关注用户文章数
   */
  @TableField(exist = false)
  private Integer attentionUserArticleNum;
  /**
   * 创建时间
   */
  private Date createTime;

  public Integer getAttentionUserArticleNum() {
    return attentionUserArticleNum;
  }

  public void setAttentionUserArticleNum(Integer attentionUserArticleNum) {
    this.attentionUserArticleNum = attentionUserArticleNum;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

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

  public Long getAttentionUserId() {
    return attentionUserId;
  }

  public void setAttentionUserId(Long attentionUserId) {
    this.attentionUserId = attentionUserId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "UserAttention{" +
        "id=" + id +
        ", userId=" + userId +
        ", attentionUserId=" + attentionUserId +
        ", createTime=" + createTime +
        "}";
  }
}
