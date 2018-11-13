package com.yzz.blog.service.thread;

import com.yzz.blog.entity.custom.*;
import com.yzz.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author zzy
 * @Date 2018/9/14 下午1:50
 */
public class SideThread implements Runnable {


    private ArticleService articleService;


    private TagService tagService;

    private WebRequest request;

    private CountDownLatch countDownLatch;

    private CommentService commentService;

    public SideThread(WebRequest request, ArticleService articleService, TagService tagService, CommentService commentService, CountDownLatch countDownLatch){
        this.request = request;
        this.articleService = articleService;
        this.tagService = tagService;
        this.commentService = commentService;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        try {
            //侧边栏显示
            //标签列表显示
            List<TagCustom> tagList = tagService.listTag(1);
            request.setAttribute("tagList",tagList, WebRequest.SCOPE_REQUEST);
            //获得随机文章
            List<ArticleCustom> randomArticleList = articleService.listRandomArticle(1,8);
            request.setAttribute("randomArticleList",randomArticleList, WebRequest.SCOPE_REQUEST);
            //获得热评文章
            List<ArticleCustom> mostCommentArticleList = articleService.listArticleByCommentCount(1,8);
            request.setAttribute("mostCommentArticleList",mostCommentArticleList, WebRequest.SCOPE_REQUEST);
            //最新评论
            List<CommentListVo> recentCommentList = commentService.listRecentComment(10);
            request.setAttribute("recentCommentList",recentCommentList, WebRequest.SCOPE_REQUEST);

            //最后更新的文章
            ArticleCustom lastUpdateArticle = articleService.getLastUpdateArticle();
            request.setAttribute("lastUpdateArticle",lastUpdateArticle, WebRequest.SCOPE_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }
}
