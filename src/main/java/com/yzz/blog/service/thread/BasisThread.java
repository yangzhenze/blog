package com.yzz.blog.service.thread;

import com.yzz.blog.entity.Options;
import com.yzz.blog.entity.custom.CategoryCustom;
import com.yzz.blog.entity.custom.MenuCustom;
import com.yzz.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author zzy
 * @Date 2018/9/15 下午12:58
 */
public class BasisThread implements Runnable{
    private ArticleService articleService;

    private CategoryService categoryService;

    private TagService tagService;

    private LinkService linkService;

    private OptionsService optionsService;

    private WebRequest request;

    private CountDownLatch countDownLatch;

    public BasisThread(WebRequest request, ArticleService articleService,
                       CategoryService categoryService,TagService tagService,
                       LinkService linkService, OptionsService optionsService,CountDownLatch countDownLatch){
        this.request = request;
        this.articleService = articleService;
        this.tagService = tagService;
        this.categoryService = categoryService;
        this.linkService = linkService;
        this.optionsService = optionsService;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            //获得网站概况
            List<String> siteBasicStatistics = new ArrayList<String>();
            siteBasicStatistics.add(articleService.countArticle(1)+"");
            siteBasicStatistics.add(articleService.countArticleComment(1)+"");
            siteBasicStatistics.add(categoryService.countCategory(1)+"");
            siteBasicStatistics.add(tagService.countTag(1)+"");
            siteBasicStatistics.add(linkService.countLink(1)+"");
            siteBasicStatistics.add(articleService.countArticleView(1)+"");
            request.setAttribute("siteBasicStatistics",siteBasicStatistics, WebRequest.SCOPE_REQUEST);


            //页脚显示
            //博客基本信息显示(Options)
            Options options = optionsService.getOptions();
            request.setAttribute("options", options, WebRequest.SCOPE_REQUEST);
            request.setAttribute("currentDate", new Date(), WebRequest.SCOPE_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }
}
