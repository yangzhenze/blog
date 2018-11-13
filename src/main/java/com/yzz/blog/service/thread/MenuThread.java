package com.yzz.blog.service.thread;

import com.yzz.blog.entity.custom.CategoryCustom;
import com.yzz.blog.entity.custom.MenuCustom;
import com.yzz.blog.service.CategoryService;
import com.yzz.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author zzy
 * @Date 2018/9/14 下午1:50
 */
public class MenuThread implements Runnable {

    private CategoryService categoryService;

    private WebRequest request;

    private CountDownLatch countDownLatch;

    private MenuService menuService;

    public MenuThread(WebRequest request, CategoryService categoryService, MenuService menuService, CountDownLatch countDownLatch){
        this.request = request;
        this.categoryService = categoryService;
        this.menuService = menuService;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        //导航主要菜单显示
        //分类目录显示
        List<CategoryCustom> categoryList = null;
        //菜单显示
        List<MenuCustom> menuCustomList = null;
        try {
            categoryList = categoryService.listCategory(1);
            menuCustomList = menuService.listMenu(1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        request.setAttribute("menuCustomList",menuCustomList, WebRequest.SCOPE_REQUEST);
        request.setAttribute("categoryList",categoryList, WebRequest.SCOPE_REQUEST);
        countDownLatch.countDown();
    }
}
