package com.example.demo.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.MyConstants;
import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleInfo;
import com.example.demo.service.ArticleInfoService;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author ZhaoMing
 * @since 2019-05-16
 */
@RestController
@RequestMapping("/templates/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleInfoService articleInfoService;

    /**
     * 分页查看文章
     *
     * @param page
     * @param articleName 文章名
     * @param categoryId  分类ID
     * @return
     */
    @GetMapping("/pageArticle")
    public R pageArticle(Page<Article> page, String articleName, Long categoryId) {
        IPage<Article> ret = articleService.pageArticle(page, articleName, categoryId, MyConstants.UserStatus.nomal);
        return R.ok(ret);
    }

    /**
     * 删除文章
     *
     * @param articleId
     * @return
     */
    @PostMapping("/deleteArticle")
    public R deleteArticle(Long articleId) {
        return articleService.deleteArticle(articleId) ? R.ok("删除成功") : R.failed("删除失败");
    }

    /**
     * 修改文章阅读状态
     *
     * @param articleId
     * @param canRead
     * @return
     */
    @PostMapping("/changeArticle")
    public R changeArticle(Long articleId, Integer canRead) {
        return articleService.changeArticle(articleId, canRead) ? R.ok("修改成功") : R.failed("修改失败");
    }

    /**
     * 添加文章
     *
     * @param picFile     图片
     * @param voiceFile   音频
     * @param articleName 文章名
     * @param categoryId  分类ID
     * @param session
     * @param content     内容
     * @return
     */
    @PostMapping("/saveArticle")
    public R saveArticle(@RequestParam(value = "picFile") MultipartFile picFile, @RequestParam(value = "voiceFile") MultipartFile voiceFile, String articleName, Long categoryId, HttpSession session, String content) {
        Long articleId = IdWorker.getId();
        Article article = new Article();
        article.setId(articleId);
        article.setArticleName(articleName);
        article.setCategoryId(categoryId);
        Long userId = Long.valueOf(session.getAttribute("userId").toString());
        article.setAuthor(userId);
        article.setStatus(MyConstants.UserStatus.nomal);
        Date date = new Date();
        article.setCreateTime(date);
        boolean saveFlag = articleService.saveArticle(article);
        if (saveFlag) {
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setId(IdWorker.getId());
            articleInfo.setArticleId(articleId);
            articleInfo.setContent(content);
            articleInfo.setArticleVoice("");
            articleInfo.setArticlePicture("");
            articleInfo.setStatus(MyConstants.UserStatus.nomal);
            articleInfo.setCreateTime(date);
            boolean saveArticleInfoFlag = articleInfoService.saveArticleInfo(articleInfo);
            if (saveArticleInfoFlag) {
                return R.ok("添加成功");
            } else {
                return R.failed("添加文章信息失败");
            }
        } else {
            return R.failed("添加文章失败");
        }
    }
}

