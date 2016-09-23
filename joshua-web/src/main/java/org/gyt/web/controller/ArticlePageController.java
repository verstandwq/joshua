package org.gyt.web.controller;

import org.gyt.web.api.service.ArticleService;
import org.gyt.web.api.utils.ModelAndViewUtils;
import org.gyt.web.model.Article;
import org.gyt.web.model.ArticleStatus;
import org.gyt.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台页面路由器
 * Created by Administrator on 2016/9/16.
 */
@RestController
@RequestMapping("/article")
public class ArticlePageController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/{id}")
    public ModelAndView detailsPage(
            @PathVariable String id
    ) {
        ModelAndView modelAndView = ModelAndViewUtils.newModelAndView("admin-article-details");
        Article article = articleService.get(Long.valueOf(id));

        if (null == article || !article.getStatus().equals(ArticleStatus.PUBLISHED)) {
            modelAndView.setViewName("404");
            modelAndView.addObject("message", "文章不存在或者未发布");
        } else {
            modelAndView.addObject("item", article);
            modelAndView.addObject("user", new User());
        }

        return modelAndView;
    }
}
