package com.yzz.blog.controller.home;

import com.yzz.blog.service.UserService;
import com.yzz.blog.util.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户的controller
 * Created by 言曌 on 2017/8/24.
 */
@Controller
public class UserController {

	@Autowired
	private  HttpServletRequest request;

	@Autowired
	private UserService userService;

	@Autowired
	private Functions functions;








}
