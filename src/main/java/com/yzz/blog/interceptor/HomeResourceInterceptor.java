package com.yzz.blog.interceptor;
import com.yzz.blog.service.*;
import com.yzz.blog.service.thread.BasisThread;
import com.yzz.blog.service.thread.MenuThread;
import com.yzz.blog.service.thread.SideThread;
import com.yzz.blog.util.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class HomeResourceInterceptor implements WebRequestInterceptor {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CommentService commentService;




    /**
     * 在请求处理之前执行，该方法主要是用于准备资源数据的，然后可以把它们当做请求属性放到WebRequest中
     */
    @Override
    public void preHandle(WebRequest request) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        System.out.println("HomeResourceInterceptor...preHandle......");

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
        threadPoolExecutor.init(1,2,1);

        ExecutorService pool = threadPoolExecutor.getThreadPoolExecutor();
        MenuThread baseThread = new MenuThread(request,categoryService,menuService,countDownLatch);
        SideThread sideThread = new SideThread(request,articleService,tagService,commentService,countDownLatch);
        BasisThread basisThread = new BasisThread(request,articleService,categoryService,tagService,linkService,optionsService,countDownLatch);
        pool.execute(baseThread);
        pool.execute(sideThread);
        pool.execute(basisThread);
        threadPoolExecutor.destroy();
        countDownLatch.await();
    }
    /**
     * 该方法将在Controller执行之后，返回视图之前执行，ModelMap表示请求Controller处理之后返回的Model对象，所以可以在
     * 这个方法中修改ModelMap的属性，从而达到改变返回的模型的效果。
     */
    @Override
    public void postHandle(WebRequest request, ModelMap map) throws Exception {
        //System.out.println("postHandle.......");
    }

    /**
     * 该方法将在整个请求完成之后，也就是说在视图渲染之后进行调用，主要用于进行一些资源的释放
     */
    @Override
    public void afterCompletion(WebRequest request, Exception exception)
            throws Exception {
        //System.out.println("afterCompletion");
    }
}
