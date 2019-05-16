package com.example.demo.web;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.MyConstants;
import com.example.demo.service.UserAttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户关注其他用户表 前端控制器
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Controller
@RequestMapping("/userAttention")
public class UserAttentionController {
    @Autowired
    private UserAttentionService userAttentionService;

    /**
     * 添加或移除关注
     *
     * @param userId
     * @param type
     * @return
     */
    @PostMapping("/addOrRemoveAttention")
    public R addOrRemoveAttention(HttpSession session, Long userId, Integer type) {
        Long currentUserId = Long.valueOf(session.getAttribute("userId").toString());
        if (ObjectUtil.equal(type, MyConstants.Operation.add)) {
            return userAttentionService.addAttention(currentUserId, userId) ? R.ok("添加成功") : R.failed("添加失败");
        } else if (ObjectUtil.equal(type, MyConstants.Operation.remove)) {
            return userAttentionService.removeAttention(currentUserId, userId) ? R.ok("移除成功") : R.failed("移除失败");
        } else {
            return R.failed("操作类型异常");
        }
    }
}

