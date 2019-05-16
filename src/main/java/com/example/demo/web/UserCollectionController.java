package com.example.demo.web;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.MyConstants;
import com.example.demo.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户收藏表 前端控制器
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@Controller
@RequestMapping("/userCollection")
public class UserCollectionController {
    @Autowired
    private UserCollectionService userCollectionService;

    /**
     * 添加或移除收藏
     *
     * @param session
     * @param articleId 文章ID
     * @return
     */
    @PostMapping("/addOrRemoveCollection")
    public R addOrRemoveCollection(HttpSession session, Long articleId, Integer type) {
        Long userId = Long.valueOf(session.getAttribute("userId").toString());
        if (ObjectUtil.equal(type, MyConstants.Operation.add)) {
            return userCollectionService.addCollection(userId, articleId) ? R.ok("添加成功") : R.failed("添加失败");
        } else if (ObjectUtil.equal(type, MyConstants.Operation.remove)) {
            return userCollectionService.removeCollection(userId, articleId) ? R.ok("移除成功") : R.failed("移除失败");
        } else {
            return R.failed("操作类型异常");
        }
    }
}

