package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 登录账号
     */
    private String loginName;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;

    private Integer role;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别 0女 1男
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 状态 -1 删除 0 停用 1可用
     */
    private Integer status;
    private Date createTime;

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
        return "UserInfo{" +
        "id=" + id +
        ", loginName=" + loginName +
        ", userName=" + userName +
        ", password=" + password +
        ", avatar=" + avatar +
        ", sex=" + sex +
        ", age=" + age +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
    }
}
