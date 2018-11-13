package com.yzz.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zzy
 * @Date 2018/8/31 下午2:59
 */
@Controller
@RequestMapping("/yzz")
public class TestController {
    /**
     * 公会登录
     * @return
     */
    @RequestMapping("index")
    public ModelAndView toLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
}
