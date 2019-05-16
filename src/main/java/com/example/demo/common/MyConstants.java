package com.example.demo.common;

public interface MyConstants {

  /**
   * 用户状态
   */
  interface UserStatus {

    /**
     * 正常
     */
    int nomal = 1;
    /**
     * 禁用
     */
    int stop = 0;
    /**
     * 删除
     */
    int delete = -1;
  }

  /**
   * 排序方式
   */
  interface sort{

    /**
     * 倒序
     */
    String desc = "desc";
    /**
     * 正序
     */
    String asc = "asc";
  }

  /**
   * 操作
   */
  interface Operation {
    /**
     * 添加
     */
    int add = 1;
    /**
     * 移除
     */
    int remove = -1;
  }
}
