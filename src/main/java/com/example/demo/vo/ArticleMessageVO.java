package com.example.demo.vo;

import com.example.demo.entity.ArticleMessage;
import java.io.Serializable;

public class ArticleMessageVO extends ArticleMessage implements Serializable {
  private String userName;
  private String avatar;
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}
