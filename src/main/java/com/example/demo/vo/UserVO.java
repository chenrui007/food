package com.example.demo.vo;

import com.example.demo.entity.UserInfo;
import java.io.Serializable;

public class UserVO extends UserInfo implements Serializable{
  private Integer articleNum;

  public Integer getArticleNum() {
    return articleNum;
  }

  public void setArticleNum(Integer articleNum) {
    this.articleNum = articleNum;
  }
}
