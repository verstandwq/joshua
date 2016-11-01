package org.gyt.web.api.utils;

import org.gyt.web.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 模型和视图工具
 * Created by y27chen on 2016/9/20.
 */
@Component
public class ModelAndViewUtils {

    @Autowired
    private CountModelComponent countModelComponent;

    @Autowired
    private ArticleService articleService;

    private ModelAndViewUtils() {
    }

    public ModelAndView newModelAndView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        return modelAndView;
    }

    public ModelAndView newAdminModelAndView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("userCountModel", countModelComponent.getUserCountModel());
        modelAndView.addObject("articleCountModel", countModelComponent.getArticleCountModel());
        modelAndView.addObject("notificationCountModel", countModelComponent.getNotificationCountModel());
        modelAndView.addObject("messageCountModel", countModelComponent.getMessageCountModel());
        return modelAndView;
    }

    public void convertTo404(ModelAndView modelAndView) {
        convertTo404(modelAndView, "页面未找到");
    }

    public void convertTo404(ModelAndView modelAndView, String message) {
        modelAndView.setViewName("404");
        modelAndView.addObject("message", message);
        modelAndView.addObject("articles", articleService.getLatestArticles());
    }
}
