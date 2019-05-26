package com.example.demo.vo;

import com.example.demo.entity.ArticleMessage;

import java.io.Serializable;
import java.util.List;

/**
 * @author ChenRui
 * @date 2019/5/26
 */
public class ArticleVO implements Serializable {
    private Long id;
    /**
     * 文章名
     */
    private String articleName;
    /**
     * 分类名
     */
    private String categoryName;
    /**
     * 作者
     */
    private String author;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片
     */
    private String articlePicture;
    /**
     * 音频
     */
    private String articleVoice;
    /**
     * 阅读量
     */
    private Integer readingVolume;
    /**
     * 留言
     */
    private List<ArticleMessage> articleMessage;

    private Long authorId;
    /**
     * 收藏状态
     */
    private Integer collectionStatus;

    public Integer getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(Integer collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public List<ArticleMessage> getArticleMessage() {
        return articleMessage;
    }

    public void setArticleMessage(List<ArticleMessage> articleMessage) {
        this.articleMessage = articleMessage;
    }
}
