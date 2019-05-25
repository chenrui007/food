package com.example.demo.web;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.MyConstants;
import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleInfo;
import com.example.demo.service.ArticleInfoService;
import com.example.demo.service.ArticleService;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p> 文章表 前端控制器 </p>
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
   * @param articleName 文章名
   * @param categoryId 分类ID
   */
  @GetMapping("/pageArticle")
  public R pageArticle(Page<Article> page, String articleName, Long categoryId) {
    IPage<Article> ret = articleService
        .pageArticle(page, articleName, categoryId, MyConstants.UserStatus.nomal);
    return R.ok(ret);
  }

  /**
   * 删除文章
   */
  @PostMapping("/deleteArticle")
  public R deleteArticle(Long articleId) {
    return articleService.deleteArticle(articleId) ? R.ok("删除成功") : R.failed("删除失败");
  }

  /**
   * 修改文章阅读状态
   */
  @PostMapping("/changeArticle")
  public R changeArticle(Long articleId, Integer canRead) {
    return articleService.changeArticle(articleId, canRead) ? R.ok("修改成功") : R.failed("修改失败");
  }

  /**
   * 添加文章
   *
   * @param picFile 图片
   * @param voiceFile 音频
   * @param articleName 文章名
   * @param categoryId 分类ID
   * @param content 内容
   */
  @PostMapping("/saveArticle")
  public R saveArticle(@RequestParam(value = "picFile") MultipartFile picFile,
      @RequestParam(value = "voiceFile") MultipartFile voiceFile, String articleName,
      Long categoryId, HttpSession session, String content)
      throws FileNotFoundException {

    ArticleInfo articleInfo = new ArticleInfo();

    if(!picFile.isEmpty()) {
      // 校验图片格式
      String picName = picFile.getOriginalFilename();
      String picfileExtension = picName.substring(picName.lastIndexOf("."));
      if (ObjectUtil.notEqual(".jpg", picfileExtension) && ObjectUtil
          .notEqual(".png", picfileExtension)) {
        return R.failed("请上传正确的图片类型");
      }
      String picfileName = System.currentTimeMillis() + picFile.getOriginalFilename();
      String picfilePath = ResourceUtils
          .getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "templates/article/pic").getPath();
      try {
        FileUtil.writeBytes(picFile.getBytes(), picfilePath + "/" + picfileName);
      } catch (IOException e) {
        return R.failed("上传图片失败");
      }
      articleInfo.setArticlePicture("article/pic/" + picfileName);
    }


    if(!voiceFile.isEmpty()){
      //校验音频格式
      String voiceName = voiceFile.getOriginalFilename();
      String voicefileExtension = voiceName.substring(voiceName.lastIndexOf("."));
      if (ObjectUtil.notEqual(".mp3", voicefileExtension)) {
        return R.failed("请上传正确的音频类型");
      }

      String voicefileName = System.currentTimeMillis() + picFile.getOriginalFilename();
      String voicefilePath = ResourceUtils
          .getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "templates/article/voice").getPath();
      try {
        FileUtil.writeBytes(picFile.getBytes(), voicefilePath + "/" + voicefileName);
      } catch (IOException e) {
        return R.failed("上传音频失败");
      }
      articleInfo.setArticleVoice("article/voice/" + voicefileName);
    }

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

      articleInfo.setId(IdWorker.getId());
      articleInfo.setArticleId(articleId);
      articleInfo.setContent(content);
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

