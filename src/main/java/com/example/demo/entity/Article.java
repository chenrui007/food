package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import java.io.Serializable;

/**
 * <p> 文章表 </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public class Article implements Serializable {

  private static final long serialVersionUID = 1L;
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
  /**
   * 文章名
   */
  private String articleName;
  /**
   * 分类ID
   */
  @JsonSerialize(using = ToStringSerializer.class)
  private Long categoryId;
  /**
   * 作者
   */
  @JsonSerialize(using = ToStringSerializer.class)
  private Long author;
  /**
   * 状态
   */
  private Integer status;
  private Date createTime;
  private Integer canRead;
  @TableField(exist = false)
  private Integer readNum;
  @TableField(exist = false)
  private String categoryName;
  @TableField(exist = false)
  private String authorName;
  @TableField(exist = false)
  private String createTimeStr;


  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public String getCreateTimeStr() {
    return createTimeStr;
  }

  public void setCreateTimeStr(String createTimeStr) {
    this.createTimeStr = createTimeStr;
  }

  public Integer getReadNum() {
    return readNum;
  }

  public void setReadNum(Integer readNum) {
    this.readNum = readNum;
  }

  public Integer getCanRead() {
    return canRead;
  }

  public void setCanRead(Integer canRead) {
    this.canRead = canRead;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getArticleName() {
    return articleName;
  }

  public void setArticleName(String articleName) {
    this.articleName = articleName;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getAuthor() {
    return author;
  }

  public void setAuthor(Long author) {
    this.author = author;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "Article{" +
        "id=" + id +
        ", articleName=" + articleName +
        ", categoryId=" + categoryId +
        ", author=" + author +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
  }
}
