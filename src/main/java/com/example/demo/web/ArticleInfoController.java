package com.example.demo.web;


import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.entity.ArticleInfo;
import com.example.demo.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章信息表 前端控制器
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@RestController
@RequestMapping("/templates/articleInfo")
public class ArticleInfoController {
    @Autowired
    private ArticleInfoService articleInfoService;

    /**
     * 修改阅读量
     *
     * @param articleId
     * @return
     */
    @PostMapping("/updateReadNum")
    public R updateReadNum(Long articleId) {
        ArticleInfo articleInfo = articleInfoService.getByArticleId(articleId);
        articleInfo.setReadingVolume(articleInfo.getReadingVolume() + 1);
        return articleInfoService.updateArticleInfo(articleInfo) ? R.ok("") : R.failed("");
    }
}

