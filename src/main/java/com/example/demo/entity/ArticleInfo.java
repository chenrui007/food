package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 文章信息表
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public class ArticleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 文章ID
     */
    private Long articleId;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章图片 json
     */
    private String articlePicture;
    /**
     * 文章语音 json
     */
    private String articleVoice;
    /**
     * 阅读量
     */
    private Integer readingVolume;
    /**
     * 状态
     */
    private Integer status;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticlePicture() {
        return articlePicture;
    }

    public void setArticlePicture(String articlePicture) {
        this.articlePicture = articlePicture;
    }

    public String getArticleVoice() {
        return articleVoice;
    }

    public void setArticleVoice(String articleVoice) {
        this.articleVoice = articleVoice;
    }

    public Integer getReadingVolume() {
        return readingVolume;
    }

    public void setReadingVolume(Integer readingVolume) {
        this.readingVolume = readingVolume;
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
        return "ArticleInfo{" +
        "id=" + id +
        ", articleId=" + articleId +
        ", content=" + content +
        ", articlePicture=" + articlePicture +
        ", articleVoice=" + articleVoice +
        ", readingVolume=" + readingVolume +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
    }
}
