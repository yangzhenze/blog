<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="shortcut icon" href="/img/logo.png">
    <title>
        ${options.optionSiteTitle}后台
            <rapid:block name="title"></rapid:block>
    </title>
    <link rel="stylesheet" href="/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="/css/back.css">
    <link rel="stylesheet" href="/plugin/font-awesome/css/font-awesome.min.css">
    <rapid:block name="header-style"></rapid:block>
    <rapid:block name="header-script"></rapid:block>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><a href="/admin" style="color:#009688;">
        ${options.optionSiteTitle}后台
        </a>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="/" target="_blank">前台</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">新建</a>
                <dl class="layui-nav-child">
                    <dd><a href="/admin/article/insert">文章</a></dd>
                    <dd><a href="/admin/page/insert">页面</a></dd>
                    <dd><a href="/admin/category/insert">分类</a></dd>
                    <dd><a href="/admin/tag/insert">标签</a></dd>
                    <dd><a href="/admin/notice/insert">公告</a></dd>
                    <dd><a href="/admin/link/insert">链接</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${loginUser.userAvatar}" class="layui-nav-img">
                    ${loginUser.userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/admin/user/profile/${loginUser.userId}">基本资料</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="/admin/logout">退了</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">文章</a>
                    <dl class="layui-nav-child">
                        <dd class="${menu[1]}"><a href="/admin/article?menu=1">全部文章</a></dd>
                        <dd class="${menu[2]}"><a href="/admin/article/insert?menu=2">写文章</a></dd>
                        <dd class="${menu[3]}"><a href="/admin/category?menu=3">全部分类</a></dd>
                        <dd class="${menu[4]}"><a href="/admin/tag?menu=4">全部标签</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">页面</a>
                    <dl class="layui-nav-child">
                        <dd class="${menu[5]}"><a href="/admin/page?menu=5">全部页面</a></dd>
                        <dd class="${menu[6]}"><a href="/admin/page/insert?menu=6">添加页面</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">
                        链接
                    </a>
                    <dl class="layui-nav-child">
                        <dd class="${menu[7]}"><a href="/admin/link?menu=7">全部链接</a></dd>
                        <dd class="${menu[8]}"><a href="/admin/link/insert?menu=8">添加链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">公告</a>
                    <dl class="layui-nav-child">
                        <dd class="${menu[9]}"><a href="/admin/notice?menu=9">全部公告</a></dd>
                        <dd class="${menu[10]}"><a href="/admin/notice/insert?menu=10">添加公告</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item ${menu[11]}">
                    <a href="/admin/comment?menu=11">
                        评论
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">
                        用户
                    </a>
                    <dl class="layui-nav-child">
                        <dd class="${menu[12]}"><a href="/admin/user?menu=12">全部用户</a></dd>
                        <dd class="${menu[13]}"><a href="/admin/user/insert?menu=13">添加用户</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">设置</a>
                    <dl class="layui-nav-child">
                        <dd class="${menu[14]}"><a href="/admin/menu?menu=14">菜单</a></dd>
                        <dd class="${menu[15]}"><a href="/admin/options?menu=15">主要选项</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <rapid:block name="content">

            </rapid:block>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © ${options.optionSiteTitle} - 后台
    </div>
</div>

<script src="/js/jquery.min.js"></script>
<script src="/plugin/layui/layui.all.js"></script>
<script src="/js/back.js"></script>
<rapid:block name="footer-script">

</rapid:block>
<script>
    //给文本编辑器的iframe引入代码高亮的css
    $("iframe").contents().find("head").append("<link rel=\"stylesheet\" href=\"/css/highlight.css\">\n");

</script>

</body>
</html>
